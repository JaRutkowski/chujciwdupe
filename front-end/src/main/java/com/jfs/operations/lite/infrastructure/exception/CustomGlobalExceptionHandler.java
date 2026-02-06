package com.jfs.operations.lite.infrastructure.exception;

import com.jfs.operations.lite.config.SystemProperties;
import com.jfs.operations.lite.dto.account.dto.JfsExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // error handler for @Valid
    //TODO: migrated to Spring Boot 3.0 @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        log.error(getEndpointData(request) + e.getLocalizedMessage());
        Map<String, Object> body = prepareResponseMap(e, status);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleError(HttpServletRequest request, Exception e) {
        log.error(getEndpointData(request.getMethod(), request.getRequestURL().toString()) + e.getLocalizedMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("500");
        return mav;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {
        log.error(getEndpointData(request) + e.getLocalizedMessage());
        return new ResponseEntity<>(prepareCustomExceptionDto(e, HttpStatus.BAD_REQUEST, Collections.singletonList(e.getMessage()),
                request.getDescription(false), ((ServletWebRequest) request).getHttpMethod().toString()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingCookieException.class)
    public ModelAndView handleMissingCookieException(HttpServletRequest request, Exception e) {
        log.error(getEndpointData(request.getMethod(), request.getRequestURL().toString()) + e.getLocalizedMessage());
        return new ModelAndView("redirect:/web/login/login-page?cookieError");
    }

    @ExceptionHandler(ExpiredPasswordException.class)
    public ModelAndView handleExpiredPasswordException(HttpServletRequest request, Exception e) {
        log.error(getEndpointData(request.getMethod(), request.getRequestURL().toString()) + e.getLocalizedMessage());
        return new ModelAndView("redirect:/web/login/login-page?expiredPassword");
    }

    private String getEndpointData(WebRequest request) {
        return MessageFormat.format(SystemProperties.getResourceBundle().getString("aspects.logTemplate.customGlobalExceptionHandler"),
                ((ServletWebRequest) request).getHttpMethod().toString(), request.getDescription(false));
    }

    private String getEndpointData(String httpMethod, String description) {
        return MessageFormat.format(SystemProperties.getResourceBundle().getString("aspects.logTemplate.customGlobalExceptionHandler"),
                httpMethod, description);
    }

    private Map prepareResponseMap(Exception e, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = new ArrayList<>();
        if (e instanceof MethodArgumentNotValidException)
            errors = ((MethodArgumentNotValidException) e).getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
        else
            errors.add(e.getMessage());
        body.put("errors", errors);
        return body;
    }

    private JfsExceptionDto prepareCustomExceptionDto(Exception e, HttpStatus status, List<String> messages, String path, String method) {
        return JfsExceptionDto.builder()
                .timestamp(new Date())
                .status(status.value())
                .messages(messages)
                .path(path)
                .method(method)
                .build();
    }
}
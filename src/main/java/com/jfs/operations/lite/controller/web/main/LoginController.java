package com.jfs.operations.lite.controller.web.main;

import com.jfs.operations.lite.controller.web.utils.RestClient;
import com.jfs.operations.lite.dto.main.ExpirationResponseDto;
import com.jfs.operations.lite.dto.main.RegisterResponseDto;
import com.jfs.operations.lite.model.account.domain.UserData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Objects;

//TODO: change to account package, issue with prod build

@Controller
@RequestMapping("/web/login")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final HttpServletRequest request;
    private final RestClient restClient;
    private String authJfsCookie;
    @Value("${app.security.registration.enabled}")
    private String securityEnabled;

    @GetMapping("/login-page")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new UserData());
        return "login-form";
    }

    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute("user") UserData userData, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String response = "redirect:/web/app/dashboard";
        try {
            if(isPasswordExpired(userData))
                throw new Exception(RootCause.EXPIRED_PASSWORD.getMessage());

            restClient.postSync("/v1/api/auth/authenticate", userData, res -> {
                if (res.statusCode().is2xxSuccessful()) {
                    log.info("COOKIE RECEIVED: {}", res.cookies().get("auth-jfs"));
                    authJfsCookie = res.cookies().get("auth-jfs").get(0).getValue();
                    return res.bodyToMono(UserData.class);
                } else {
                    return res.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new Exception(body)));
                }
            });

            Cookie cookie = new Cookie("auth-jfs", authJfsCookie);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);

            response += "?registered";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            if(e.getMessage() != null && e.getMessage().contains(RootCause.INVALID_TOTP.getMessage()))
                response = "redirect:/web/login/login-page?invalidCode";
            else if(e.getMessage() != null && e.getMessage().contains(RootCause.EXPIRED_PASSWORD.getMessage()))
                response = "redirect:/web/login/login-page?expiredPassword";
            else
                response = "redirect:/web/login/login-page?error";
        }
        return response;
    }

    @GetMapping("/register-page")
    public String showRegistrationPage(Model model) {
        if (!Boolean.FALSE.toString().equals(securityEnabled)) {
            model.addAttribute("user", new UserData());
            Map<String, String> captcha = (Map<String, String>) restClient.getSync("/v1/api/captcha/generate", Map.class);
            model.addAttribute("captchaQuestion", captcha.get("question"));
            model.addAttribute("captchaToken", captcha.get("token"));
            return "registration-form";
        } else
            return "redirect:/web/login/login-page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserData userData) throws NoSuchAlgorithmException {
        String response = "redirect:/web/login/register-page";
        try {
            Map<String, Object> captchaBody = Map.of("captchaToken", userData.getCaptchaToken(),
                    "captchaAnswer", userData.getCaptchaAnswer());
            restClient.postSync("/v1/api/captcha/validate", captchaBody, res ->
                    !res.statusCode().is2xxSuccessful()
                            ? res.bodyToMono(String.class).flatMap(body -> Mono.error(new Exception(body)))
                            : res.bodyToMono(Map.class)
            );
            //TODO: userData has been changed userDetailsService.createUser(userData.getLogin(), userData.getPassword());
            RegisterResponseDto registerResponseDto = (RegisterResponseDto) restClient.postSync("/v1/api/auth/register",
                    userData,
                    res -> !res.statusCode().is2xxSuccessful()
                            ? res.bodyToMono(String.class).flatMap(body -> Mono.error(new Exception(body))) : res.bodyToMono(RegisterResponseDto.class));

            response += "?registered&param=" + registerResponseDto.totpQr();
            Cookie[] cookies = request.getCookies();

            if (cookies != null)
                for (Cookie cookie : cookies)
                    if (cookie.getName().equals("cookieName"))
                        return "Cookie Value: " + cookie.getValue();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            if (Objects.nonNull(e.getMessage()) && e.getMessage().contains(RootCause.INVALID_PASSWORD.getMessage()))
                response = "redirect:/web/login/register-page?passwordInvalid";
            else if (Objects.nonNull(e.getMessage()) && e.getMessage().contains(RootCause.INVALID_USERNAME.getMessage()))
                response = "redirect:/web/login/register-page?loginAlreadyExists";
            else if (Objects.nonNull(e.getMessage()) && e.getMessage().contains(RootCause.INVALID_DATA_TYPE.getMessage()))
                response = "redirect:/web/login/register-page?captchaInvalid";
            else if (Objects.nonNull(e.getMessage()) && e.getMessage().contains(RootCause.INVALID_RESULT_VALUE.getMessage()))
                response = "redirect:/web/login/register-page?captchaInvalid";
            else
                response = "redirect:/web/login/register-page?error";
        }
        return response;
    }

    private boolean isPasswordExpired(UserData userData) {
        ExpirationResponseDto expirationResponseDto = (ExpirationResponseDto) restClient.postSync("/v1/api/pass-expiration/check-expiration", userData, res ->
                res.statusCode().is2xxSuccessful()
                        ? res.bodyToMono(ExpirationResponseDto.class)
                        : res.bodyToMono(String.class).flatMap(body -> Mono.error(new Exception(body)))
        );

        return expirationResponseDto.expirationIn() != null && expirationResponseDto.expirationIn() <= 0;
    }
}
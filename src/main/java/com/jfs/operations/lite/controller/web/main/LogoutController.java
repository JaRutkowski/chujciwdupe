package com.jfs.operations.lite.controller.web.main;

import com.jfs.operations.lite.controller.web.utils.RestClient;
import com.jfs.operations.lite.dto.main.LogoutRequestDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/web")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class LogoutController {
    final RestClient restClient;

    @GetMapping("/sign-out")
    public String signOut(HttpServletRequest httpServletRequest){
        var authorizationToken = getCookieValue("auth-jfs", httpServletRequest.getCookies());
        var logoutUrl = "/v1/api/auth/logout";
        try{
            restClient.postSync(logoutUrl, buildLogoutRequestDTO(authorizationToken), this::onExchangeMono);
            log.info("WebClient call success: {}", logoutUrl);
            return "redirect:/web/login/login-page";
        }catch (Exception e){
            log.error("Exception occur during logout: {}", e.getMessage(), e);
            return "redirect:/404-page";
        }
    }

    private Mono<String> onExchangeMono(ClientResponse response){
        return response.statusCode().is2xxSuccessful()
                ? response.bodyToMono(String.class)
                : response.createException().flatMap(Mono::error);
    }

    private String getCookieValue(String name, Cookie[] cookies){
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(name))
                .map(Cookie::getValue)
                .limit(1L)
                .collect(Collectors.joining());
    }

    private LogoutRequestDTO buildLogoutRequestDTO(String authorizationToken){
        return LogoutRequestDTO.builder()
                .authorizationToken(authorizationToken)
                .build();
    }

}

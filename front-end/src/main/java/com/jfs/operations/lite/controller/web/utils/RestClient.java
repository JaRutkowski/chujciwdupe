package com.jfs.operations.lite.controller.web.utils;

import com.jfs.operations.lite.infrastructure.exception.ExpiredPasswordException;
import com.jfs.operations.lite.infrastructure.exception.MissingCookieException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.core.ParameterizedTypeReference;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class RestClient {
    //TODO: review if the server url should be provided
    @Value("${server.url}")
    private String serverPort;
    @Value("${app.server.public.path}")
    private String serverPath;
    private WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder()
                .baseUrl(serverPort + Optional.ofNullable(serverPath).orElse(""))
                .build();
    }

    public Object postSync(String url, Object body, Class<?> type) {
        return webClient.post()
                .uri(url)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(type)
                .block();
    }

    public Object postSync(String url, Object body, Class<?> argType, Class<?> type) {
        return webClient.post()
                .uri(url)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(type)
                .block();
    }

    public Object postSync(String url, Class<?> type) {
        return webClient.post()
                .uri(url)
                .retrieve()
                .bodyToMono(type)
                .block();
    }

    public Object postSync(String url, Object bodyValue, Function<ClientResponse, ? extends Mono<?>> onExchangeMono) {
        return webClient.post()
                .uri(url)
                .bodyValue(bodyValue)
                .exchangeToMono(response -> onExchangeMono.apply(response).map(r -> (Object) r))
                .block();
    }

    public Object putSync(String url, Class<?> type) {
        return webClient.put()
                .uri(url)
                .retrieve()
                .bodyToMono(type)
                .block();
    }

    public Object putSync(String url, Object bodyValue, HttpServletRequest request, Function<ClientResponse, ? extends Mono<?>> onExchangeMono) {
        return webClient.put()
                .uri(url)
                .cookie(getCookie(request).getName(), getCookie(request).getValue())
                .header("Authorization", "Bearer " + getCookie(request).getValue())
                .bodyValue(bodyValue)
                .exchangeToMono(response -> onExchangeMono.apply(response).map(r -> (Object) r))
                .block();
    }

    public Object postSync(String url, Object bodyValue, Cookie cookie, Function<ClientResponse, ? extends Mono<?>> onExchangeMono) {
        return webClient.post()
                .uri(url)
                .cookie(cookie.getName(), cookie.getValue())
                .header("Authorization", "Bearer " + cookie.getValue())
                .bodyValue(bodyValue)
                .exchangeToMono(response -> onExchangeMono.apply(response).map(r -> (Object) r))
                .block();
    }

    public Object getSync(String url, Class<?> type) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(type)
                .block();
    }

    public Object getSync(String url, Class<?> type, HttpServletRequest request) {
        try {
            return webClient.get()
                    .uri(url)
                    .cookie(getCookie(request).getName(), getCookie(request).getValue())
                    .header("Authorization", "Bearer " + getCookie(request).getValue())
                    .retrieve()
                    .bodyToMono(type)
                    .block();
        } catch (WebClientResponseException e) {
            String errorMessage = e.getHeaders().getFirst("Error-Message");
            if (Objects.isNull(errorMessage))
                throw e;
            else if(errorMessage.equals("Password expired"))
                throw new ExpiredPasswordException("Password expired");
            else
                throw new RuntimeException(errorMessage, e);
        }
    }

    public <T> T getSyncWithType(String url, ParameterizedTypeReference<T> type, HttpServletRequest request) {
        try {
            return webClient.get()
                    .uri(url)
                    .cookie(getCookie(request).getName(), getCookie(request).getValue())
                    .header("Authorization", "Bearer " + getCookie(request).getValue())
                    .retrieve()
                    .bodyToMono(type)
                    .block();
        } catch (WebClientResponseException e) {
            String errorMessage = e.getHeaders().getFirst("Error-Message");
            if (Objects.isNull(errorMessage))
                throw e;
            else if(errorMessage.equals("Password expired"))
                throw new ExpiredPasswordException("Password expired");
            else
                throw new RuntimeException(errorMessage, e);
        }
    }


    public Object getAsync(String url, Class<?> type) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(type)
                .collectList().block();
    }

    private Cookie getCookie(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("auth-jfs"))
                .findFirst()
                .orElseThrow(() -> new MissingCookieException("Cookie missing or expired"));
    }

    public <T> T getSync(String url, ParameterizedTypeReference<T> responseType, HttpServletRequest request) {
        try {
            Object res = webClient.get()
                    .uri(url)
                    .cookie(getCookie(request).getName(), getCookie(request).getValue())
                    .header("Authorization", "Bearer " + getCookie(request).getValue())
                    .retrieve()
                    .bodyToMono(responseType)
                    .block();
            return (T) res;
        } catch (WebClientResponseException e) {
            String errorMessage = e.getHeaders().getFirst("Error-Message");
            if (Objects.isNull(errorMessage))
                throw e;
            else if(errorMessage.equals("Password expired"))
                throw new ExpiredPasswordException("Password expired");
            else
                throw new RuntimeException(errorMessage, e);
        }
    }
}

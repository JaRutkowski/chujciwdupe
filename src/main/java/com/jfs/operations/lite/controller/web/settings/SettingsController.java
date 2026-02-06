package com.jfs.operations.lite.controller.web.settings;

import com.google.gson.Gson;
import com.jfs.operations.lite.controller.web.main.RootCause;
import com.jfs.operations.lite.controller.web.utils.RestClient;
import com.jfs.operations.lite.dto.settings.Passwords;
import com.jfs.operations.lite.infrastructure.exception.MissingCookieException;
import com.jfs.operations.lite.model.account.domain.UserData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

@Controller
@RequestMapping("/web/settings")
@RequiredArgsConstructor
@Slf4j
public class SettingsController {
    private final RestClient restClient;
    private final HttpServletRequest httpServletRequest;
    private String authJfsCookie;

    @GetMapping("/change-password-page")
    public String showChangePasswordPage(Model model) {
        model.addAttribute("passwords", new Passwords());
        return "password-change-form";
    }

    @PostMapping
    public String changePassword(@ModelAttribute("passwords") Passwords passwords, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String response = "redirect:/web/app/dashboard?registered";
        if (!passwords.getNewPassword().equals(passwords.getConfirmPassword()))
            return "redirect:/web/settings/change-password-page?mismatchPassword";
        try {
            restClient.putSync("/v1/api/settings/change-password", buildUserDataFromCookie(passwords, request), httpServletRequest, res -> !res.statusCode().is2xxSuccessful()
                    ? res.bodyToMono(String.class).flatMap(body -> Mono.error(new Exception(body))) : res.bodyToMono(String.class));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            if (e.getMessage() != null && e.getMessage().contains(RootCause.INVALID_PASSWORD.getMessage()))
                response = "redirect:/web/settings/change-password-page?invalidPassword";
            else if (e.getMessage() != null && e.getMessage().contains(RootCause.MATCHING_PASSWORD.getMessage())) {
                response = "redirect:/web/settings/change-password-page?matchingPassword";
            } else
                response = "redirect:/web/settings/change-password-page?error";
        }
        return response;
    }

    private UserData buildUserDataFromCookie(Passwords passwords, HttpServletRequest request) {
        authJfsCookie = Arrays.stream(request.getCookies())
                .filter(e -> e.getName().equals("auth-jfs"))
                .findFirst()
                .orElseThrow(() -> new MissingCookieException("Cookie missing or expired"))
                .getValue();
        String[] jwtChunks = authJfsCookie.split("\\.");
        String username = new Gson().fromJson(new String(Base64.getUrlDecoder().decode(jwtChunks[1])), Map.class).get("username").toString();
        return UserData.builder().username(username).password(passwords.getNewPassword()).build();
    }
}
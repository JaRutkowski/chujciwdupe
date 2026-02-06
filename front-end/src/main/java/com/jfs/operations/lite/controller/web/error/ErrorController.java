package com.jfs.operations.lite.controller.web.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/app/error")
@RequiredArgsConstructor
@Slf4j
public class ErrorController {
    @GetMapping("/404-page")
    public String show404Page() {
        return "404";
    }
}

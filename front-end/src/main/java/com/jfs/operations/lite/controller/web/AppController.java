package com.jfs.operations.lite.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	@GetMapping("/")
	public String showLoginPage() {
		return "redirect:/web/login/login-page";
	}
}

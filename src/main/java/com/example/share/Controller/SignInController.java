package com.example.share.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@ControllerAdvice
@RequestMapping("/")
public class SignInController {
	@GetMapping(value = "/signin")
	public String viewHome() {
		return "auth-normal-sign-in";
	}
}

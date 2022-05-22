package com.example.share.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Mycontroller {
	@GetMapping("/")
	public String viewHome() {
		return "index";
	}

}

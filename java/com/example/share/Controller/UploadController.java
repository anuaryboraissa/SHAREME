package com.example.share.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UploadController {
	@RequestMapping("/resource")
	public String viewRegister() {
		return "resource";
	}
}

package com.example.share.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HistoryController {
	@RequestMapping("/history")
	public String viewHome() {
		return "bs-basic-table";
	}
}

package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@GetMapping("/")
	public String getReuest() {
		return "Hello From Spring Project";
	}
	
	@GetMapping("/demo")
	public String getDemoReuest() {
		return "Hello From Demo Get Request";
	}

}

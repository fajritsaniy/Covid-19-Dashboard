package com.covid.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/html/index.html")
	public String covidDashboard(Model model) {
		return "index";
	}

	@GetMapping("/html/countries.html")
	public String covidDashboard1(Model model) {
		return "countries";
	}

	@GetMapping("/html/symptoms.html")
	public String covidDashboard2(Model model) {
		return "symptoms";
	}

	@GetMapping("/html/prevention.html")
	public String covidDashboard3(Model model) {
		return "prevention";
	}

	@GetMapping("/html/faqs.html")
	public String covidDashboard4(Model model) {
		return "faqs";
	}

	@GetMapping("/html/components/index.html")
	public String covidDashboard5(Model model) {
		return "components/index.html";

	}
}

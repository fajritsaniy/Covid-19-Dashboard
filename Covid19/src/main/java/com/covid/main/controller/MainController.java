package com.covid.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.covid.main.model.AllCovidInfoModel;
import com.covid.main.services.AllCovidRepository;
import com.covid.main.services.CovidApiService;

import retrofit2.Retrofit;

@Controller
public class MainController {
	@Autowired
	CovidApiService covidService;
	
	
	@GetMapping("/")
	public String covidDashboard(Model model) {

		
		List<AllCovidInfoModel> lstInfo = covidService.getAllCovidInfo();
		model.addAttribute("allCovidInfo", lstInfo);
		for (AllCovidInfoModel allCovidInfo : lstInfo) {
			System.out.println(allCovidInfo.toString());
		}
		
		

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

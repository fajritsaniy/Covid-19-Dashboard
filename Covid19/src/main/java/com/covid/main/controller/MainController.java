package com.covid.main.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.covid.main.model.AllCovidInfoModel;
import com.covid.main.model.FaqsModel;
import com.covid.main.model.MockPostModel;
import com.covid.main.model.PreventionModel;
import com.covid.main.repository.AllCovidRepository;
import com.covid.main.repository.FaqsRepository;
import com.covid.main.repository.PreventionRepository;
import com.covid.main.services.CovidApiService;
import com.covid.main.services.MockPostService;
import com.covid.main.utility.FileUtility;

import retrofit2.Retrofit;

@Controller
public class MainController {
	@Autowired
	CovidApiService covidService;
	
	@Autowired
	MockPostService mockService;
	
	@Autowired
	PreventionRepository preventionRepo;
	
	@Autowired
	FaqsRepository faqsRepo;
	

	@GetMapping("/")
	public String covidDashboard(@RequestParam(name="country",defaultValue = "") String country,Model model) {
		AllCovidInfoModel covid = new AllCovidInfoModel();
		if (country.equalsIgnoreCase("")) {
			covid=covidService.getCovidInfo("world");
		}
		else {
			covid=covidService.getCovidInfo(country);
		}
		List<AllCovidInfoModel> lstInfo = covidService.getAllCovidInfo();
		model.addAttribute("allCovidInfo", lstInfo);
		model.addAttribute("covidSearch",covid);
		model.addAttribute("mockModel", new MockPostModel());
		for (AllCovidInfoModel allCovidInfo : lstInfo) {
			System.out.println(allCovidInfo.toString());
		}
		return "index";
	}
	
//	
//	@GetMapping("/")
//	public String covidDashboard(Model model) {
//
//		
//		List<AllCovidInfoModel> lstInfo = covidService.getAllCovidInfo();
//		model.addAttribute("allCovidInfo", lstInfo);
//		for (AllCovidInfoModel allCovidInfo : lstInfo) {
//			System.out.println(allCovidInfo.toString());
//		}
//		return "index";
//	}
	
	@PostMapping("/")
	public String inputModel(@ModelAttribute("mockModel") MockPostModel data) {
		MockPostModel response = mockService.inputModel(data);
		return "redirect:/";
		
	}

	@GetMapping("/{country}")
	public String covidDashboard0(@ModelAttribute("allCovidInfo") List<AllCovidInfoModel> lstInfo, @PathVariable(value="country") String country, Model model) {
		
		List<AllCovidInfoModel> lstInfoo = lstInfo;
		model.addAttribute("allCovidInfo", lstInfoo);
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
		List<PreventionModel> lstPrevention = preventionRepo.findAll();
		model.addAttribute("lstPrevention",lstPrevention);
		return "prevention";
	}
	
	@GetMapping("/prevention/input")
	public String getInputPrevention(Model model) {
		
		model.addAttribute("preventionModel", new PreventionModel());
		return "input_prevention";
	}
	
	@PostMapping("/prevention/input")
	public String inputPrevention(
			@ModelAttribute("preventionModel") PreventionModel data,
			Model model,
			@RequestParam("file") MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		String type = file.getContentType();
		if (type.equalsIgnoreCase("image/png") || type.equalsIgnoreCase("image/jpeg")) {
			String uploadDir = "prevention-image/";
			FileUtility.saveFile(file, uploadDir, fileName);
			data.setImage(fileName);
			this.preventionRepo.save(data);
		}
		else {
			System.out.println("Bukan Format yang Benar");
		}
		
	
		
		return "redirect:/html/prevention.html";
	}

	@GetMapping("/html/faqs.html")
	public String covidDashboard4(Model model) {
		List<FaqsModel> lstFaqs = faqsRepo.findAll();
		model.addAttribute("lstFaqs",lstFaqs);
		return "faqs";
	}
	@GetMapping("/faqs.html")
	public String covidDashboard6(Model model) {
		List<FaqsModel> lstFaqs = faqsRepo.findAll();
		model.addAttribute("lstFaqs",lstFaqs);
		return "faqs";
	}
	
	@GetMapping("/faqs/input")
	public String getInputFaqs(Model model) {
		
		model.addAttribute("faqsModel", new FaqsModel());
		return "input_faqs";
	}
	
	@PostMapping("/faqs/input")
	public String inputFaqs(
			@ModelAttribute("faqsModel") FaqsModel data,
			Model model,
			@RequestParam("file") MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		String type = file.getContentType();
		if (type.equalsIgnoreCase("image/png") || type.equalsIgnoreCase("image/jpeg")) {
			String uploadDir = "faqs-image/";
			FileUtility.saveFile(file, uploadDir, fileName);
			data.setImage(fileName);
			this.faqsRepo.save(data);
		}
		else {
			System.out.println("Bukan Format yang Benar");
		}
		
	
		
		return "redirect:/html/faqs.html";
	}

	@GetMapping("/html/components/index.html")
	public String covidDashboard5(Model model) {
		return "components/index.html";

	}
}

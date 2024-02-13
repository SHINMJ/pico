package com.example.pico.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.pico.dto.PrizeResponse;
import com.example.pico.dto.ProductResponse;
import com.example.pico.service.ProductService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private final ProductService productService;
	
	public HomeController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		List<ProductResponse> products = this.productService.findAll();
		
		model.addAttribute("products", products );
		
		return "home";
	}
	
	@ResponseBody
	@PostMapping("/order")
	public PrizeResponse order(@RequestBody List<Long> productIds) {
		
		logger.info("checked values : {}",productIds.toString());
		
		PrizeResponse prize = productService.getPrizeFromProducts(productIds);
		logger.info("경품은? {}", prize.getName());
		return prize;
	}
	
	
	
}

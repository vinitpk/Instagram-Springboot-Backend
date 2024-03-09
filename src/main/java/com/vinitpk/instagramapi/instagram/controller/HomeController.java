package com.vinitpk.instagramapi.instagram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling requests related to the home page.
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: [Date]
 */
@RestController
public class HomeController {

	// Handler method for the home page
	@GetMapping("/test")
	public String homeControllerHandler() {
		// Return a welcome message
		return "welcome to instagram backend api";
	}
}

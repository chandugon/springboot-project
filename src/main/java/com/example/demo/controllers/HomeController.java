package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.loginCredentials.AdminLogin;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ProductServices;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductServices productServices;

	HomeController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping(value = { "/home", "/" })
	public String home() {
		return "Home";
	}

	@GetMapping("/products")
	public String products(Model model) {
		List<Product> allProducts = this.productServices.getAllProducts();
		model.addAttribute("products", allProducts);
		return "Products";
	}

	@GetMapping("/location")
	public String location() {
		return "Locate_us";
	}

	@GetMapping("/about")
	public String about() {
		return "About";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute User user) {
		userRepository.save(user);
		return "redirect:/login";
	}

	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("userRegistration", new User());
		return "register";
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("adminLogin", new AdminLogin());
		return "Login";
	}

}
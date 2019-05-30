package com.example.demo.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.EmailService;
import com.example.demo.MD5Util;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@Controller
public class RegisterController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	EmailService emailService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/register")
	public String register() {

		return "register";
	}

	@PostMapping("/register")
	public String registering(User user) {
		LocalDate now = LocalDate.now();

		user.setRegisterDate(now);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setActivated(true);
		user.setToken(MD5Util.md5(now.toString() + user.getUsername()));
		userRepository.save(user);
		//emailService.sendActivationMessage(user);
		return "login";
	}



}

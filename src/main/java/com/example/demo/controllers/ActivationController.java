package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@Controller
@RequestMapping("/activation/")
public class ActivationController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/{token}")
	public String register(@PathVariable String token) {
			User user = userRepository.findByToken(token);
			user.setActivated(true);
			userRepository.save(user);

		
		return "accountActivation";
	}

}

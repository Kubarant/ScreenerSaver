package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class EmailService {

	
	@Autowired
	public JavaMailSender emailSender;

	public void sendActivationMessage(User user) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmail());
		message.setFrom("obraskihost@o2.pl");
		message.setSubject("Aktywuj Konto");
		message.setText("127.0.0.1:8080/activation/"+user.getToken());
		emailSender.send(message);

	}
	
	
}

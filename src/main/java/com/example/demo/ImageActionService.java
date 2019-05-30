package com.example.demo;

import java.security.Principal;
import java.util.Optional;

import com.example.demo.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserImagesRepository;
import com.example.demo.repositories.UserRepository;

import javax.transaction.Transactional;

@Service
public class ImageActionService {
	
	@Autowired
	UserRepository userRepository;

	@Transactional
	public void deleteImage(Principal suser, String imageTitle) {
		User user = userRepository.findByUsername(suser.getName());
		Optional<Image> imageToDelete = user.getImages()
				.stream()
				.filter(img -> img.getTitle()
						.equals(imageTitle))
				.findFirst();
		imageToDelete.ifPresent(image ->user.getImages().remove(image));

	}
	
	
	
	
}

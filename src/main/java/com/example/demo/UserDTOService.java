package com.example.demo;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Image;
import com.example.demo.model.User;
import com.example.demo.model.UserPageDTO;
import com.example.demo.repositories.UserRepository;

@Service
public class UserDTOService {
	@Autowired
	UserRepository userRepository;
	
	public UserDTOService() {}
	

	public UserPageDTO createUserPageDTO(Principal princ) {
		UserPageDTO info = new UserPageDTO();
		User user = userRepository.findByUsername(princ.getName());
		info.setUsername(user.getUsername());
		List<Image> images = user.getImages();
		images.stream().forEach(img->{img.setData(new byte[] {});img.setThumbData(new byte[] {});});
		info.setImages(images);
		return info;
		
	}
}

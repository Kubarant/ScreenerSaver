package com.example.demo.controllers;

import com.example.demo.ImageActionService;
import com.example.demo.model.Image;
import com.example.demo.repositories.UserImagesRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class ImageRetrievingService {

    private final UserImagesRepository imagesRepository;
    final UserRepository userRepository;

    @Autowired
    public ImageRetrievingService(UserRepository userRepository, ImageActionService imageActionService, UserImagesRepository imagesRepository) {
        this.userRepository = userRepository;
        this.imagesRepository= imagesRepository;
    }

    public Optional<Image> findUserImageByTitle(String title, Principal principal) {
        return userRepository.findByUsername(principal.getName())
                .getImages()
                .stream()
                .filter(img -> img.getTitle()
                        .equals(title))
                .findFirst();

    }

    public Optional<byte[]> getImageData(String title, Principal principal) {
        return findUserImageByTitle(title, principal).map(Image::getData);
    }

    public Optional<byte[]> getThumbnailData(String title, Principal principal) {
        return findUserImageByTitle(title, principal)
                .map(Image::getThumbData);
    }

    public Optional<Image> findImageByToken(String token){
        return imagesRepository.findByToken(token);
    }

    public Optional<byte[]> getImageDataByToken(String token) {
        return findImageByToken(token).map(Image::getData);
    }

    public Optional<byte[]> getThumbnailDataByToken(String token) {
        return findImageByToken(token)
                .map(Image::getThumbData);
    }
}

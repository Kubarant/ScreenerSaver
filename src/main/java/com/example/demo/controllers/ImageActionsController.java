package com.example.demo.controllers;

import com.example.demo.ImageActionService;
import com.example.demo.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ImageActionsController {

    @Autowired
    ImageActionService imageActionService;

    @PostMapping("/images/delete")
    public void deleteImage(@RequestBody Image img, Principal principal) {
        imageActionService.deleteImage(principal,img.getTitle());
    }
}

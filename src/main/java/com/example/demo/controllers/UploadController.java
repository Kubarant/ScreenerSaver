package com.example.demo.controllers;

import com.example.demo.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
public class UploadController {

    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService= uploadService;
    }

    @PostMapping(value = "/upload")
   public String upload(@RequestPart("file") MultipartFile file, Principal principal, @RequestPart("title") String title) {
        uploadService.saveNewImage(file, principal, title);

        return file.toString();

    }
}

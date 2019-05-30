package com.example.demo.controllers;

import com.example.demo.UserDTOService;
import com.example.demo.model.UserPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.concurrent.TimeUnit;



@RestController
public class ImageRestController {

    @Autowired
    UserDTOService userInfoService;

    @Autowired
    ImageRetrievingService imageRetrievingService;


    @GetMapping(value = "/images/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity getImageByTitle(@PathVariable String name, Principal principal) {
        return Optional.ofNullable(principal).flatMap(user-> imageRetrievingService.getImageData(name, user)).
                map(bytes -> ResponseEntity.ok().cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES)).body(bytes)).
                orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/images/thumb/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getThumbnailByTitle(@PathVariable String name, Principal principal) {
        return Optional.ofNullable(principal).flatMap(user-> imageRetrievingService.getThumbnailData(name, user)).
                map(bytes -> ResponseEntity.ok().cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES)).body(bytes)).
                orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/images/token/{token}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImageByToken(@PathVariable String token) {
        return imageRetrievingService.getImageDataByToken(token).
                map(bytes -> ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.MINUTES)).body(bytes)).
                orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/images/thumb/token/{token}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getThumbnailByToken(@PathVariable String token) {
        return imageRetrievingService.getThumbnailDataByToken(token).
                map(bytes -> ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.MINUTES)).body(bytes)).
                orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/userinfo")
    public UserPageDTO homePage(Principal principal) {
        return userInfoService.createUserPageDTO(principal);
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void errorHandling(Exception e) {
        System.err.println("Returning HTTP 400 Bad Request  " + e);
    }


}

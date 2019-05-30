package com.example.demo;

import com.example.demo.model.Image;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
public class UploadService {

    private final ThumbnailService thumbnailService;
    private final UserRepository userRepository;

    @Autowired
    public UploadService(ThumbnailService thumbnailService, UserRepository userRepository) {
        this.thumbnailService = thumbnailService;
        this.userRepository = userRepository;
    }

    public void saveNewImage(MultipartFile file, Principal principal, String title) {
        BufferedImage sourceImage = ImageBytesUtil.multipartToImage(file);
        BufferedImage thumbnail = thumbnailService.makeThumbnail(sourceImage);

        User user = userRepository.findByUsername(principal.getName());
        List<Image> images = user.getImages();
        String imageToken =MD5Util.md5(LocalDate.now()+title);
        images.add(new Image(imageToken,title, LocalDate.now(),sourceImage, thumbnail, "jpg"));
        user.setImages(images);
        userRepository.save(user);
    }

}

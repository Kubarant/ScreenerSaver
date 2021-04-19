package com.example.demo.register;

import com.example.demo.MD5Util;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final ActivationEmailSender emailService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(UserRepository userRepository, ActivationEmailSender emailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerWithoutEmailActivation(UserRegisterDTO userRegisterDTO){
        User user = prepareNewUser(userRegisterDTO);
        user.setActivated(true);
        userRepository.save(user);
    }

    public void register( UserRegisterDTO userRegisterDTO){
        User user = prepareNewUser(userRegisterDTO);
        emailService.sendActivationMessage(user);
        userRepository.save(user);

    }

    private User prepareNewUser(UserRegisterDTO userRegisterDTO) {
        LocalDate now = LocalDate.now();
        String userToken = MD5Util.md5(now.toString() + userRegisterDTO.getUsername());
        String passwordHash = passwordEncoder.encode(userRegisterDTO.getPassword());
        return new User(userRegisterDTO.getUsername(),passwordHash, now, userToken, userRegisterDTO.getEmail());
    }




}

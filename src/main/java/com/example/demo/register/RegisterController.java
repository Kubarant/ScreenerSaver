package com.example.demo.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registering(@Valid UserRegisterDTO userRegisterDTO, Errors errors) {
        System.out.println(errors);
        if (errors.hasErrors())
            return errors.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .reduce((s, s2) -> s + ";" + s2)
                    .map(error -> "redirect:/register?error=" + error)
                    .orElse("redirect:/register?error");


        registerService.register(userRegisterDTO);
        return "login";
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void errorHandling(Exception e) {
        System.err.println("Returning HTTP 400 Bad Request  " + e);
    }


}

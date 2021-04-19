package com.example.demo.register;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegisterDTO {
    @NotEmpty(message = "Login is required")
    @Size(min = 3, max = 25, message = "Login must contain minimum 3 characters")
    private final String username;
    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 25, message = "Password must contain minimum 6 characters")
    private final String password;
    @Email(message = "Email has wrong format")
    @NotEmpty(message = "Email is required")
    private final String email;

    public UserRegisterDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

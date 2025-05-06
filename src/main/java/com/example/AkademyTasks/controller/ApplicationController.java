package com.example.AkademyTasks.controller;

import com.example.AkademyTasks.dto.AuthenticationRequest;
import com.example.AkademyTasks.dto.JwtResponse;
import com.example.AkademyTasks.dto.RefreshTokenRequest;
import com.example.AkademyTasks.service.AuthService;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {

    private final AuthService authService;


    public ApplicationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public JwtResponse authentication(@RequestBody AuthenticationRequest authenticationRequest) {
        return authService.authenticate(authenticationRequest);
    }

    @PostMapping("/refresh_token")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @GetMapping("/welcome/user")
    public String welcomeUser() {
        return "Привет пользователь.";
    }
    @GetMapping("/welcome/moderator")
    public String welcomeModerator() {
        return "Привет модер.";
    }

    @GetMapping("/welcome/admin")
    public String welcomeAdmin() {
        return "Привет админ.";
    }

    @PutMapping("/unlock_account/{username}")
    public void unlockAccount(@PathVariable String username) {
        authService.unlockAccount(username);
    }
}

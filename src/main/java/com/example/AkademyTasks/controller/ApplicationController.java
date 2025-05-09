package com.example.AkademyTasks.controller;

import com.example.AkademyTasks.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApplicationController {

    private final UserService auth2UserService;


    public ApplicationController(UserService auth2UserService) {
        this.auth2UserService = auth2UserService;
    }

    @GetMapping("/home")
    public String user(@AuthenticationPrincipal OAuth2User principal) {
        return auth2UserService.checkUser(principal);
    }

    @GetMapping("/home/admin")
    public String homeAdmin() {
        return "Привет админ";
    }

    @GetMapping("/home/user")
    public String homeUser() {
        return "Привет пользователь";
    }



}

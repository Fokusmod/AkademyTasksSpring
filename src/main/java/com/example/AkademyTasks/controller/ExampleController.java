package com.example.AkademyTasks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {


    @GetMapping("/home")
    public String welcomeHome() {
        return "hello home page";
    }

    @GetMapping("/another")
    public String welcomeAnother() {
        return "hello another page";
    }



}

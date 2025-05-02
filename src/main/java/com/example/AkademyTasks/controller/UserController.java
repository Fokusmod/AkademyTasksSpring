package com.example.AkademyTasks.controller;

import com.example.AkademyTasks.exception.BadRequestException;
import com.example.AkademyTasks.model.User;
import com.example.AkademyTasks.service.UserService;

import com.example.AkademyTasks.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all_users_info")
    @JsonView(Views.UserSummary.class)
    public List<User> getSimpleInfoAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/details")
    @JsonView(Views.UserDetails.class)
    public User getCurrentUserFullDetailsInfo(@RequestParam @NotBlank String name) {
        return userService.getCurrentUser(name);
    }

    @PostMapping("/users")
    public void createNewUser(@RequestParam @NotBlank String name, @RequestParam @NotBlank @Email String email) {
        userService.createUser(name, email);
    }

    @PutMapping("/users")
    public void updateUserInfo(@RequestParam @NotBlank String name, @RequestParam @NotBlank String newName) {
        userService.updateUser(name, newName);
    }

    @DeleteMapping("/users")
    public void deleteCurrentUser(@RequestParam @NotBlank String name) {
        userService.deleteUser(name);
    }


}

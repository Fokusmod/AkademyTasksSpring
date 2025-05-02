package com.example.AkademyTasks.service;

import com.example.AkademyTasks.exception.BadRequestException;
import com.example.AkademyTasks.exception.NotFoundException;
import com.example.AkademyTasks.model.User;
import com.example.AkademyTasks.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getCurrentUser(String name) {
        Optional<User> user = userRepository.findByName(name);
        return user.orElseThrow(() -> new NotFoundException("Пользователь не найден."));
    }


    public void createUser(String name, String email) {
        Optional<User> exist = userRepository.findByName(name);
        if (exist.isPresent()) {
            throw new BadRequestException("Такой пользователь уже существует");
        } else {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            userRepository.save(user);
        }
    }

    public void deleteUser(String name) {
        userRepository.deleteUserByName(name);
    }

    public void updateUser(String name, String newName) {
        User user = getCurrentUser(name);
        user.setName(newName);
    }
}

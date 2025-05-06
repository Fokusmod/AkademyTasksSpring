package com.example.AkademyTasks.service;


import com.example.AkademyTasks.exception.NotFoundException;
import com.example.AkademyTasks.model.User;
import com.example.AkademyTasks.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NotFoundException("Пользователь: " + username + "не найден."));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                user.isAccountNonLocked(),
                List.of(new SimpleGrantedAuthority(user.getRole())));
    }

    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NotFoundException("Пользователь: " + username + "не найден."));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }


}

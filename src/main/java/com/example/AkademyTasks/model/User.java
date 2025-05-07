package com.example.AkademyTasks.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class User implements OAuth2User {

    private String name;
    private String email;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;



    @Override
    public String getName() {
        return "";
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}

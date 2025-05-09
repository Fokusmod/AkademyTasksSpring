package com.example.AkademyTasks.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    public String checkUser(OAuth2User principal) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("USER: ").append(principal.getName());
        stringBuilder.append("LOGIN: ").append((String) principal.getAttribute("login")).append("\n");
        stringBuilder.append("EMAIL: ").append((String) principal.getAttribute("email")).append("\n");
        return stringBuilder.toString();
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        Collection<GrantedAuthority> authorities = new ArrayList<>(oAuth2User.getAuthorities());
        String nameAttribute = checkProvider(oAuth2User);

        if (oAuth2User.getAttribute(nameAttribute).equals("Fokusmod")) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("USER"));
        }
        String provider = nameAttribute.equals("name") ? "Google" : "GitHub";
        logger.info("Пользователь " + oAuth2User.getAttribute(nameAttribute) + " вошёл в систему через " + provider);
        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), nameAttribute);
    }

    public String checkProvider(OAuth2User oAuth2User) {
        if (oAuth2User.getAttribute("login") != null) {
            return "login";
        } else {
            return "name";
        }
    }
}

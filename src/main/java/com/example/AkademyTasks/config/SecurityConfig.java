package com.example.AkademyTasks.config;

import com.example.AkademyTasks.handler.AuthErrorHandler;
import com.example.AkademyTasks.handler.LogoutHandler;
import com.example.AkademyTasks.handler.OAuth2DeniedHandler;
import com.example.AkademyTasks.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;

    private final LogoutHandler logoutHandler;

    private final OAuth2DeniedHandler auth2DeniedHandler;

    private final AuthErrorHandler authErrorHandler;


    public SecurityConfig(UserService userService, LogoutHandler logoutHandler, OAuth2DeniedHandler auth2DeniedHandler, AuthErrorHandler authErrorHandler) {
        this.userService = userService;
        this.logoutHandler = logoutHandler;
        this.auth2DeniedHandler = auth2DeniedHandler;
        this.authErrorHandler = authErrorHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/home/admin").hasAuthority("ADMIN")
                        .requestMatchers("/home/user").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers("/home/another").hasAnyAuthority("ANOTHER")
                        .anyRequest().authenticated())
                .exceptionHandling(exceptionHandler-> exceptionHandler
                        .accessDeniedHandler(auth2DeniedHandler)
                        .authenticationEntryPoint(authErrorHandler))
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/home", true)
                        .userInfoEndpoint(service-> service.userService(userService)))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(logoutHandler)
                        .deleteCookies("JSESSIONID"));
        return httpSecurity.build();
    }
}
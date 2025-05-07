package com.example.AkademyTasks.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthErrorHandler implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthErrorHandler.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        if (authException.getCause() instanceof JwtException) {
            String errorMessage = "Данные токена некоректны или более недействительны.";
            logger.info("Данные токена некоректны или более недействительны.");
            response.getWriter().write(errorMessage);
        } else {
            String errorMessage = "Ваши учётные данные неверны.";
            logger.info("Авторизация не пройдена. Введённые данные оказались неверными");
            response.getWriter().write(errorMessage);
        }
    }
}

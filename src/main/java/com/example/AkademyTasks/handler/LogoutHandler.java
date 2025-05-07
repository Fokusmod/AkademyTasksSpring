package com.example.AkademyTasks.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LogoutHandler implements LogoutSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(LogoutHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            logger.info("Пользователь {} вышел из системы", username);
        }
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            response.sendRedirect(request.getContextPath() + "/home");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

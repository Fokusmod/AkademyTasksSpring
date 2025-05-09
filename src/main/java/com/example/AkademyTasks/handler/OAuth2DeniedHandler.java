package com.example.AkademyTasks.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2DeniedHandler implements AccessDeniedHandler {

    private final Logger logger = LoggerFactory.getLogger(OAuth2DeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        String errorMessage = "Недостаточно прав для доступа к этому эндпоинту";
        logger.info("Недостаточно прав для доступа к этому эндпоинту");
        response.getWriter().write(errorMessage);
    }
}


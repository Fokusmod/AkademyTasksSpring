package com.example.AkademyTasks.filter;


import com.example.AkademyTasks.exception.UnauthorizedException;
import com.example.AkademyTasks.service.LoggingService;
import com.example.AkademyTasks.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;


import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtProvider;

    private final LoggingService loggingService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;


    public JwtAuthenticationFilter(JwtUtils jwtProvider, LoggingService loggingService) {
        this.jwtProvider = jwtProvider;
        this.loggingService = loggingService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                username = jwtProvider.getUsername(jwt);
            } catch (ExpiredJwtException e) {
                loggingService.logInfo("Токен не прошёл проверку. Время жизни токена вышло.");
                resolver.resolveException(request, response, null,
                        new UnauthorizedException("Токен доступа более не является валидным. Получите новый токен доступа."));
                return;

            } catch (SignatureException e) {
                loggingService.logInfo("Токен не прошёл проверку. Сигнатура не совпадает.");
                resolver.resolveException(request, response, null,
                        new UnavailableException("Токен доступа был изменён. Получите новый токен доступа."));
                return;
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username, null, jwtProvider.getRoles(jwt)
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList())
            );
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            securityContext.setAuthentication(token);
            SecurityContextHolder.setContext(securityContext);

        }
        filterChain.doFilter(request, response);
    }
}


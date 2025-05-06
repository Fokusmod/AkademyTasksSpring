package com.example.AkademyTasks.service;

import com.example.AkademyTasks.dto.AuthenticationRequest;
import com.example.AkademyTasks.dto.JwtResponse;
import com.example.AkademyTasks.dto.RefreshTokenRequest;
import com.example.AkademyTasks.exception.AccountBannedException;
import com.example.AkademyTasks.model.User;
import com.example.AkademyTasks.utils.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    private final LoggingService loggingService;

    private final LoginAttemptService loginAttemptService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public AuthService(UserService userService, LoggingService loggingService, LoginAttemptService loginAttemptService,
                       AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.loggingService = loggingService;
        this.loginAttemptService = loginAttemptService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public JwtResponse authenticate(AuthenticationRequest authenticationRequest) {
        tryAuthenticateUser(authenticationRequest);
        UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        String refreshToken = jwtUtils.generateRefreshToken(userDetails);
        return new JwtResponse(token, refreshToken);
    }


    @Transactional
    public void tryAuthenticateUser(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            tryLockAccount(authenticationRequest);
        } catch (LockedException e) {
            loggingService.logInfo("Аккаунт пользователя +  " + authenticationRequest.getUsername() + " заблокирован");
            throw new AccountBannedException("Аккаунт был заблокирован. Обратитесь к администратору для решения проблемы.");
        }
        loginAttemptService.loginSucceeded(authenticationRequest.getUsername());
        loggingService.logInfo("Успешная аутентификация пользователя: " + authenticationRequest.getUsername());
    }

    public void tryLockAccount(AuthenticationRequest authenticationRequest) {
        if (loginAttemptService.isBlocked(authenticationRequest.getUsername())) {
            loggingService.logInfo("Аккаунт пользователя " + authenticationRequest.getUsername() +
                                   " заблокирован. Дальнейшая авторизация невозможна.");
            throw new AccountBannedException("Аккаунт был заблокирован. Обратитесь к администратору для решения " +
                                             "проблемы.");
        } else if (loginAttemptService.tryCount(authenticationRequest.getUsername()) == 1) {
            loginAttemptService.loginFailed(authenticationRequest.getUsername());
            User user = userService.getUserByUsername(authenticationRequest.getUsername());
            user.setAccountNonLocked(false);
            userService.saveUser(user);
            loggingService.logInfo("Была использована последняя попытка авторизоваться. Аккаунт пользователя "
                                   + authenticationRequest.getUsername() + " заблокирован.");
            throw new AccountBannedException("Авторизация не пройдена. Аккаунт был заблокирован. Обратитесь к администратору для решения " +
                                             "проблемы.");
        }
        loginAttemptService.loginFailed(authenticationRequest.getUsername());
        int count = loginAttemptService.tryCount(authenticationRequest.getUsername());
        loggingService.logInfo("Пароль оказался неверным. Осталось " + count + " попыток для пользователя "
                               + authenticationRequest.getUsername());
        throw new com.example.AkademyTasks.exception.BadCredentialsException(
                "Неверно введён пароль, проверьте правильность ввода. Осталось попыток: " + count);
    }

    public JwtResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        UserDetails userDetails = userService.loadUserByUsername(refreshTokenRequest.getUsername());
        boolean isValid = jwtUtils.checkTokens(refreshTokenRequest.getRefreshToken());
        if (isValid) {
            loggingService.logInfo("Успешное обновление токена доступа.");
            return new JwtResponse(jwtUtils.generateToken(userDetails), jwtUtils.generateRefreshToken(userDetails));
        }
        loggingService.logInfo("Был предоставлен некорректный решреш токен. Операция отменена.");
        throw new com.example.AkademyTasks.exception.BadCredentialsException("Отправлен некорректный рефреш токен");
    }

    public void unlockAccount(String username) {
        User user = userService.getUserByUsername(username);
        user.setAccountNonLocked(true);
        userService.saveUser(user);
        loggingService.logInfo("Пользователь " + username + " был разблокирован администратором.");
    }
}

package com.example.AkademyTasks.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginAttemptService {

    private final ConcurrentHashMap<String, Integer> attemptsCache = new ConcurrentHashMap<>();
    private final int MAX_ATTEMPTS = 5;

    public void loginFailed(String username) {
        attemptsCache.merge(username, 1, Integer::sum);
    }

    public void loginSucceeded(String username) {
        attemptsCache.remove(username);
    }

    public boolean isBlocked(String username) {
        return attemptsCache.getOrDefault(username, 0) >= MAX_ATTEMPTS;
    }

    public int tryCount (String username) {
        return MAX_ATTEMPTS - attemptsCache.getOrDefault(username,0);
    }
}

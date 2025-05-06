package com.example.AkademyTasks.dto;

import java.util.Objects;

public class RefreshTokenRequest {

    private String username;

    private String refreshToken;

    public RefreshTokenRequest(String username, String refreshToken) {
        this.username = username;
        this.refreshToken = refreshToken;
    }

    public RefreshTokenRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RefreshTokenRequest that = (RefreshTokenRequest) o;
        return Objects.equals(username, that.username) && Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, refreshToken);
    }


    @Override
    public String toString() {
        return "RefreshTokenRequest{" +
               "username='" + username + '\'' +
               ", refreshToken='" + refreshToken + '\'' +
               '}';
    }
}

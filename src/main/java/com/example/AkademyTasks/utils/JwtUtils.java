package com.example.AkademyTasks.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${jwt.secret.accessSecret}")
    private String jwtAccessSecret;

    @Value("${jwt.secret.accessSecret.lifetime}")
    private Duration jwtAccessLifetime;

    @Value("${jwt.secret.refreshSecret}")
    private String jwtRefreshSecret;

    @Value("${jwt.secret.refreshSecret.lifetime}")
    private Duration jwtRefreshLifetime;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        claims.put("roles", rolesList);
        ZoneId zone = ZoneId.of("Europe/Moscow");
        LocalDateTime now = LocalDateTime.now(zone);
        LocalDateTime expiredDate = now.plusSeconds(jwtAccessLifetime.getSeconds());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(now.atZone(zone).toInstant()))
                .setExpiration(Date.from(expiredDate.atZone(zone).toInstant()))
                .signWith(Keys.hmacShaKeyFor(jwtAccessSecret.getBytes()))
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        claims.put("roles", rolesList);
        ZoneId zone = ZoneId.of("Europe/Moscow");
        LocalDateTime now = LocalDateTime.now(zone);
        LocalDateTime expiredDate = now.plusSeconds(jwtRefreshLifetime.getSeconds());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(now.atZone(zone).toInstant()))
                .setExpiration(Date.from(expiredDate.atZone(zone).toInstant()))
                .signWith(Keys.hmacShaKeyFor(jwtRefreshSecret.getBytes()))
                .compact();
    }

    public boolean checkTokens(String refreshToken) {
        try {
            Claims refreshClaims = getAllClaimsFromRefreshToken(refreshToken);
        } catch (SignatureException | ExpiredJwtException e) {
            return false;
        }
        return true;
    }

    public Date getExpirationDate(String token) {
        return getAllClaimsFromToken(token).get("exp", Date.class);
    }

    public Claims getAllClaimsFromRefreshToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtRefreshSecret.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims getAllClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtAccessSecret.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }
}

package com.ind.SmartExpenseTracker.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key;
    private final long expMillis;

    public JwtUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration-in-minutes}") long expMinutes) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expMillis = expMinutes * 60_000;
    }

    public String generateToken(String userId, String email) {
        Date now = new Date(); Date exp = new Date(now.getTime() + expMillis);
        return Jwts.builder().setSubject(userId).claim("email", email)
                .setIssuedAt(now).setExpiration(exp).signWith(key).compact();
    }

    public Claims parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}

package com.mangobazar.security;

import com.mangobazar.service.CurrentUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.User;

import java.util.Date;

public final class TokenHandler {
    private final long TOKEN_VALIDITY = 600000;

    private final String encryptionKey;
    private final CurrentUserDetailsService currentUserDetailsService;

    public TokenHandler(String secret, CurrentUserDetailsService userDetailsService) {
        encryptionKey = secret;
        currentUserDetailsService = userDetailsService;
    }

    public User parseUserFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(encryptionKey)
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        return currentUserDetailsService.loadUserByUsername(username);
    }

    public String createTokenForUser(User user) {
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + TOKEN_VALIDITY;
        Date issuedAt = new Date(nowMillis);
        Date expireAt = new Date(expMillis);


        return Jwts.builder()
                .setIssuedAt(issuedAt)
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, encryptionKey)
                .setExpiration(expireAt)
                .compact();
    }
}

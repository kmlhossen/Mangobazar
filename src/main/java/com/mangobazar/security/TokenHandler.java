package com.mangobazar.security;

import com.mangobazar.service.CurrentUserDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.User;

public final class TokenHandler {

    private final String encryptionKey;
    private final CurrentUserDetailsService currentUserDetailsService;

    public TokenHandler(String secret, CurrentUserDetailsService userDetailsService) {
        encryptionKey = secret;
        currentUserDetailsService = userDetailsService;
    }

    public User parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(encryptionKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return currentUserDetailsService.loadUserByUsername(username);
    }

    public String createTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, encryptionKey)
                .compact();
    }
}

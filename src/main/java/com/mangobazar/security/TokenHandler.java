package com.mangobazar.security;

import com.mangobazar.service.CurrentUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.stream.Collectors;

public final class TokenHandler {
    private final long TOKEN_VALIDITY = 1800000;

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

        User user =  currentUserDetailsService.loadUserByUsername(claims.getSubject());

        //user logged out before, using this token
        if(currentUserDetailsService.getLastLogOutTime() != null &&
                currentUserDetailsService.getLastLogOutTime().compareTo(claims.getIssuedAt()) > 0){
            throw new JwtException("JWT expired, please login again.");
        }

        return  user;
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
                .claim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                        Collectors.joining(",")))
                .compact();
    }
}

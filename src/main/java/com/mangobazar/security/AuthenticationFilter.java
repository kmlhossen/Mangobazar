package com.mangobazar.security;


import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Extends GenericFilterBean to filter JWT token and do authentication
 */
public class AuthenticationFilter extends GenericFilterBean {
    private final TokenAuthenticationService authenticationService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public AuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
        authenticationService = tokenAuthenticationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Authentication authentication = null;

        //TODO Need to check any better way exist or not.
        try {
            authentication = authenticationService.getAuthentication(httpRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        } catch (JwtException | UsernameNotFoundException ex) {
            //TODO will fix when supporting mobile client.
            //UserNotFoundException Only happen here if token exist in one session,
            //but user delete account from another session.
            SecurityContextHolder.clearContext();
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            // modify the response code and message
            httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), ex.getCause() == null ? ex.getMessage() :
                    ex.getCause().getMessage());
            log.error(ex.getMessage(), ex);
        }
    }
}

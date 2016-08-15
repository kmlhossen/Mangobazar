package com.mangobazar.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mangobazar.service.CurrentUserDetailsService;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private final CurrentUserDetailsService currentUserDetailsService;
    private final TokenAuthenticationService tokenAuthenticationService;
   

    public SpringSecurityConfig() {
        super(true);
        currentUserDetailsService = new CurrentUserDetailsService();
        tokenAuthenticationService = new TokenAuthenticationService("passMangOpass", currentUserDetailsService);
       
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new AuthenticationFilter(tokenAuthenticationService),UsernamePasswordAuthenticationFilter.class).exceptionHandling().and()
                .anonymous().and()
                .servletApi().and()
                .headers().cacheControl();                // TODO need to block admin panel root url, later
                
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public CurrentUserDetailsService userDetailsService() {
        return currentUserDetailsService;
    }
    
   

    @Bean
    public TokenAuthenticationService tokenAuthenticationService() {
        return tokenAuthenticationService;
    }
   
}

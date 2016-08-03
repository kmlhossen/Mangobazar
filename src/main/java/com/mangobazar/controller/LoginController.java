package com.mangobazar.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mangobazar.security.TokenAuthenticationService;
import com.mangobazar.security.UserAuthentication;
import com.mangobazar.service.CurrentUserDetailsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mangobazar.model.SystemUser;
import com.mangobazar.service.SystemUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class
LoginController {

    private final SystemUserService systemUserService;
    private CurrentUserDetailsService currentUserDetailsService;
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    public LoginController(SystemUserService service, CurrentUserDetailsService userDetailsService,
                           TokenAuthenticationService tokenService) {
        systemUserService = service;
        currentUserDetailsService = userDetailsService;
        tokenAuthenticationService = tokenService;
    }

    @ApiOperation(value = "LogIn", notes = "Login user with username and password")
    @RequestMapping (method =  RequestMethod.POST)
    public String login(@RequestBody ObjectNode logInMap, HttpServletResponse response) {
        User user;
        user = currentUserDetailsService.loadUserByUsername(logInMap.get("userName").asText());

        UserAuthentication authentication = new UserAuthentication(user);
        return tokenAuthenticationService.addAuthentication(response, authentication);

    }
}

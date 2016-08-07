package com.mangobazar.controller;

import com.mangobazar.dto.LoginDto;
import com.mangobazar.exception.InvalidLogInException;
import com.mangobazar.security.TokenAuthenticationService;
import com.mangobazar.security.UserAuthentication;
import com.mangobazar.service.CurrentUserDetailsService;
import com.mangobazar.service.SystemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(value = "Login", description = "Login functionality")
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
    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestBody LoginDto loginDto, HttpServletResponse response) throws Exception {
        User user = currentUserDetailsService.loadUserByUsername(loginDto.getEmail());

        if (!systemUserService.checkPassword(user.getPassword(), loginDto.getPassword().trim())) {
            throw new InvalidLogInException();
        }


        UserAuthentication authentication = new UserAuthentication(user);
        return tokenAuthenticationService.addAuthentication(response, authentication);

    }

    //TODO need to get rid of hard coded role
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_SUPPORT_USER')")
    @ApiOperation(value = "Log out current user", notes = "Log out current user")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void logOut() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        systemUserService.updateLogOutTime(userName);
    }
}

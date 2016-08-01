package com.mangobazar.controller;


import com.mangobazar.model.SystemUser;
import com.mangobazar.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final SystemUserService systemUserService;

    @Autowired
    public UserController(SystemUserService service) {
        systemUserService = service;
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping (method =  RequestMethod.POST)
    public void createUser(@RequestBody SystemUser systemUser) {
        systemUserService.createUser(systemUser);
    }


}
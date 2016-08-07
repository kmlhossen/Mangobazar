package com.mangobazar.controller;


import com.mangobazar.dto.SystemUserDto;
import com.mangobazar.exception.DuplicateEntryException;
import com.mangobazar.service.SystemUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class SystemUserController {

    private final SystemUserService systemUserService;

    @Autowired
    public SystemUserController(SystemUserService service) {
        systemUserService = service;
    }

    @ApiOperation(value = "Create a new user", notes = "Create user with default role")
    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody SystemUserDto systemUserDto) throws DuplicateEntryException {
        systemUserService.createUser(systemUserDto);
    }
}
package com.mangobazar.service;

import com.mangobazar.dto.SystemUserDto;
import com.mangobazar.exception.DuplicateEntryException;
import com.mangobazar.model.SystemUser;

public interface SystemUserService {
    SystemUser createUser(SystemUserDto systemUserDto) throws DuplicateEntryException;

    SystemUser getUserByEmail(String email);

    boolean checkPassword(String userPassword, String givenPassword);

    void updateLogOutTime(String userName);
}

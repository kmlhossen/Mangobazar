package com.mangobazar.service;

import com.mangobazar.exception.DuplicateUserException;
import com.mangobazar.model.SystemUser;

public interface SystemUserService {
    SystemUser createUser(SystemUser systemUser) throws DuplicateUserException;
    SystemUser getUserByEmail(String email);
}

package com.mangobazar.service;

import com.mangobazar.model.SystemUser;

public interface SystemUserService {
    SystemUser createUser(SystemUser systemUser);
    SystemUser getUserByEmail(String email);
}

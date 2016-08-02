package com.mangobazar.service;


import com.mangobazar.model.SystemUser;
import com.mangobazar.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SystemUserServiceImpl implements SystemUserService {

    static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SystemUserRepository systemUserRepository;

    @Autowired
    public SystemUserServiceImpl(SystemUserRepository repository) {
        systemUserRepository = repository;
    }

    @Override
    public SystemUser getUserByEmail(String email) {
        return systemUserRepository.findOneByEmail(email);
    }

    @Transactional
    @Override
    public SystemUser createUser(SystemUser userObject){
        userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
        return systemUserRepository.saveAndFlush(userObject);
    }
}

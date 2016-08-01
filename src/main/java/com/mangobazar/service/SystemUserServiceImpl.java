package com.mangobazar.service;


import com.mangobazar.model.SystemUser;
import com.mangobazar.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SystemUserServiceImpl implements SystemUserService {

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
    public SystemUser createUser(SystemUser systemUser){
        return systemUserRepository.saveAndFlush(systemUser);
    }
}

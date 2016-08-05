package com.mangobazar.service;


import com.mangobazar.dto.SystemUserDto;
import com.mangobazar.exception.DuplicateEntryException;
import com.mangobazar.model.SystemUser;
import com.mangobazar.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service
@Transactional
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

    @Override
    public boolean checkPassword(String userPassword, String givenPassword) {
        if(userPassword != null && givenPassword != null){
                return passwordEncoder.matches(givenPassword, userPassword);
        }
        return false;
    }

    @Override
    public SystemUser createUser(SystemUserDto systemUserDto) throws DuplicateEntryException {

        if(systemUserRepository.findOneByEmail(systemUserDto.getEmail()) != null){
            throw new DuplicateEntryException("User name already exist");
        }

        SystemUser systemUser = new SystemUser();
        systemUser.setEmail(systemUserDto.getEmail());
        systemUser.setEmail(systemUserDto.getEmail());

        // encrypt the password
        systemUser.setPassword(passwordEncoder.encode(systemUserDto.getPassword().trim()));

        systemUser.setFirstName(systemUserDto.getFirstName());
        systemUser.setLastName(systemUserDto.getLastName());
        systemUser.setAddress(systemUserDto.getAddress());
        systemUser.setContactNo(systemUserDto.getContactNo());


        return systemUserRepository.saveAndFlush(systemUser);
    }

    @Override
    public void updateLogOutTime(String userName) {
        SystemUser systemUser = getUserByEmail(userName);
        if(systemUser != null){
            systemUser.setLastLogOut(new Date(System.currentTimeMillis()));
        }
    }
}

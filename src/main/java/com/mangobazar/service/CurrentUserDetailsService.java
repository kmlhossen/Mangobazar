package com.mangobazar.service;

import com.mangobazar.model.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    private SystemUserService systemUserService;
    static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {

        SystemUser systemUser = systemUserService.getUserByEmail(email);

        //check if this user with this username exist, if not, throw an exception
        // and stop the login process
        if (systemUser == null) {
            throw new UsernameNotFoundException("User details not found with this email: " +
                    systemUser.getEmail());
        }

        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        User user = new User(systemUser.getEmail(), systemUser.getPasswordHash(), authList);

        return user;
    }
}

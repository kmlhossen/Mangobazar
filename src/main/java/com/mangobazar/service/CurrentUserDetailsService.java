package com.mangobazar.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mangobazar.model.Privilege;
import com.mangobazar.model.UserRole;
import com.mangobazar.model.SystemUser;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private Date lastLogOutTime = null;
    @Autowired
    private SystemUserService systemUserService;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = null;
        SystemUser systemUser = systemUserService.getUserByEmail(email);

        //check if this user with this username exist, if not, throw an exception
        // and stop the login process
        if (systemUser == null) {
            throw new UsernameNotFoundException("No user found");
        }

        lastLogOutTime = systemUser.getLastLogOut();

        // add all the found roles.
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        for (UserRole userRole : systemUser.getRoles()) {
        	for (Privilege privilege: userRole.getPrivileges())
             authList.add(new SimpleGrantedAuthority(privilege.getName()));
        }

        user = new User(systemUser.getEmail(), systemUser.getPassword(), authList);

        
        return user;
    }

    public Date getLastLogOutTime() {
        return lastLogOutTime;
    }
}

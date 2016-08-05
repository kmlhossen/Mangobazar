package com.mangobazar.service;

import com.mangobazar.model.SystemUser;
import com.mangobazar.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private Date lastLogOutTime = null;
    @Autowired
    private SystemUserService systemUserService;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {

        SystemUser systemUser = systemUserService.getUserByEmail(email);

        //check if this user with this username exist, if not, throw an exception
        // and stop the login process
        if (systemUser == null) {
            throw new UsernameNotFoundException("User details not found for this email: " +
                    email);
        }

        lastLogOutTime = systemUser.getLastLogOut();

        // add all the found roles.
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        for(UserRole userRole : systemUser.getRoles()){
            authList.add(new SimpleGrantedAuthority(userRole.name()));
        }



        User user = new User(systemUser.getEmail(), systemUser.getPassword(), authList);

        return user;
    }

    public Date getLastLogOutTime(){
        return lastLogOutTime;
    }
}

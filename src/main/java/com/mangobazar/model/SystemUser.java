package com.mangobazar.model;

import com.mangobazar.util.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    
    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "user_role", nullable = false)
    private Set<Role> roles = new HashSet<>();
    @Enumerated(EnumType.STRING)
    public Set<Role> getDays() {
        return roles;
    }


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}

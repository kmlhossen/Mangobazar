package com.mangobazar.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Represent Privilege table in the database.
 */
@Entity
@Table(name = "Privilege")
public class Privilege {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Set<UserRole> roles;
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

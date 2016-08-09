package com.mangobazar.model;

import javax.persistence.*;

/**
 * Represent category table in the database.
 */
@Entity
@Table(name = "Category")
public class Category {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

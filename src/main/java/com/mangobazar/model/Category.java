package com.mangobazar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.mangobazar.views.CategoryViews;
import com.mangobazar.views.ProductViews;

/**
 * Represent category table in the database.
 */
@Entity
@Table(name = "Category")
public class Category {

	@JsonView(ProductViews.Create.class)
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonView({ProductViews.View.class,CategoryViews.Create.class})
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

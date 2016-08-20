package com.mangobazar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;
import com.mangobazar.views.ProductViews;

import io.swagger.annotations.ApiModel;


/**
 * Represent category table in the database.
 */
@ApiModel("Product")
@Entity
public class Product {
	  
	@JsonView(ProductViews.View.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;

	@JsonView(ProductViews.Create.class)
	@Column(name = "Name", nullable = false, unique = true)
	private String name;
    
	@JsonView(ProductViews.Create.class)
    @OneToOne()
 	@JoinColumn(name= "CategoryId" )
    private Category category;
    
	public Category getCategory() {
		return category;
	}

	public Long getId(){
		return id;
	}

	public String getName(){
		return name;
	}
	
}

package com.mangobazar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import io.swagger.annotations.ApiModel;


/**
 * Represent category table in the database.
 */
@ApiModel("Product")
@Entity
public class Product {
	Product(){

	}

  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Name", nullable = false, unique = true)
	private String name;
    
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

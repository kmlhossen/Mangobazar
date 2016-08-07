package com.mangobazar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Represent category table in the database.
 */
@ApiModel("Product")
@Entity
public class Product {
	Product(){

	}

    @ApiModelProperty(value = "id of the Product", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @ApiModelProperty(value = "name of the Product", required = true)
	@Column
	private String name;
    
    @ApiModelProperty(value = "category of the Product", required = true)
 	
    @OneToOne(fetch=FetchType.EAGER)
 	@JoinColumn(name= "category_id" )
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

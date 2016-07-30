package com.mangobazar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represent category table in the database.
 */
@ApiModel("Category")
@Entity
public class Category {
	Category(){

	}

    @ApiModelProperty(value = "id of the category", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @ApiModelProperty(value = "name of the category", required = true)
	@Column
	private String name;

	public Long getId(){
		return id;
	}

	public String getName(){
		return name;
	}
	
}

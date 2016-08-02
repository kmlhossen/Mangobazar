package com.mangobazar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Represent category table in the database.
 */
@ApiModel("Category")
@Entity
@Table(name="Category")
public class Category {

	Category(){
	}

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @ApiModelProperty(value = "name of the category", required = true)
	@Column(name = "Name")
	private String name;

	public Long getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

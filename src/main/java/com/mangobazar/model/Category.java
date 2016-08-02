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
	private Long m_Id;

    @ApiModelProperty(value = "name of the category", required = true)
	@Column(name = "Name")
	private String m_Name;

	public Long getId(){
		return m_Id;
	}

	public String getName(){
		return m_Name;
	}

	public void setName(String name) {
		m_Name = name;
	}
}

package com.mangobazar.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

/**
 * Represent category table in the database.
 */
@ApiModel("Product")
@Entity
@Table(name = "Product")
public class Product {
	Product() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Name", nullable = false, unique = true)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CategoryId")
	private Category category;

	@OneToMany(mappedBy = "product")
	private Set<ProductType> productTypeSet;

	public Set<ProductType> getProductType() {
		return productTypeSet;
	}

	public void setProductType(Set<ProductType> productType) {
		this.productTypeSet = productType;
	}

	public Category getCategory() {
		return category;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}

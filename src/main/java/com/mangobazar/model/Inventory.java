package com.mangobazar.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Inventory")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Inventory {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public double getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(double otherCost) {
		this.otherCost = otherCost;
	}

	public Long getId() {
		return id;
	}

	@Column(name = "Amount", columnDefinition="Decimal(18,4)", nullable = false)
	private double amount;
	
	@Column(name = "BuyingPrice", columnDefinition="Decimal(18,4)", nullable = false)
	private double buyingPrice;

	@Column(name = "OtherCost", columnDefinition="Decimal(18,4)", nullable = false)
	private double otherCost;
	
	@Column(name = "BuyingDate", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date startTime;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
}

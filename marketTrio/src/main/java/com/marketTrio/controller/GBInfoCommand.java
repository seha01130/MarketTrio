package com.marketTrio.controller;

import java.sql.Date;
import java.util.ArrayList;

import com.marketTrio.domain.OptionEntity;

public class GBInfoCommand {
	private ArrayList<OptionEntity> options;
	private String productName;
	private String duration;
	private int allQuantity;
	private double regularPrice;
	private double discountRate;
	private String content;
	private Date createDate;
	private String image;
	
	public GBInfoCommand(ArrayList<OptionEntity> options, String productName, String duration, int allQuantity, double regularPrice,
			double discountRate, String content, Date createDate, String image) {
		super();
		this.options = options;
		this.productName = productName;
		this.duration = duration;
		this.allQuantity = allQuantity;
		this.regularPrice = regularPrice;
		this.discountRate = discountRate;
		this.content = content;
		this.createDate = createDate;
		this.image = image;
	}

	public ArrayList<OptionEntity> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<OptionEntity> options) {
		this.options = options;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getAllQuantity() {
		return allQuantity;
	}

	public void setAllQuantity(int allQuantity) {
		this.allQuantity = allQuantity;
	}

	public double getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(double regularPrice) {
		this.regularPrice = regularPrice;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}

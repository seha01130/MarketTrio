package com.marketTrio.controller;

public class GBUpdateInfoCommand {
	private int GBpostId;
	private String productName;
	private String duration;
	private String content;
	
	public GBUpdateInfoCommand(int gBpostId, String productName, String duration, String content) {
		super();
		GBpostId = gBpostId;
		this.productName = productName;
		this.duration = duration;
		this.content = content;
	}

	public int getGBpostId() {
		return GBpostId;
	}

	public void setGBpostId(int gBpostId) {
		GBpostId = gBpostId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

package com.marketTrio.controller;

import java.util.Date;

public class SHListCommand {
    private int SHPostId;
    private String image;
    private String title;
    private Date createDate;
    private int price;
    private int reviewStatus;  //이게 secondHandEntity에는 없는데 command에서는 필요함. 리스트에 보여줄때 후기 작성 여부를 판별해서
    							//그에 따른 후기작성하기 버튼을 보여줘야함.
    
    public SHListCommand() {
		super();
	}

	public SHListCommand(int sHPostId, String image, String title, Date createDate, int price, int reviewStatus) {
		super();
		SHPostId = sHPostId;
		this.image = image;
		this.title = title;
		this.createDate = createDate;
		this.price = price;
		this.reviewStatus = reviewStatus;
	}

	public int getSHPostId() {
		return SHPostId;
	}
	public void setSHPostId(int sHPostId) {
		SHPostId = sHPostId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(int reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
}


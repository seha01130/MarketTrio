package com.marketTrio.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class AuctionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int auctionPostId;
	private String sellerId;
	private int buyerId;
	private int maxPrice;
	private String name;
	private int startPrice;
	private String detailInfo;
	private Date deadline;
	private String picture;
	private int auctionStatus;
//    @OneToMany
//    @JoinColumn(name="memeberId") ??
//    private List<AParticipant> participants;

	public int getAuctionPostId() {
		return auctionPostId;
	}

	public void setAuctionPostId(int auctionPostId) {
		this.auctionPostId = auctionPostId;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}

	public String getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getAuctionStatus() {
		return auctionStatus;
	}

	public void setAuctionStatus(int auctionStatus) {
		this.auctionStatus = auctionStatus;
	}
	/*
	 * public List<AParticipant> getParticipants() { return participants; }
	 * 
	 * public void setParticipants(List<AParticipant> participants) {
	 * this.participants = participants; }
	 */

}

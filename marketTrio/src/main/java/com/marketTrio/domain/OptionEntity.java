package com.marketTrio.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
@Table(name="Opt")
@Embeddable
public class OptionEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int optionId;
	
	@ManyToOne	// 공구 글 : 옵션 1:n 관계
	@JoinColumn(name="GBPostId", nullable=false)
	private GBEntity GBPost;
	
	private String optionName;
	private int quantity;
	private int remainingQuantity;
	
	public OptionEntity() {
		super();
	}
		
	public OptionEntity(int optionId, GBEntity gBPost, String optionName, int quantity, int remainingQuantity) {
		super();
		this.optionId = optionId;
		GBPost = gBPost;
		this.optionName = optionName;
		this.quantity = quantity;
		this.remainingQuantity = remainingQuantity;
	}

	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public GBEntity getGBPost() {
		return GBPost;
	}

	public void setGBPost(GBEntity gBPost) {
		GBPost = gBPost;
	}

	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getRemainingQuantity() {
		return remainingQuantity;
	}
	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}
	
	
}
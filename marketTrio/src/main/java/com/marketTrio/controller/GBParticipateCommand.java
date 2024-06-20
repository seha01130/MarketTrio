package com.marketTrio.controller;

import com.marketTrio.domain.GBEntity;
import com.marketTrio.domain.Member;
import com.marketTrio.domain.OptionEntity;

public class GBParticipateCommand {
	private String member;
	private int GBPost;
	private int optionId;
	private int quantity;
	
	public GBParticipateCommand() {
		
	}
	
	public GBParticipateCommand(String member, int gbPost, int option, int quantity) {
		super();
		this.member = member;
		this.GBPost = gbPost;
		this.optionId = option;
		this.quantity = quantity;
	}

	public int getGBPostId() {
		return GBPost;
	}

	public void setGBPostId(int gBPost) {
		GBPost = gBPost;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "GBParticipateCommand [member=" + member + ", GBPost=" + GBPost + ", optionId=" + optionId
				+ ", quantity=" + quantity + "]";
	}

}

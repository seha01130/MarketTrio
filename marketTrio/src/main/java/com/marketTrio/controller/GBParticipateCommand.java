package com.marketTrio.controller;

import com.marketTrio.domain.GBEntity;
import com.marketTrio.domain.Member;
import com.marketTrio.domain.OptionEntity;

public class GBParticipateCommand {
	private Member member;
	private GBEntity GBPost;
	private OptionEntity option;
	private int quantity;
	
	public GBParticipateCommand(Member member, GBEntity gbPost, OptionEntity option, int quantity) {
		super();
		this.member = member;
		this.GBPost = gbPost;
		this.option = option;
		this.quantity = quantity;
	}

	public GBEntity getGBPost() {
		return GBPost;
	}

	public void setGBPost(GBEntity gBPost) {
		GBPost = gBPost;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public OptionEntity getOption() {
		return option;
	}

	public void setOption(OptionEntity option) {
		this.option = option;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

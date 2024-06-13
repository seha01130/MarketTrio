package com.marketTrio.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="GBParticipant")
public class GBParticipantEntity {
	@Id
	@OneToOne(cascade = CascadeType.ALL)	//공구 참여자 : 멤버 단방향 1:1
    @PrimaryKeyJoinColumn(name = "memberId")
    private Member member;
		
	@OneToMany
	@JoinColumn(name="GBPostId")
	private GBEntity GBPost;
	
	@OneToOne(cascade=CascadeType.ALL) //공구 참여자 : 옵션 단방향 1:1
	@PrimaryKeyJoinColumn(name="optionId")
	private OptionEntity myOption;
	private int myQuantity;
	
	public GBParticipantEntity() {
		super();
	}
	
	public GBParticipantEntity(Member member, GBEntity gBPost, OptionEntity myOption, int myQuantity) {
		super();
		this.member = member;
		this.GBPost = gBPost;
		this.myOption = myOption;
		this.myQuantity = myQuantity;
	}

	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public GBEntity getGBPost() {
		return GBPost;
	}
	public void setGBPost(GBEntity gBPost) {
		GBPost = gBPost;
	}
	public OptionEntity getMyOption() {
		return myOption;
	}
	public void setMyOption(OptionEntity myOption) {
		this.myOption = myOption;
	}
	public int getMyQuantity() {
		return myQuantity;
	}
	public void setMyQuantity(int myQuantity) {
		this.myQuantity = myQuantity;
	}
	
}

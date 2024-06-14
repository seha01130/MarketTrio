package com.marketTrio.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="GBParticipant")
public class GBParticipantEntity {
	@Id
	private String memberId;
	
	@OneToOne(cascade = CascadeType.ALL)	//공구 참여자 : 멤버 단방향 1:1
	@MapsId
    @PrimaryKeyJoinColumn(name = "memberId")
    private Member member;
	
	@OneToOne(cascade=CascadeType.ALL) //공구 참여자 : 옵션 단방향 1:1
	@PrimaryKeyJoinColumn(name="optionId")
	private OptionEntity myOption;
	
	@ManyToOne
    @JoinColumn(name = "GBPostId")
    private GBEntity gbEntity;
	
	private int myQuantity;
	
	public GBParticipantEntity() {
		super();
	}
	
	public GBParticipantEntity(Member member, OptionEntity myOption, GBEntity gbEntity, int myQuantity) {
        super();
        this.member = member;
        this.myOption = myOption;
        this.gbEntity = gbEntity;
        this.myQuantity = myQuantity;
    }

	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public OptionEntity getMyOption() {
		return myOption;
	}
	public void setMyOption(OptionEntity myOption) {
		this.myOption = myOption;
	}
	public GBEntity getGbEntity() {
        return gbEntity;
    }

    public void setGbEntity(GBEntity gbEntity) {
        this.gbEntity = gbEntity;
    }
	public int getMyQuantity() {
		return myQuantity;
	}
	public void setMyQuantity(int myQuantity) {
		this.myQuantity = myQuantity;
	}
	
	
	//////////수연 추가
	// 추가된 getter
	public Integer getGBPostId() {
        return this.gbEntity != null ? this.gbEntity.getGBPostId() : null;
    }
	
}
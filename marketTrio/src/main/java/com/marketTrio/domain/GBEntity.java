package com.marketTrio.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name="GroupBuy")
public class GBEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int GBPostId;
    
	@ManyToOne //공구 글 : 멤버, 단방향 N:1
	@JoinColumn(name="memberId")
	private Member member;
	
	@OneToMany(cascade = CascadeType.ALL) //공구 글 : 옵션, 단방향 1:n
	@JoinColumn(name = "GBPostId") // 외래 키 설정
	private List<OptionEntity> options;
	
	@OneToMany(cascade = CascadeType.ALL)	  // 공구 글 : 참여자, 단방향 1:n
	@JoinColumn(name = "GBPostId") // 외래 키 설정
	private List<GBParticipantEntity> participants;
	
	private String productName;
	private String duration;
	private int allQuantity;
	private double regularPrice;
	@Transient	// 판매가는 db 저장 X
	private String salePrice;
	private double discountRate;
	private String content;
	@Temporal(TemporalType.DATE)	//날짜 타입 필드
	private Date createDate;
	private String image;
	private int GBStatus;
	
	public GBEntity() {
		super();
	}
	
	public GBEntity(int gBPostId, Member member, List<OptionEntity> options,
			List<GBParticipantEntity> participants, String productName, String duration, int allQuantity,
			double regularPrice, double discountRate, String content, Date createDate, String image, int gBStatus) {
		super();
		GBPostId = gBPostId;
		this.member = member;
		this.options = options;
		this.participants = participants;
		this.productName = productName;
		this.duration = duration;
		this.allQuantity = allQuantity;
		this.regularPrice = regularPrice;
		this.discountRate = discountRate;
		this.content = content;
		this.createDate = createDate;
		this.image = image;
		GBStatus = gBStatus;
	}

	public int getGBPostId() {
		return GBPostId;
	}
	
	public void setGBPostId(int gBPostId) {
		GBPostId = gBPostId;
	}
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<OptionEntity> getOptions() {
		return options;
	}
	public void setOptions(List<OptionEntity> options) {
		this.options = options;
	}
	public List<GBParticipantEntity> getParticipants() {
		return participants;
	}
	public void setParticipants(List<GBParticipantEntity> participants) {
		this.participants = participants;
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
	public int getGBStatus() {
		return GBStatus;
	}
	public void setGBStatus(int gBStatus) {
		GBStatus = gBStatus;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
}

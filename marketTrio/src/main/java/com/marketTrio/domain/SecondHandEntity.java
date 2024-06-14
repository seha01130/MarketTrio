package com.marketTrio.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="SecondHand")
public class SecondHandEntity implements Serializable{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int SHPostId;
   @Column(nullable = false)
   private String sellerId;
//    private String buyerId;
   @Column(nullable = false)
   private int SHStatus;
   @Column(nullable = false)
   private Date createDate;
   @Column(nullable = false)
   private String title;
   @Column(nullable = false)
   private int price;
   private String image;
   @Column(nullable = false)
   private String content;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "buyerId", referencedColumnName="id", nullable = false)
   private Member member;

   @OneToOne (cascade = CascadeType.ALL)
   @PrimaryKeyJoinColumn(name="SHPostId")
   private ReviewEntity review;

   public SecondHandEntity() {
      super();
   }

   public SecondHandEntity(int sHPostId, String sellerId, Member member, int sHStatus, Date createDate, String title,
         int price, String image, String content) {
      super();
      SHPostId = sHPostId;
      this.sellerId = sellerId;
      this.member = member;
      SHStatus = sHStatus;
      this.createDate = createDate;
      this.title = title;
      this.price = price;
      this.image = image;
      this.content = content;
   }

   public int getSHPostId() {
      return SHPostId;
   }

   public void setSHPostId(int sHPostId) {
      SHPostId = sHPostId;
   }

   public String getSellerId() {
      return sellerId;
   }

   public void setSellerId(String sellerId) {
      this.sellerId = sellerId;
   }

   public Member getMember() {
      return member;
   }

   public void setMember(Member member) {
      this.member = member;
   }

   public int getSHStatus() {
      return SHStatus;
   }

   public void setSHStatus(int sHStatus) {
      SHStatus = sHStatus;
   }

   public Date getCreateDate() {
      return createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }
}
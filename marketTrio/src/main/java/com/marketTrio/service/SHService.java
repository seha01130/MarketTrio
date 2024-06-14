package com.marketTrio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marketTrio.controller.SHListCommand;
import com.marketTrio.domain.SecondHandEntity;
import com.marketTrio.repository.SHListRepository;

import java.util.Date;
import java.util.List;

@Service
public class SHService {
    @Autowired
    private SHListRepository SHListRepository;
    @Autowired
    private ReviewService reviewService; // 주입

    //@Transactional(readOnly = true)
    @SuppressWarnings("null")
	public List<SHListCommand> getSHPurchaseListByMemberId(String memberId) {
    	List<SecondHandEntity> purchaseList = SHListRepository.findByMember_Id(memberId);
    	List<SHListCommand> purchaseListCommand = null;
    	
    	for (SecondHandEntity s : purchaseList) {
    		SHListCommand c = new SHListCommand();
    		c.setSHPostId(s.getSHPostId());
    		c.setImage(s.getImage());
    		c.setTitle(s.getTitle());
    		c.setCreateDate(s.getCreateDate());
    		c.setPrice(s.getPrice());
    		c.setReviewStatus(reviewService.hasReviewed(memberId, s.getSHPostId()) ? 1 : 0);
    		purchaseListCommand.add(c);
    	}
        return purchaseListCommand;
    }

    //@Transactional(readOnly = true)
    @SuppressWarnings("null")
	public List<SHListCommand> getSHSalesListByMemberId(String memberId) {
    	List<SecondHandEntity> salesList =  SHListRepository.findBySellerId(memberId);
    	List<SHListCommand> salesListCommand = null;
    	
    	for (SecondHandEntity s : salesList) {
    		SHListCommand c = new SHListCommand();
    		c.setSHPostId(s.getSHPostId());
    		c.setImage(s.getImage());
    		c.setTitle(s.getTitle());
    		c.setCreateDate(s.getCreateDate());
    		c.setPrice(s.getPrice());
    		c.setReviewStatus(reviewService.hasReviewed(memberId, s.getSHPostId()) ? 1 : 0);
    		salesListCommand.add(c);
    	}
        return salesListCommand;
    }
    
    public SecondHandEntity getSHByPostId(int postId) {
    	//postId로 SecondHand 객체 가져와서 객체를 리턴. 중고거래 코드 사용하면 됨
    	return null;
    }

    public SecondHandEntity getSHPostByPostId(int SHPostId) {
        //postId로 SecondHand 객체 가져와서 객체를 리턴. 중고거래 코드 사용하면 됨
//       Optional<SecondHandEntity> sh = SHPostRepository.findById(SHPostId);
//       //       SecondHandEntity shPost = sh.orElseThrow(() -> new IllegalArgumentException("Post does not exist!!!"));
//       SecondHandEntity shPost = sh.get();
//
//       return shPost;
    	return null;
     }
}

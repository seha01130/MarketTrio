package com.marketTrio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marketTrio.controller.SHListCommand;
import com.marketTrio.domain.SecondHandEntity;
import com.marketTrio.repository.SHListRepository;

import java.util.ArrayList;
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
    	List<SecondHandEntity> purchaseList = SHListRepository.findByBuyerId(memberId);
    	List<SHListCommand> purchaseListCommand = new ArrayList<>();
    	
    	for (SecondHandEntity s : purchaseList) {
    		SHListCommand c = new SHListCommand();
    		
    		c.setSHPostId(s.getSHPostId());
    		c.setImage(s.getImage());
    		c.setTitle(s.getTitle());
    		c.setCreateDate(s.getCreateDate());
    		c.setPrice(s.getPrice());
    		c.setBuyerId(s.getBuyerId());
    		c.setSellerId(s.getMember().getId());//seller의 Id 구하기. SecondHandEntity에 sellerId는 member의 fk이기떄문에 OneToOne으로 매핑되어있음. 
    		c.setReviewStatus(reviewService.hasReviewed(memberId, s.getSHPostId()) ? 1 : 0);
    		purchaseListCommand.add(c);
    	}
        return purchaseListCommand;
    }
    
    ///////////////여기까지 완료//////////////////////////////////

    //@Transactional(readOnly = true)
    @SuppressWarnings("null")
	public List<SHListCommand> getSHSalesListByMemberId(String memberId) {
    	List<SecondHandEntity> salesList =  SHListRepository.findByMember_Id(memberId);
    	List<SHListCommand> salesListCommand = new ArrayList<>();
    	
    	for (SecondHandEntity s : salesList) {
    		SHListCommand c = new SHListCommand();
    		
    		c.setSHPostId(s.getSHPostId());
    		c.setImage(s.getImage());
    		c.setTitle(s.getTitle());
    		c.setCreateDate(s.getCreateDate());
    		c.setPrice(s.getPrice());
    		c.setBuyerId(s.getBuyerId());
    		c.setSellerId(s.getMember().getId());
    		c.setReviewStatus(reviewService.hasReviewed(memberId, s.getSHPostId()) ? 1 : 0);
    		salesListCommand.add(c);
    	}
        return salesListCommand;
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

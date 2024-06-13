package com.marketTrio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marketTrio.controller.SHListCommand;
import com.marketTrio.domain.AuctionEntity;
import com.marketTrio.domain.SecondHandEntity;
import com.marketTrio.repository.AListRepository;

import java.util.Date;
import java.util.List;

@Service
public class AService {
    @Autowired
    private AListRepository AListRepository;

    //@Transactional(readOnly = true)
    public List<AuctionEntity> getAPurchaseListByMemberId(String memberId) {
    	List<AuctionEntity> purchaseList = AListRepository.findByBuyerId(memberId);
//    	List<AListCommand> purchaseListCommand = null;
    	
//    	for (AuctionEntity s : purchaseList) {
//    		AListCommand c = new AListCommand();
//    		//아래 알맞게 수정
//    		c.setSHPostId(s.getSHPostId());
//    		c.setImage(s.getImage());
//    		c.setTitle(s.getTitle());
//    		c.setCreateDate(s.getCreateDate());
//    		c.setPrice(s.getPrice());
//    		purchaseListCommand.add(c);
//    	}
        return purchaseList;
    }

    //@Transactional(readOnly = true)
    public List<AuctionEntity> getASalesListByMemberId(String memberId) {
    	List<AuctionEntity> salesList =  AListRepository.findBySellerId(memberId);
//    	List<AListCommand> salesListCommand = null;
    	
//    	for (AuctionEntity s : salesList) {
//    		AListCommand c = new AListCommand();
//    		//아래 알맞게 수정
//    		c.setSHPostId(s.getSHPostId());
//    		c.setImage(s.getImage());
//    		c.setTitle(s.getTitle());
//    		c.setCreateDate(s.getCreateDate());
//    		c.setPrice(s.getPrice());
//    		salesListCommand.add(c);
//    	}
        return salesList;
    }
}

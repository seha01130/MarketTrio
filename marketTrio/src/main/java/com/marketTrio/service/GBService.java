package com.marketTrio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marketTrio.controller.SHListCommand;
import com.marketTrio.domain.GBEntity;
import com.marketTrio.domain.SecondHandEntity;
import com.marketTrio.repository.GBListRepository;

import java.util.Date;
import java.util.List;

@Service
public class GBService {
    @Autowired
    private GBListRepository GBListRepository;

    //@Transactional(readOnly = true)
    public List<GBEntity> getGBPurchaseListByMemberId(String memberId) {
    	List<GBEntity> purchaseList = GBListRepository.findByBuyerId(memberId);
//    	List<GBListCommand> purchaseListCommand = null;
    	
//    	for (GBEntity s : purchaseList) {
//    		GBListCommand c = new GBListCommand();
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
    public List<GBEntity> getGBSalesListByMemberId(String memberId) {
    	List<GBEntity> salesList =  GBListRepository.findBySellerId(memberId);
//    	List<GBListCommand> salesListCommand = null;
    	
//    	for (GBEntity s : salesList) {
//    		GBListCommand c = new GBListCommand();
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

package com.marketTrio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marketTrio.controller.SHListCommand;
import com.marketTrio.dao.jpa.JPAAuctionAndGroupBuyListDao;
import com.marketTrio.domain.AuctionEntity;
import com.marketTrio.domain.GBEntity;
import com.marketTrio.domain.GBParticipantEntity;
import com.marketTrio.domain.SecondHandEntity;
import com.marketTrio.domain.GBEntity;
import com.marketTrio.domain.GBParticipantEntity;
import com.marketTrio.repository.GBListRepository;
import com.marketTrio.repository.GroupBuyPartRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GBService {
    @Autowired
    private GBListRepository GBListRepository;
    @Autowired
    private JPAAuctionAndGroupBuyListDao jpaListDao;
//    @Autowired
//    private GBEntity gbEntity;
//    @Autowired
//    private GBParticipantEntity gbPartEntity;

//    @Transactional(readOnly = true)
    public List<GBEntity> getGBPurchaseListByMemberId(String memberId) {
    	List<Integer> purchaseListIds = jpaListDao.getGBPostIdList(memberId); //내가 참여한 공동구매의 게시글ID의 List를 가져오기
//    	
    	List<GBEntity> purchaseList = GBListRepository.findByGBPostIdIn(purchaseListIds );
    	
        return purchaseList;
    }
//    public List<GBEntity> getGBListByMemberId(String memberId) {
//        // GBParticipantEntity에서 해당 멤버의 리스트를 가져옴
//        List<GBParticipantEntity> participantEntities = gbPartRepository.findAllByMemberId(memberId);
//
//        // GBPostId 리스트를 추출함
//        List<Integer> gbPostIds = participantEntities.stream()
//                .map(GBParticipantEntity::getGBPostId)
//                .collect(Collectors.toList());
//
//        // GBPostId 리스트를 통해 GBEntity 리스트를 가져옴
//        return GBListRepository.findByGBPostIdIn(gbPostIds);
//    }

    //@Transactional(readOnly = true)
    public List<GBEntity> getGBSalesListByMemberId(String memberId) {
    	List<GBEntity> salesList =  GBListRepository.findByMemberId(memberId);
        return salesList;
    }
}

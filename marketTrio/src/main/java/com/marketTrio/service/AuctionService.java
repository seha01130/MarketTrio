package com.marketTrio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketTrio.dao.AuctionDao;
import com.marketTrio.domain.AuctionEntity;
import com.marketTrio.domain.AuctionForm;
import com.marketTrio.domain.Member;
import com.marketTrio.repository.AuctionRepository;

@Service
public class AuctionService {
	@Autowired
	private AuctionRepository auctionRepository;

	public List<AuctionEntity> getAuctionList(){
		return auctionRepository.findAll();
	}
	
	@Autowired
    private AuctionDao auctionDao;
	
	public AuctionEntity createAuction(AuctionForm postData) {
		return auctionDao.createAuction(postData);
	}

	public AuctionEntity getAuction(int auctionId) {
		// TODO Auto-generated method stub
		return auctionDao.getAuction(auctionId);
	}

	public AuctionEntity modifyAuction(int auctionId, AuctionForm formData) {
		// TODO Auto-generated method stub
		return auctionDao.modifyAuction(auctionId, formData);
	}
	
	public int deleteAuction(int auctionId) {
		// TODO Auto-generated method stub
		return auctionDao.deleteAuction(auctionId);
	}

	public Member getBidder(int auctionId) {
		// TODO Auto-generated method stub
		return auctionDao.getBidder(auctionId);
	}

	public int placeBid(int auctionId, int price) {
		return auctionDao.placeBid(auctionId,price);
	}
//  public List<Auction> getAuctionList() {
//      return auctionDao.getauctionList();
//  }
}

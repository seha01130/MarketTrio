package com.marketTrio.dao;

import com.marketTrio.domain.AuctionEntity;
import com.marketTrio.domain.AuctionForm;
import com.marketTrio.domain.Member;


public interface AuctionDao {
   //spring jpa 이용
	// List<Auction> getauctionList() throws DataAccessException;

	AuctionEntity createAuction(AuctionForm postData);

	AuctionEntity getAuction(int auctionId);

	AuctionEntity modifyAuction(int auctionId, AuctionForm formData);

	int deleteAuction(int auctionId);

	Member getBidder(int auctionId);

	int placeBid(int auctionId, int price);
}

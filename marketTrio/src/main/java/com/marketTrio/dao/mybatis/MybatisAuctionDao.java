package com.marketTrio.dao.mybatis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marketTrio.dao.AuctionDao;
import com.marketTrio.dao.mybatis.mapper.AuctionMapper;
import com.marketTrio.domain.AuctionEntity;
import com.marketTrio.domain.AuctionForm;
import com.marketTrio.domain.Member;

@Repository
public class MybatisAuctionDao implements AuctionDao {

	@Autowired
	private AuctionMapper auctionMapper;

//	@Override
//	public List<Auction> getauctionList() throws DataAccessException {
//		// TODO Auto-generated method stub
//		return auctionMapper.getauctionList();
//	}

	@Override
	public AuctionEntity createAuction(AuctionForm postData) {
		// TODO Auto-generated method stub
		return auctionMapper.createAuction(postData);
	}

	@Override
	public AuctionEntity getAuction(int auctionId) {
		// TODO Auto-generated method stub
		return auctionMapper.getAuction(auctionId);
	}

	@Override
	public AuctionEntity modifyAuction(int auctionId, AuctionForm formData) {
		// TODO Auto-generated method stub
		return auctionMapper.modifyAuction(auctionId, formData);
	}

	@Override
	public int deleteAuction(int auctionId) {
		// TODO Auto-generated method stub
		return auctionMapper.deleteAuction(auctionId);
	}

	@Override
	public Member getBidder(int auctionId) {
		// TODO Auto-generated method stub
		return auctionMapper.getBidder(auctionId);
	}

	@Override
	public int placeBid(int auctionId, int price) {
		// TODO Auto-generated method stub
		return auctionMapper.placeBid(auctionId, price);
	}
}

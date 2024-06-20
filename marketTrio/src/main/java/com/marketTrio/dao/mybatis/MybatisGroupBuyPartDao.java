package com.marketTrio.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.marketTrio.dao.mybatis.mapper.GBMapper;
import com.marketTrio.domain.GBParticipantEntity;

@Repository
public class MybatisGroupBuyPartDao {
	@Autowired
	private GBMapper gbMapper;
	
	public void participateGB(GBParticipantEntity GBParticipant) {
		gbMapper.updateOption(GBParticipant);
		gbMapper.insertGBParticipant(GBParticipant);
	} 
	
	public void participateCancelGB(GBParticipantEntity GBParticipant) {
		GBParticipantEntity gbp = new GBParticipantEntity();
		int myQuantity = -(GBParticipant.getMyQuantity());
		gbp.setMyQuantity(myQuantity);
		gbp.setMember(GBParticipant.getMember());
		gbp.setMyOption(GBParticipant.getMyOption());
		gbMapper.updateOption(gbp);
		gbMapper.deleteGBParticipant(GBParticipant);
	}
}

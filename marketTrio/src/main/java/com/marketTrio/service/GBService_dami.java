package com.marketTrio.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketTrio.controller.GBInfoCommand;
import com.marketTrio.controller.GBParticipateCommand;
import com.marketTrio.controller.GBUpdateInfoCommand;
import com.marketTrio.controller.MemberSession;
import com.marketTrio.dao.mybatis.MybatisGroupBuyPartDao;
import com.marketTrio.domain.GBParticipantEntity;
import com.marketTrio.domain.GBEntity;
import com.marketTrio.domain.Member;
import com.marketTrio.domain.OptionEntity;
import com.marketTrio.repository.GroupBuyPartRepository;
import com.marketTrio.repository.GroupBuyRepository;
import com.marketTrio.repository.OptionRepository;
import com.marketTrio.repository.GBListRepository;
import com.marketTrio.domain.GBEntity;
import com.marketTrio.repository.GBListRepository;

@Service("GBService")
public class GBService_dami {
	@Autowired
	private GroupBuyRepository gbRepository;
	@Autowired
	private GroupBuyPartRepository gbPartRepository;
	@Autowired
    private OptionRepository optionRepository;
    @Autowired
    private GBListRepository GBListRepository;
    
	public List<GBEntity> getGBPostList() {
		List<GBEntity> gbPostList = gbRepository.findAll();
		return gbPostList;
	}
	
	public GBEntity getGBPost(int gbPostId) {
		Optional<GBEntity> optionalgbPost = gbRepository.findById(gbPostId);
		GBEntity gbPost = optionalgbPost.get();
		
		return gbPost;
	}
	
	@Transactional
	public GBEntity createGBPost(GBInfoCommand gbInfo) {
//		HttpServletRequest request = null;
//		MemberSession memberSession = (MemberSession) request.getAttribute("memberId");
		GBEntity gbEntity = new GBEntity();
		//gbEntity.setMemberId(null);
		gbEntity.setOptions(gbInfo.getOptions());
		gbEntity.setProductName(gbInfo.getProductName());
		gbEntity.setAllQuantity(gbInfo.getAllQuantity());
		gbEntity.setRegularPrice(gbInfo.getRegularPrice());
		gbEntity.setDiscountRate(gbInfo.getDiscountRate());
		gbEntity.setContent(gbInfo.getContent());
		gbEntity.setCreateDate(gbInfo.getCreateDate());
		gbEntity.setImage(gbInfo.getImage());
		
		return gbRepository.save(gbEntity);
	}
	
	@Transactional
	public void deleteGBPost(GBEntity gbPost) {
		gbRepository.delete(gbPost);
	}
	
	// update - GET
	@Transactional
	public GBEntity getUpdateGBPost(int gbPostId) {
		Optional<GBEntity> optionalPost = gbRepository.findById(gbPostId);
		return optionalPost.orElse(null);
	}
	
	// update - POST
	@Transactional
	public GBEntity updateGBPost(GBUpdateInfoCommand gbUpdateInfo) {
		Optional<GBEntity> optionalPost = gbRepository.findById(gbUpdateInfo.getGBpostId());
		if (optionalPost.isPresent()) {
			GBEntity gbPost = optionalPost.get();
			gbPost.setProductName(gbUpdateInfo.getProductName());
			gbPost.setDuration(gbUpdateInfo.getDuration());
			gbPost.setContent(gbUpdateInfo.getContent());
		
			return gbRepository.save(gbPost);
		}
		
		return null;
	}
	
	public GBParticipantEntity getGBPart(String memberId) {
		GBParticipantEntity gbPart = gbPartRepository.findByMemberId(memberId);
		return gbPart;
	}
	
    @Transactional
	public void participate(GBParticipateCommand gbPartCommand) {
		Optional<OptionEntity> option = optionRepository.findById(gbPartCommand.getOption().getOptionId());
    	OptionEntity o = option.get();
		
    	if (o.getRemainingQuantity() < gbPartCommand.getQuantity()) {
    		 throw new IllegalArgumentException("잔여 수량보다 선택한 수량이 더 많아 선택할 수 없습니다.");
        }

    	GBParticipantEntity gbp = new GBParticipantEntity(gbPartCommand.getMember(), gbPartCommand.getGBPost(), gbPartCommand.getOption(), gbPartCommand.getQuantity());
		
    	o.setRemainingQuantity(o.getRemainingQuantity() - gbPartCommand.getQuantity());
        optionRepository.save(o);

        gbPartRepository.save(gbp);
	}
	
    @Transactional
	public void participateCancel(Member memberId) {
    	Optional<GBParticipantEntity> gbp = gbPartRepository.findById(memberId);
		OptionEntity option = gbp.get().getMyOption();
    	
		option.setRemainingQuantity(option.getRemainingQuantity() + gbp.get().getMyOption().getQuantity());
        optionRepository.save(option);
    	
        gbPartRepository.save(gbp.get());
	}
    
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
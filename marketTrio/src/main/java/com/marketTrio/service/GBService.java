package com.marketTrio.service;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketTrio.controller.GBInfoCommand;
import com.marketTrio.controller.GBParticipateCommand;
import com.marketTrio.controller.GBUpdateInfoCommand;
import com.marketTrio.controller.MemberSession;
import com.marketTrio.controller.OptionCommand;
import com.marketTrio.dao.mybatis.MyBatisMemberDao;
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
public class GBService {
	@Autowired
	private GroupBuyRepository gbRepository;
	@Autowired
	private GroupBuyPartRepository gbPartRepository;
	@Autowired
    private OptionRepository optionRepository;
    @Autowired
    private GBListRepository GBListRepository;
    @Autowired
    private MyBatisMemberDao memberDao;
    
    //@Transactional(readOnly = true)
//    public List<GBEntity> getGBPurchaseListByMemberId(String memberId) {
//    	List<GBEntity> purchaseList = GBListRepository.findByBuyerId(memberId);
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
//        return purchaseList;
//    }

    //@Transactional(readOnly = true)
//    public List<GBEntity> getGBSalesListByMemberId(String memberId) {
//    	List<GBEntity> salesList =  GBListRepository.findBySellerId(memberId);
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
//        return salesList;
//    }
    
    ////////////////////////////////////////////////////////////////
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
	public GBEntity createGBPost(GBInfoCommand gbInfo, String memberId, List<String> uploadedFileNames) throws IllegalStateException, IOException {
//		HttpServletRequest request = null;
//		MemberSession memberSession = (MemberSession) request.getAttribute("memberId");
		GBEntity gbEntity = new GBEntity();
		Member member = memberDao.getMember(memberId);
		
		gbEntity.setProductName(gbInfo.getProductName());
		gbEntity.setAllQuantity(gbInfo.getAllQuantity());
		gbEntity.setRegularPrice(gbInfo.getRegularPrice());
		gbEntity.setDiscountRate(gbInfo.getDiscountRate());
		gbEntity.setContent(gbInfo.getContent());
		gbEntity.setDuration(gbInfo.getDuration());
		gbEntity.setCreateDate(gbInfo.getCreateDate());
		gbEntity.setMember(member);
		gbEntity.setPictures(uploadedFileNames);
		
		return gbRepository.save(gbEntity);
	}
	
	//
	@Transactional
	public void deleteGBPost(GBEntity gbPost, List<OptionEntity> optList, List<GBParticipantEntity> gbpList) {
		// 자식 먼저 삭제 - 참여자 삭제
		gbPartRepository.deleteAll(gbpList);
		// 자식 먼저 삭제 - 옵션 삭제
		optionRepository.deleteAll(optList);
		// 공구 글 삭제
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
		Optional<GBEntity> optionalPost = gbRepository.findById(gbUpdateInfo.getId());
		if (optionalPost.isPresent()) {
			GBEntity gbPost = optionalPost.get();
			gbPost.setProductName(gbUpdateInfo.getProductName());
			gbPost.setDuration(gbUpdateInfo.getDuration());
			gbPost.setContent(gbUpdateInfo.getContent());
						
			calculateDday(gbPost);
			
			return gbRepository.save(gbPost);
		}
		
		return null;
	}
	
	// 옵션 생성
	public OptionEntity createOption(OptionEntity option) {
		OptionEntity optEntity = new OptionEntity(); 
		//gbEntity.setMemberId(null);
		optEntity.setOptionName(option.getOptionName());
		optEntity.setQuantity(option.getQuantity());
		optEntity.setGBEntity(option.getGBEntity());
		optEntity.setRemainingQuantity(option.getQuantity());	// insert 때는 수량 == 남은 양
		
		return optionRepository.save(optEntity);
	}
	
	public List<OptionEntity> findOptionList(int gbPostId) {
        // GBEntity 객체를 생성하여 gbPostId를 설정하고, 해당 객체로 OptionEntity를 조회합니다.
        Optional<GBEntity> gbEntity = gbRepository.findById(gbPostId);
        return optionRepository.findByGbEntity(gbEntity.get());
    }
	
	public GBParticipantEntity getGBPart(int gbPostid, String memberId) {
		GBParticipantEntity gbPart = gbPartRepository.findByGbEntity_GBPostIdAndMember_Id(gbPostid, memberId);
		
		return gbPart;
	}
	
	public List<GBParticipantEntity> findGBPaticipantsList(int gbPostId) {
		Optional<GBEntity> gbEntity = gbRepository.findById(gbPostId);
		
		return gbPartRepository.findByGbEntity(gbEntity.get());
	}
	
    @Transactional
	public void participate(GBParticipateCommand gbPartCommand) {
		Optional<OptionEntity> option = optionRepository.findById(gbPartCommand.getOptionId());
    	OptionEntity o = option.get();
    	
    	if (o.getRemainingQuantity() < gbPartCommand.getQuantity()) {
    		 throw new IllegalArgumentException("잔여 수량보다 선택한 수량이 더 많아 선택할 수 없습니다.");
        }

    	Optional<GBEntity> optionalgbPost = gbRepository.findById(gbPartCommand.getGBPostId());
		GBEntity gbPost = optionalgbPost.get();
		Member member = memberDao.getMember(gbPartCommand.getMember());
	
		GBParticipantEntity gbp = new GBParticipantEntity();
		gbp.setGbPost(gbPost);
		gbp.setMember(member);
		gbp.setMyOption(o);
		gbp.setMyQuantity(gbPartCommand.getQuantity());
		    
		gbPartRepository.save(gbp);
		
    	o.setRemainingQuantity(o.getRemainingQuantity() - gbPartCommand.getQuantity());
    	optionRepository.save(o);
    	System.out.println("!!!!!!!!!!!" + gbp);    
	}
	
    @Transactional
	public void participateCancel(int gbPostId, String memberId) {
    	GBParticipantEntity gbPart = gbPartRepository.findByGbEntity_GBPostIdAndMember_Id(gbPostId, memberId);
    	//Optional<GBParticipantEntity> gbp = gbPartRepository.findByMemberId(memberId);
		OptionEntity option = gbPart.getMyOption();

		option.setRemainingQuantity(option.getRemainingQuantity() + gbPart.getMyQuantity());
        optionRepository.save(option);
    	gbPart.setMyOption(null);		// 옵션 삭제 방지
    	
        gbPartRepository.delete(gbPart);
	}
    
    public String calculateDday(GBEntity gbPost) {
    	int status = gbPost.getGBStatus();
     
    	Instant now = Instant.now();

        // DB에 저장된 날짜를 Instant로 변환합니다.
        Date durationDate = gbPost.getDuration();
        Instant deadline = durationDate.toInstant();

        // 남은 일수를 계산합니다.
        long daysRemaining = ChronoUnit.DAYS.between(now, deadline);
            
        if (status == 0) {
            if (daysRemaining < 0) { // 공구 종료 case 1) 마감 기한을 넘긴 경우
            	gbPost.setGBStatus(1);
            	gbRepository.save(gbPost);
            }
        }
        else {
            if (daysRemaining > 0) { // 마감일 변경 등으로 공구 연장되어 다시 0으로 바꿈
            	gbPost.setGBStatus(0);
            	gbRepository.save(gbPost);
            }
        }
        
		if (status == 1) { // 공구가 종료된 경우
    		return "마감";
    	}
		else {
			return String.format("D-%d", daysRemaining);
		}
    }
}
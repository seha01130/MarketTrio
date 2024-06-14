package com.marketTrio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.marketTrio.dao.mybatis.MyBatisMemberDao;
import com.marketTrio.domain.Member;
import com.marketTrio.domain.ReviewEntity;
import com.marketTrio.domain.SecondHandEntity;
import com.marketTrio.service.AService;
import com.marketTrio.service.GBService;
import com.marketTrio.service.ReviewService;
import com.marketTrio.service.SHService;

@Controller
@RequestMapping("/sales")
public class MySalesListController {

//	@Autowired
//	private MarketTrioFacade marketTrio;
//	public void setMarketTrio(MarketTrioFacade marketTrio) {
//		this.marketTrio = marketTrio;
//	}
	@Autowired	
	private SHService sHService;
	@Autowired	
	private AService aService;
	@Autowired	
	private GBService gBService;
	@Autowired
    private ReviewService reviewService;
	@Autowired	
	private MyBatisMemberDao memberDao;
	
	//내가 판매한 리스트 보여주기
	@RequestMapping("/mySalesList")
	public ModelAndView handleRequest(
		@ModelAttribute("memberSession") MemberSession memberSession) throws Exception {
		String memberId = memberSession.getMemberId();
		 
		ModelAndView modelAndView = new ModelAndView("mySalesList");
	    modelAndView.addObject("SHSalesList", sHService.getSHSalesListByMemberId(memberId));
//	    modelAndView.addObject("ASalesList", AService.getASalesListById(memberId));
//	    modelAndView.addObject("GBSalesList", gBService.getGBSalesListById(memberId));
	    return modelAndView;
	}
	
	// 나의 판매 리스트에서 : 후기 작성 폼 페이지를 열기 위한 요청 처리
		@GetMapping("/giveRate/{SHPostId}")
	    @ResponseBody
	    public ReviewCommand openReviewForm(@PathVariable int SHPostId) {
	        SecondHandEntity sh = sHService.getSHPostByPostId(SHPostId);
	        
	        ReviewEntity review = new ReviewEntity();
	        review.setSHPostId(SHPostId);
	        review.setSenderId(sh.getSellerId());
	        review.setReceiverId(sh.getMember().getId());
	        
	        Member sender = memberDao.getMember(sh.getSellerId());
	        Member receiver = memberDao.getMember(sh.getMember().getId());
	        
	        String senderNickname = sender.getNickname();
	        String receiverNickname = receiver.getNickname();
	        
	        ReviewCommand reviewCommand =  new ReviewCommand(review, senderNickname, receiverNickname);
	        return reviewCommand;
	    }

	    // 후기 작성 처리를 위한 AJAX 요청 처리  
	    @PostMapping("/giveRate/{SHPostId}")
	    @ResponseBody
	    public ResponseEntity<String> submitReview(@RequestBody ReviewEntity review) {
	        reviewService.insertReview(review);
	        return ResponseEntity.ok("Review submitted successfully");
	    }
}

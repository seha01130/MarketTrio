package com.marketTrio.controller;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/purchase")
public class MyPurchaseListController {

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

	// 내가 구매한 리스트 보여주기
	@RequestMapping("/myPurchaseList")
	public ModelAndView handleRequest(@ModelAttribute("memberSession") MemberSession memberSession) throws Exception {
		String memberId = memberSession.getMemberId();

		ModelAndView modelAndView = new ModelAndView("myPurchaseList"); // myPurchaseList.jsp로 감
//		modelAndView.addObject("SHPurchaseList", sHService.getSHPurchaseListByMemberId(memberId));
//	    modelAndView.addObject("APurchaseList", aService.getAPurchaseListByMemberId(memberId));
//	    modelAndView.addObject("GBPurchaseList", gBService.getGBPurchaseListByMemberId(memberId));
		return modelAndView;
	}

	// 나의 구매 리스트에서 : 후기 작성 폼 페이지를 열기 위한 요청 처리
	@GetMapping("/giveRate/{SHPostId}")
	@ResponseBody
	public ReviewCommand openReviewForm(@PathVariable int SHPostId, HttpServletRequest request) {
		SecondHandEntity sh = sHService.getSHPostByPostId(SHPostId);
		ReviewEntity review = new ReviewEntity();
		review.setSHPostId(SHPostId);
		MemberSession memberSession = (MemberSession) request.getAttribute("memberId");
		String memberId = memberSession.getMemberId();
		Member member = memberDao.getMember(memberId);
//        review.setSenderId(sh.getBuyerId());
		review.setSenderId(memberId);
		review.setReceiverId(sh.getSellerId());

//        Member sender = memberDao.getMember(sh.getBuyerId());
		review.setSenderId(memberId);
		Member receiver = memberDao.getMember(sh.getSellerId());

		String senderNickname = member.getNickname();
		String receiverNickname = receiver.getNickname();

		ReviewCommand reviewCommand = new ReviewCommand(review, senderNickname, receiverNickname);
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

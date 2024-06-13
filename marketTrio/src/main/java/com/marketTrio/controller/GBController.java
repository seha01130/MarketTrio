package com.marketTrio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.marketTrio.domain.GBParticipantEntity;
import com.marketTrio.domain.GBEntity;
import com.marketTrio.domain.Member;
import com.marketTrio.service.GBService;

@Controller
@RequestMapping("/GroupBuy")
public class GBController {
	@Autowired
	private GBService gbService;
	
	// 공동구매 글 목록 보기
	@RequestMapping("/list")
	public ModelAndView getGBPostList() {
		ModelAndView mv = new ModelAndView("groupBuyList");	//  groupBuyList.jsp로 감
		mv.addObject("gbPostList", gbService.getGBPostList());
		
		return mv;
	}
	
//	public String getGBPostList(ModelMap model) {
//		List gbList = this.gbService.getGBPostList();
//		model.put("gbList", gbList);
//		return "groupBuyList";
//	}
	
	// 공동구매 글 상세 보기
	@RequestMapping("/detail/{GBPostId}")
	public ModelAndView getGBPost(
			@ModelAttribute("memberSession") MemberSession memberSession,
			@PathVariable("GBPostId") int GBPostId) {
		String loginUserId = memberSession.getMember().getId();
		
		GBEntity gbPost =  gbService.getGBPost(GBPostId);
		String gbPostAuthor = gbPost.getMember().getId();
		ModelAndView mv;
		
		if (loginUserId.equals(gbPostAuthor)) {	
			mv = new ModelAndView("gbSellerDetail");	//판매자 화면
		} else {
			mv = new ModelAndView("gbBuyerDetail");		//구매자 화면
		}
		
		mv.addObject("gbPost", gbPost);
		return mv;
	}
	
	// 공동구매 글 작성
	@PostMapping("/write")
	public ModelAndView writeGBPost(GBInfoCommand gbInfo) {
		ModelAndView mv = new ModelAndView();
		try {	// 작성 성공
			GBEntity gbPost = gbService.createGBPost(gbInfo);
			mv.setViewName("redirect:/GroupBuy/detail" + gbPost.getGBPostId());
		} catch (Exception e) {		// 작성 실패
			mv.setViewName("GroupBuy/groupBuyWriting");
			mv.addObject("errorMessage", "Fail to create the GBPost");
		}
		
		return mv;
	}
	
	
	// 공동구매 글 삭제
	@RequestMapping("/delete")
	public void deleteGBPost(@RequestParam("gbPostId") int gbPostId) {
		GBEntity gbPost = gbService.getGBPost(gbPostId);
		gbService.deleteGBPost(gbPost);
	}
	
	// 공동구매 글 수정 - GET 방식
	@GetMapping("/update")
	public ModelAndView Updateform(int gbPostId) {
		ModelAndView mv = new ModelAndView("groupBuyCorrection");	//groupBuyCorrection.jsp로 이동
		GBEntity gbPost = gbService.getGBPost(gbPostId);
		mv.addObject("gbPost", gbPost);
		
		return mv;
	}
	
	// 공동구매 글 수정 - POST 방식
	@PostMapping("/detail/{GBPostId}")
	public GBEntity updateGBPost(
			@RequestBody GBUpdateInfoCommand gbUpdateInfo,
			@PathVariable("GBPostId") int GBPostId) {
		GBEntity updatedPost = gbService.updateGBPost(gbUpdateInfo);
		
		return updatedPost;
	}
	
	// 공동구매 참여
	@PostMapping("/detail/{GBPostId}")
	public ModelAndView participateGB(
			@RequestBody GBParticipateCommand gbParticipate,
			@PathVariable("GBPostId") int GBPostId) {
		ModelAndView mv = new ModelAndView();
		try {	// 참여 성공
			gbService.participate(gbParticipate);
			mv.setViewName("redirect:/GroupBuy/detail" + GBPostId);
		} catch (Exception e) {		// 참여 실패
			mv.setViewName("GroupBuy/groupBuyParticipate");
			mv.addObject("errorMessage", "Fail to participate in GroupBuy");
		}
		
		return mv;
	}	
	
	// 공동구매 참여 취소
	@RequestMapping("/participateCancel")
	public String participateCancelGB(
			@ModelAttribute("memberSession") MemberSession memberSession,
			@PathVariable("GBPostId") int GBPostId) {
		Member loginUserId = memberSession.getMember();
	
		gbService.participateCancel(loginUserId);
		
		return "redirect:GroupBuyDetail";
	}
}

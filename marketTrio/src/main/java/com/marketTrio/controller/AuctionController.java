package com.marketTrio.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.marketTrio.domain.AuctionEntity;
import com.marketTrio.domain.AuctionForm;
import com.marketTrio.domain.Member;
import com.marketTrio.service.AuctionService;

@Controller
public class AuctionController {

	private AuctionService auctionService;

	@Autowired
	public void setAuction(AuctionService auction) {
		this.auctionService = auction;
	}

	// 경매글 작성
	@RequestMapping("/actions/create")
	public String register(AuctionForm postData, Model model) {
		AuctionEntity a = auctionService.createAuction(postData);
		model.addAttribute("sessionAuction", a);

		return "redirect:/posts/detail/" + a.getAuctionPostId();
	}

	// 경매글 조회
	@GetMapping("/auctions/{auctionId}/detail")
	public String viewAuction(HttpServletRequest request, @PathVariable("auctionId") int auctionId, Model model) {

		MemberSession memberSession = (MemberSession) request.getAttribute("memberId");
		String memberId = memberSession.getMemberId();

		AuctionEntity auction = auctionService.getAuction(auctionId);
		if (auction == null) {
			return "redirect:/auction/list";
		}
		model.addAttribute("auction", auction);

		Member member = auctionService.getBidder(auctionId);
		model.addAttribute("member", member);
		// 이거 auction.getparticipnts 로 할 수 있나?

		if (auction.getSellerId() == memberId) { // 판매자면
			model.addAttribute("isPartcipant", 0);
			return "auctionDetail";
		} else { // 구매자면
			model.addAttribute("isPartcipant", 1);
			return "auctionDetail";
		}
	}

	// 경매글 삭제
	@RequestMapping("/actions/{actionId}/delete")
	public String deleteAuction(@PathVariable("auctionId") int auctionId) {
		int check = auctionService.deleteAuction(auctionId);
		if (check == 0) {
			return "redirect:/auction/list";
		}
		return "redirect:/auction/list";
	}

	// 입찰하기
	@RequestMapping("/auctions/{auctionId}/bid")
	public String placeBid(@PathVariable("auctionId") int auctionId, @ModelAttribute int price, Model model) {
		// 가격비교
		// 입찰 insert
		int isbid = auctionService.placeBid(auctionId, price);
		if (isbid == 0) {
			return "redirect:/posts/detail/{auctionId}";
		} else {
			return "redirect:/posts/detail/{auctionId}";
		}
	}
}
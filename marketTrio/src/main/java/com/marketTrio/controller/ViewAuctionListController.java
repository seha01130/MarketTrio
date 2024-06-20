package com.marketTrio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.marketTrio.domain.AuctionEntity;

@Controller
@SessionAttributes("auctionList")
public class ViewAuctionListController {

	/*
	 * private AuctionFacade auction;
	 * 
	 * @Autowired public void setAuction(AuctionFacade auction) { this.auction =
	 * auction; }
	 */

	// auctionService를 이용한(주입필요) spring jpa로 변경한거.
//    @RequestMapping("/auction/list")
//    public String viewList(Model model) {
//    	List<Auction> auctionList = auctionService.getAuctionList();
//        PagedListHolder<Auction> auctionListPage = new PagedListHolder<Auction>(this.auction.getAuctionList());
//        auctionListPage.setPageSize(4);
//        model.addAttribute("auctionList", auctionListPage);
//        return "auctionList";
//    }

	@RequestMapping("/auction/list2")
	public String viewList2(@RequestParam("page") String page,
			@ModelAttribute("auctionList") PagedListHolder<AuctionEntity> auctionList, BindingResult result) {
		if (auctionList == null) {
			throw new IllegalStateException("Cannot find auction list");
		}
		if ("next".equals(page)) {
			auctionList.nextPage();
		} else if ("previous".equals(page)) {
			auctionList.previousPage();
		}
		return "auctionList";
	}

}

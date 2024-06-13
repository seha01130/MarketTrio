package com.marketTrio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.marketTrio.domain.AuctionEntity;
import com.marketTrio.domain.AuctionForm;
import com.marketTrio.service.AuctionService;

@Controller
//@SessionAttributes("sessionAuction")
public class AuctionModifyContoller {

	private AuctionService auctionService;
    
   
	@Autowired
    public void setAuction(AuctionService auction) {
        this.auctionService = auction;
    }
	
    //경매글수정 get요청
    @GetMapping("/actions/{actionId}/form")
    public String showModifyForm(@PathVariable("actionId") int actionId, Model model) {
        AuctionEntity a = auctionService.getAuction(actionId);
        if (a == null) {
            return "redirect:/auction/list";  // 경매글이 존재하지 않을 경우 목록 페이지로 리디렉션
        }
        model.addAttribute("auction", a);
        return "auctionDetail";
    }
    
    //경매글수정 post요청
    @PostMapping("/actions/{actionId}/form")
    public String modifyAuction(@PathVariable("actionId") int actionId, @ModelAttribute AuctionForm formData) {
        AuctionEntity modifiedAuction = auctionService.modifyAuction(actionId, formData);
        if (modifiedAuction == null) {
            return "redirect:/auction/list";  // 수정 실패 시 목록 페이지로 리디렉션
        }
        return "redirect:/posts/detail/" + actionId;
    }
}

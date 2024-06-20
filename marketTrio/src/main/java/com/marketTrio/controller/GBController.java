package com.marketTrio.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.marketTrio.domain.GBParticipantEntity;
import com.marketTrio.domain.GBEntity;
import com.marketTrio.domain.Member;
import com.marketTrio.domain.OptionEntity;
import com.marketTrio.service.GBService;

@Controller
@RequestMapping("/groupBuy")
public class GBController {
	@Autowired
	private GBService gbService;
	
	// 공동구매 글 목록 보기
	@RequestMapping("/list")
	public ModelAndView getGBPostList() {
		List<GBEntity> gbPostList = gbService.getGBPostList();
		System.out.println("gbPostList: " + gbPostList); 
		ModelAndView mv = new ModelAndView("thyme/groupBuy/groupBuyList");	//  groupBuyList.html로 감
		mv.addObject("gbPostList", gbService.getGBPostList());
		
		return mv;
	}
	
	// 공동구매 글 상세 보기
	@RequestMapping("/detail/{GBPostId}")
	public ModelAndView getGBPost(
			@ModelAttribute("memberSession") MemberSession memberSession,
			@PathVariable("GBPostId") int GBPostId) {
		//String loginUserId = memberSession.getMemberId();
		String loginUserId = "rkdms";
		
		GBEntity gbPost =  gbService.getGBPost(GBPostId);
		String gbPostAuthor = gbPost.getMember().getId();
		String dday = gbService.calculateDday(gbPost);
		double sp = gbPost.getRegularPrice() * (gbPost.getDiscountRate() / 100);
		int salePrice = gbPost.getRegularPrice() - (int) Math.floor(sp);
		List<OptionEntity> opList = gbService.findOptionList(GBPostId);
		
		ModelAndView mv;
		if (loginUserId.equals(gbPostAuthor)) {	
			mv = new ModelAndView("thyme/groupBuy/gbSellerDetail");	//판매자 화면
		} else {
			// 이미 참여했으면 gbBuyerDetail로, 참여 안 했으면 gbNoneBuyerDetail
			GBParticipantEntity gbPart = gbService.getGBPart(GBPostId, loginUserId);
			if (gbPart == null) {
				mv = new ModelAndView("thyme/groupBuy/gbNoneBuyerDetail");  //참여X 구매자 화면
				GBParticipateCommand gbp = new GBParticipateCommand();
				mv.addObject("gbpCommand", new GBParticipateCommand());
			} else {
				mv = new ModelAndView("thyme/groupBuy/gbBuyerDetail");  //참여O 구매자 화면
			}		
		}
		
		mv.addObject("gbPost", gbPost);
		mv.addObject("dday", dday);
		mv.addObject("salePrice", salePrice);
		mv.addObject("optionList", opList);
		
		return mv;
	}
	
	// 공동구매 글 작성 - GET 방식
	@GetMapping("/write")
	public ModelAndView writeform() {
		ModelAndView mv = new ModelAndView("thyme/groupBuy/groupBuyWrite");
		mv.addObject("gbInfoCommand", new GBInfoCommand());
		
		return mv;
	}
		
	// 공동구매 글 작성 - POST 방식
	@PostMapping("/writed")
	public String writeGBPost(
			@ModelAttribute("gbInfoCommand")GBInfoCommand gbInfo,
			@ModelAttribute("memberSession") MemberSession memberSession,
			@RequestParam("files") MultipartFile[] files) throws IOException, ServletException {
		//String loginUserId = memberSession.getMemberId();
		String loginUserId = "rkdms";
		String fileDir = "C:/absolute/path/to/upload/";

        // 디렉토리가 존재하지 않으면 생성
        File directory = new File(fileDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 여러 파일 업로드 처리
        List<String> uploadedFileNames = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                String uploadedFileName = file.getOriginalFilename();
                System.out.println("Processing file: " + uploadedFileName); // 추가된 디버그 메시지
                
                if (uploadedFileName != null && !uploadedFileName.isEmpty()) {
                    file.transferTo(new File(fileDir + uploadedFileName));
                    uploadedFileNames.add(uploadedFileName);
                }
            }
        }

        System.out.println("Uploaded File Names in Controller: " + uploadedFileNames);
        
		Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(now);
		
        try {
        	Date createDate = dateFormat.parse(formattedDate);
        	gbInfo.setCreateDate(createDate);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        GBEntity createdPost = gbService.createGBPost(gbInfo, loginUserId, uploadedFileNames);
		int postId = createdPost.getGBPostId();    
		
		List<OptionEntity> options = gbInfo.getOptions();
		
		for (OptionEntity option : options) {
			option.setGBEntity(createdPost);
			gbService.createOption(option);
        }
			
		return "redirect:/groupBuy/detail/" + postId;
	}	
	
	// 공동구매 글 삭제
	@RequestMapping("/delete")
	public String deleteGBPost(@RequestParam("gbPostId") int gbPostId) {
		GBEntity gbPost = gbService.getGBPost(gbPostId);
		List<OptionEntity> optList = gbService.findOptionList(gbPostId);
		List<GBParticipantEntity> gbpList = gbService.findGBPaticipantsList(gbPostId);
		System.out.println("!!!!!!!" + optList.toString());
		System.out.println("!!!!!!!" + gbpList.toString());
		gbService.deleteGBPost(gbPost, optList, gbpList);
		
		return "redirect:/groupBuy/list";
	}
	
	// 공동구매 글 수정 - GET 방식
	@GetMapping("/update/{GBPostId}")
	public ModelAndView Updateform(@PathVariable("GBPostId") int GBPostId) {
		ModelAndView mv = new ModelAndView("thyme/groupBuy/groupBuyCorrection");	
		GBEntity gbPost = gbService.getGBPost(GBPostId);
		GBUpdateInfoCommand gbUpdateInfoCommand = new GBUpdateInfoCommand(gbPost.getGBPostId(), gbPost.getProductName(), gbPost.getDuration(), gbPost.getContent());
		mv.addObject("gbPost", gbPost);
		mv.addObject("gbUpdateInfoCommand", gbUpdateInfoCommand);
				
		return mv;
	}
	
	// 공동구매 글 수정 - POST 방식
	@PostMapping("/updated/{GBPostId}")
	public String updateGBPost(
			@ModelAttribute("gbUpdateInfoCommand") GBUpdateInfoCommand gbUpdateInfo,
			@PathVariable("GBPostId") int GBPostId) {
		gbService.updateGBPost(gbUpdateInfo);

		return "redirect:/groupBuy/detail/" + GBPostId;
	}
		
	// 공동구매 참여
	@PostMapping("/participate/{GBPostId}")
	public String participateGB(
			@Valid @ModelAttribute("gbpCommand") GBParticipateCommand gbParticipate,
			@PathVariable("GBPostId") int GBPostId,
			BindingResult bindingResult, Model model,
			@ModelAttribute("memberSession") MemberSession memberSession) {		
		//String loginUserId = memberSession.getMemberId();
		String loginUserId = "dami";   
		//ModelAndView mv = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			//mv = new ModelAndView("thyme/groupBuy/gbNoneBuyerDetail");
            return "thyme/groupBuy/gbNoneBuyerDetail";
        }
	
		gbParticipate.setMember(loginUserId);
		gbService.participate(gbParticipate);
		//mv.setViewName("redirect:/groupBuy/detail/" + GBPostId);
		
//		try {	// 참여 성공
//			gbParticipate.setMember(loginUserId);
//			gbService.participate(gbParticipate);
//			System.out.println("!!!!!" + gbParticipate.toString());
//			mv.setViewName("redirect:/groupBuy/detail/" + GBPostId);
//		} catch (Exception e) {		// 참여 실패
//			mv.setViewName("redirect:/groupBuy/detail/" + GBPostId);
//			mv.addObject("errorMessage", "Fail to participate in GroupBuy");
//		}
		
		return "redirect:/groupBuy/detail/" + GBPostId;
	}

	// 공동구매 참여 취소
	@RequestMapping("/participateCancel/{GBPostId}")
	public String participateCancelGB(
			@ModelAttribute("memberSession") MemberSession memberSession,
			@PathVariable("GBPostId") int GBPostId) {
		//String loginUserId = memberSession.getMemberId();
		String loginUserId = "dami";
		gbService.participateCancel(GBPostId, loginUserId);
		
		return "redirect:/groupBuy/detail/" + GBPostId;
	}
}

package com.marketTrio.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.marketTrio.dao.mybatis.MyBatisMemberDao;
import com.marketTrio.domain.Member;
import com.marketTrio.service.MyInfoValidator;

@Controller
@RequestMapping("/myPage")
@SessionAttributes("memberCommand")
public class MyInfoController {
	@Value("thyme/myPage/myInfoCheck")
	private String myInfoCheck;
	@Value("thyme/myPage/myInfoUpdate")
	private String myInfoUpdate;
	@Value("thyme/myPage/myInfo")
	private String myInfo;
	@Value("thyme/myPage/quit")
	private String quit;
	
//	@Autowired
//	private MarketTrioFacade marketTrio;
//	public void setMarketTrio(MarketTrioFacade marketTrio) {
//		this.marketTrio = marketTrio;
//	}
	@Autowired	
	private MyBatisMemberDao memberDao;

	@Autowired
	private MyInfoValidator validator;
	public void setValidator(MyInfoValidator validator) {
		this.validator = validator;
	}
	
//	@ModelAttribute 어노테이션이 있는 메소드가 호출될 때 memberCommand 객체가 모델에 자동으로 추가된다는 것입니다. 그리고 이 객체는 세션에도 저장됩니다.
	@ModelAttribute("memberCommand")
	public MemberCommand formBacking(HttpServletRequest request) throws Exception {
//		MemberSession memberSession = (MemberSession) request.getAttribute("memberId");
//        String memberId = memberSession.getMemberId();
		String memberId = "seha";
        Member member = memberDao.getMember(memberId);  //현재 사용자의 member객체 구해서
        System.out.println("Member ID: " + member.getId());
        MemberCommand memberCommand = new MemberCommand(member); //memberCommand 객체내의 필드로 member객체를 넣고
        return memberCommand;  //session에 command객체를 저장
	}
	
	//내 정보 보러 감
	@RequestMapping("/myInfo")
	public String showMyInfo(HttpServletRequest request, Model model) {
//		formBacking에서 MemberCommand 객체를 session에 저장했으니 아래 로직 필요 없음
//		MemberSession memberSession = (MemberSession) request.getSession().getAttribute("memberId");
//		Member member = marketTrio.getMember(memberSession.getMemberId());
//		model.addAttribute("member", member);
		return myInfo;  //myInfo에서는 session에서 memberCommand 꺼내서 사용하면 됨
	}
	
	//내 정보에서 정보수정 버튼 눌러서 myInfoCheck화면에서 비번 확인하는 창으로 가는거
	@RequestMapping("/myInfoCheck")
	public String showMyInfoCheck() {
		return myInfoCheck;
	}
	
	//비번입력하고 확인버튼 누르면 정보 수정폼에 정보 보여주는 로직
	//@ModelAttribute("memberCommand")
	@GetMapping("/myInfoUpdate")
	public String showForm(@RequestParam("password") String password, //form에서 name="password" 여기에 입력된 값
							@ModelAttribute("memberCommand") MemberCommand memberCommand) throws Exception {
		Member member = memberCommand.getMember();
		
		if (password == null || password.length() < 1 || !member.getPassword().equals(password)) {
			return myInfoCheck;
		}else {
			//근데 @SessionAttributes("memberCommand") 이걸 썼으니까 굳이 이거 안해도 바로 view로 command 객체 전달되는거 아님? -> 맞음
			//model.addAttribute("memberCommand", memberCommand);  //이거 안써도 됨!
			return myInfoUpdate; //memberCommand는 해당 요청의 모델에 추가되어 뷰에서 ${member.id}와 같은 방식으로 사용됨하면 됨
		}
	}
	
	//정보수정 다 하고 수정완료 버튼 누르는 로직
	@PostMapping("/myInfoUpdate")
	public String onSubmit(
			@ModelAttribute MemberCommand memberCommand,
			BindingResult result, HttpServletRequest request) throws Exception {
		
		validator.validate(memberCommand, result);
		
		if (result.hasErrors()) {
			return myInfoUpdate;
		} else {
			memberDao.updateMember(memberCommand.getMember());
			request.removeAttribute("memberCommand");
			return "redirect:/myPage/myInfo"; // 정보 수정이 완료되면 myInfo 페이지로 리다이렉션
//			리다이렉션된 myInfo.jsp로 요청이 이동하면 showMyInfo 메소드가 실행됨.
//			그러면 @ModelAttribute("memberCommand") 어노테이션이 있는 formBacking 메소드가 실행되어
//			새로운 memberCommand 객체가 생성되어 세션에 저장됨.
//			세션이 비워지더라도 새로운 memberCommand 객체가 생성되어 사용 가능하므로, 데이터의 유실 없이 페이지를 표시할 수 있음.
		}
	}
	
	//회원탈퇴 화면으로 감
	@RequestMapping("/quit")
	public String quit() {
		return quit;
	}
	
	@PostMapping("/quit")
    public String quit(@RequestParam("password") String password,
    					@ModelAttribute MemberCommand memberCommand,
    					SessionStatus status, HttpServletRequest request, Model model) {
		Member member = memberCommand.getMember();
        
        // 입력한 비밀번호와 비밀번호를 비교
		if (password == null || password.length() < 1 || !member.getPassword().equals(password)) {
			model.addAttribute("message", "비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
									/*            jsp에서 이렇게 사용.
									 <%-- 비밀번호가 일치하지 않을 때 표시할 메시지 --%>
						    		<c:if test="${not empty message}">
						        		<p style="color: red;">${message}</p>
						    		</c:if>
									*/
			return "quit"; // 다시 회원 탈퇴 화면으로 이동
		}else {
			memberDao.deleteMember(member.getId());
			status.setComplete();
			return "redirect:/main.jsp";
		}
    }
}

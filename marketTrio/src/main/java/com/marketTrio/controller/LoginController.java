//package com.marketTrio.controller;
//
//package com.marketTrio.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.bind.support.SessionStatus;
//
//import com.marketTrio.controller.MemberSession;
//import com.marketTrio.domain.Member;
//import com.marketTrio.service.LoginService;
//
//@Controller
//@RequestMapping("/login")
//@SessionAttributes("memberSession")
//public class LoginController {
//
//    @Autowired
//    private LoginService loginService;
//
//    @PostMapping
//    public String login(String username, String password, Model model) {
//        Member member = loginService.login(username, password);
//        if (member != null) {
//            model.addAttribute("memberSession", new MemberSession(member.getId()));
//            return "redirect:/myPage/myInfo";
//        } else {
//            return "login";
//        }
//    }
//
//    @RequestMapping("/logout")
//    public String logout(SessionStatus sessionStatus) {
//        sessionStatus.setComplete();
//        return "redirect:/";
//    }
//}

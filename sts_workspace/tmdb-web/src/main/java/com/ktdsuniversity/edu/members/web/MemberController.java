package com.ktdsuniversity.edu.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.members.service.MemberService;
import com.ktdsuniversity.edu.members.vo.CheckResultVO;
import com.ktdsuniversity.edu.members.vo.MemberVO;

import jakarta.validation.Valid;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@ResponseBody
	@GetMapping("/join/check/{email}")
	public CheckResultVO doCheckEmailAction(@PathVariable String email) {
		
		MemberVO memberVO = this.memberService.findMemberByEmail(email);
		
		CheckResultVO result = new CheckResultVO();
		result.setEmail(email);
		result.setCheck(memberVO != null);
		
		return result;
		
	}
	
	
	@GetMapping("/join")
	public String viewMemberJoinPage() {
		return "members/join";
	}
	
	@PostMapping("/join")
	public String doMemberJoinPage(@Valid @ModelAttribute MemberVO memberVO,
									BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("errorData",memberVO);
			return "members/join";
		}
		
		boolean join = this.memberService.createMemberJoin(memberVO);
		
		return "redirect:/";
	}
	
}

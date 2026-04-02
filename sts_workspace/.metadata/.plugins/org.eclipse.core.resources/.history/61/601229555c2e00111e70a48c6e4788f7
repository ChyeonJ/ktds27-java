package com.ktdsuniversity.edu.members.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.edu.members.service.MemberService;
import com.ktdsuniversity.edu.members.vo.request.SignVO;
import com.ktdsuniversity.edu.members.vo.response.SearchVO;

@Controller
public class MembersController {
	
	@Autowired
	private MemberService memberService;
	
	
	// 회원가입 보여주는 EndPoint
	@GetMapping("/regist")
	public String  viewWritePage() {
		return "members/regist";
	}
	
	
	@PostMapping("/regist")
	public String viewMemberPage(SignVO signVO) {
		
		boolean regist = this.memberService.createRegist(signVO);
		System.out.println(regist);
		
		return "redirect:/member/list";
	}
	
	/*
	 * /member/사용자 아이디 ==> 회원 정보 조회
	 * /member/update/사용자 아이디 ==> 회원 정보 수정 페이지 보기
	 * /member/update/사용자 아이디 ==> 회원 정보 수정 하기
	 * /member/delete?id=사용자 아이디 ==> 회원정보 삭제
	 */
	
	@GetMapping("/member/{articleId}")
	public String viewMemberPage(@PathVariable String articleId, Model model) {
		SignVO signVO = this.memberService.findMembersByArticleId(articleId);
		model.addAttribute("member",signVO);
		return "/members/member";
	}
	
	@GetMapping("/member/update/{id}")
	public String viewUpdatePage(@PathVariable String id, Model model) {
		SignVO signVO = this.memberService.findMembersByArticleId(id);
		model.addAttribute("member",signVO);
		
		return "/members/update";
	}
	
	@PostMapping("/member/update/{email}")
	public String updateById(@PathVariable String email, SignVO signVO) {
		
		signVO.setEmail(email);
		boolean result = this.memberService.updateMemberById(signVO);
		System.out.println(result);
		return "redirect:/member/" + email;
	}
	
	@GetMapping("/member/delete")
	public String deleteMemberPage(@RequestParam String id) {
		
		boolean result = this.memberService.doDeleteMember(id);
		System.out.println(result);
		
		return "redirect:/member/list";
	}
	
	// /member 회원들의 목록이 조회되도록 코드를 작성
	//  ==> 회원목록조회
	//  ==> members/list.jsp : 회원 목록 반복
	// 						 : 회원의 수 출력
	//		 				 : 회원의 수가 없을 때, "등록된 회원이 없습니다" 출력
	//						 : 목록 아래에는 "새로운 회원 등록" 링크 추가
	@GetMapping("/member/list")
	public String viewSearchListPage(Model model) {
		
		
		SearchVO searchVo = this.memberService.findMemberList();
		List<SignVO> result = searchVo.getSearchList();
		int resultCount = searchVo.getSearchCount();
		
		model.addAttribute("searchMemberResult",result);
		model.addAttribute("resultCount",resultCount);
		
		return "/members/list";
	}

}

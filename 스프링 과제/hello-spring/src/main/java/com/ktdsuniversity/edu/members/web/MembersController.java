package com.ktdsuniversity.edu.members.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.common.utils.ServletUtils;
import com.ktdsuniversity.edu.members.service.MemberService;
import com.ktdsuniversity.edu.members.vo.request.SignVO;
import com.ktdsuniversity.edu.members.vo.response.DuplicateResultVO;
import com.ktdsuniversity.edu.members.vo.response.SearchVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class MembersController {
	
	private static final Logger logger = LoggerFactory.getLogger(MembersController.class);
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")
	public String viewLoginPage(Authentication authentication) {
		
		if(authentication != null) {
			return "redirect:/";
		}
		
		return "members/login";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/logout")
	public String doLogoutAction(HttpSession session) {
		
		// "/logout" 링크로 접속하면 로그아웃 되고(세션이 제거되고) "/login" 페이지로 이동하도록 한다.
		session.invalidate();
		
		return"redirect:/login";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete-me")
	public String doDeleteAction(HttpSession session, Authentication authentication) {
		
		SignVO loginMember = (SignVO) authentication.getPrincipal();
		
		// 1. 로그인 세션에서 회원의 이메일을 가져온다
		// 2. MEMBERS 테이블에서 회원의 정보를 이메일을 이용해 삭제한다.
		boolean result = this.memberService.doDeleteMember(loginMember.getEmail());
		
		// 3. 현재 로그인된 사용자를 로그아웃 시킨다.
		LogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(ServletUtils.getRequest(),
							 ServletUtils.getResponse(),
							 authentication);
		
		// 4. "members/deletesuccess" 페이지를 보여준다.
		// 	   "탈퇴가 완료됐습니다. 다음에 다시 만나요!"
		return "members/deletesuccess";
	}
	
	// email 중복 검사
	// 반환 타입이 String이면 템플릿을 돌려준다 우리는 사용 하지 않는다
	//ResponseBody를 붙이면 반환되는 데이터가 JSON으로 반환된다.
	@ResponseBody
	@GetMapping("/regist/check/duplicate/{email}")
	public DuplicateResultVO doCheckDuplicateEmailAction(@PathVariable String email) {
		
		// email이 이미 사용중인지 확인한다.
		SignVO membersVO = this.memberService.findMembersByArticleId(email);
		
		// 확인 된 결과를 브라우저에게 JSON으로 전송한다.
		// 이미 사용중 ==> {email: "test@gmail", duplicate:true}
		// 사용중이지 않음 ==> {email: "test@gmail", duplicate:false}
		DuplicateResultVO result = new DuplicateResultVO();
		result.setEmail(email);
		result.setDuplicate(membersVO != null);
		
		return result;
	}
	
	
	// 회원가입 보여주는 EndPoint
	@PreAuthorize("isAnonymous()")
	@GetMapping("/regist")
	public String  viewWritePage() {
		return "members/regist";
	}
	
	@PreAuthorize("isAnonymous()")
	@PostMapping("/regist")
	public String viewMemberPage(@Valid @ModelAttribute SignVO signVO,
								  BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("inputData",signVO);
			return "members/regist";
		}
		
		boolean regist = this.memberService.createRegist(signVO);
		logger.debug("{}",regist);
		
		return "redirect:/login";
	}
	
	/*
	 * /member/사용자 아이디 ==> 회원 정보 조회
	 * /member/update/사용자 아이디 ==> 회원 정보 수정 페이지 보기
	 * /member/update/사용자 아이디 ==> 회원 정보 수정 하기
	 * /member/delete?id=사용자 아이디 ==> 회원정보 삭제
	 */
	@PreAuthorize("isAuthenticated() and #email == authentication.principal.email")
	@GetMapping("/member/{articleId}")
	public String viewMemberPage(@PathVariable String articleId, Model model) {
		SignVO signVO = this.memberService.findMembersByArticleId(articleId);
		model.addAttribute("member",signVO);
		return "/members/member";
	}
	
	@PreAuthorize("isAuthenticated() and #email == authentication.principal.email")
	@GetMapping("/member/update/{id}")
	public String viewUpdatePage(@PathVariable String id, Model model) {
		SignVO signVO = this.memberService.findMembersByArticleId(id);
		model.addAttribute("member",signVO);
		
		return "/members/update";
	}
	
	@PreAuthorize("isAuthenticated() and #email == authentication.principal.email")
	@PostMapping("/member/update/{email}")
	public String updateById(@PathVariable String email, SignVO signVO) {
		
		signVO.setEmail(email);
		boolean result = this.memberService.updateMemberById(signVO);
		logger.debug("{}",result);
//		System.out.println(result);
		return "redirect:/member/" + email;
	}
	
	@PreAuthorize("isAuthenticated() and #email == authentication.principal.email")
	@GetMapping("/member/delete")
	public String deleteMemberPage(@RequestParam String id) {
		
		boolean result = this.memberService.doDeleteMember(id);
		logger.debug("{}",result);
//		System.out.println(result);
		
		return "redirect:/member/list";
	}
	
	// /member 회원들의 목록이 조회되도록 코드를 작성
	//  ==> 회원목록조회
	//  ==> members/list.jsp : 회원 목록 반복
	// 						 : 회원의 수 출력
	//		 				 : 회원의 수가 없을 때, "등록된 회원이 없습니다" 출력
	//						 : 목록 아래에는 "새로운 회원 등록" 링크 추가
	@PreAuthorize("hasRole('RL-20260414-000002')")
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

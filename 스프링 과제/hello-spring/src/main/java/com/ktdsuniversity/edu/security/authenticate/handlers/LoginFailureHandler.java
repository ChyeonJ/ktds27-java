package com.ktdsuniversity.edu.security.authenticate.handlers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFailureHandler implements AuthenticationFailureHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginFailureHandler.class);
	
	private MembersDao membersDao;
	
	//실패하면 fail처리해줘야해서 정보 받아옴?
	public LoginFailureHandler(MembersDao membersDao) {
		this.membersDao = membersDao;
	}
	
	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		//예외 확인
		logger.error(exception.getMessage(), exception);
		
		//폼데이터 상의 아이디 가져옴
		String email = request.getParameter("email");
		
		// 패스워드가 틀렸을 때만 실행.
		// 예외가 Bad예외면 그때만 실행시켜라
		if(exception instanceof BadCredentialsException) {
		this.membersDao.updateIncreaseLoginFailCount(email);
		this.membersDao.updateBlock(email);
		}
		
		// 로그인 페이지 보여주기
		// 로그인 페이지에 이메일 전달해주기
		String loginPagePath = "/WEB-INF/views/members/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPagePath);
				
		// 로그인 페이지에 이메일 전달
		LoginVO loginVO = new LoginVO();
		loginVO.setEmail(email);
		
		// Spring  > Model.addAttribute(k,v) 같은 코드
		request.setAttribute("inputData", loginVO);
		
		// 에러 메시지 보내주기
		request.setAttribute("errorMessage", exception.getMessage());
		
		// 로그인 페이지 이동
		dispatcher.forward(request, response);
	}
}

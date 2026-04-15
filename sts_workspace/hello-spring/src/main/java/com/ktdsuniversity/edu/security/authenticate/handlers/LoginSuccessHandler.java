package com.ktdsuniversity.edu.security.authenticate.handlers;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ktdsuniversity.edu.common.utils.StringUtils;
import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;

import ch.qos.logback.core.util.StringUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private MembersDao membersDao;
	
	public LoginSuccessHandler(MembersDao membersDao) {
		this.membersDao = membersDao;
	}
	
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		LoginVO loginVO = new LoginVO();
		loginVO.setIp(request.getRemoteAddr());
		// authentication이 인증토큰을 가지고 있어서 getName을 쓰면 이메일을 가지고옴
		loginVO.setEmail(authentication.getName());
		
		this.membersDao.updateSuccessLogin(loginVO);
		
		//HttpServletRequest에서 파라미터를 가져오는 방법
		String go = request.getParameter("go");
		
		response.sendRedirect(StringUtils.emptyTo(go, "/"));
	}

}

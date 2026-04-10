package com.ktdsuniversity.edu.common.utils;

import com.ktdsuniversity.edu.members.vo.request.SignVO;

import jakarta.servlet.http.HttpSession;

public class SessionUtils {
	

	private SessionUtils() {}
	
	public static HttpSession createNewSession() {
		SessionUtils.getSession().invalidate();
		return ServletUtils.getRequest().getSession(true);
	}
	
	public static void invalidate() {
		SessionUtils.getSession().invalidate();
	}
	
	public static HttpSession getSession() {
		return ServletUtils.getRequest().getSession();
	}
	
	public static boolean isLoggedIn() {
		return SessionUtils.getLoginSession() != null;
	}
	
	public static SignVO getLoginSession() {
		return (SignVO) getAttribute("__LOGIN_DATA__");
	}
	
	public static String getEmail() {
		if (SessionUtils.isLoggedIn()) {
			return SessionUtils.getLoginSession().getEmail();
		}
		return null;
	}
	
	public static void setAttribute(String key, Object value) {
		SessionUtils.getSession().setAttribute(key, value);
	}
	
	public static Object getAttribute(String key) {
		return SessionUtils.getSession().getAttribute(key);
	}
	
	public static boolean isMineResource(String email) {
		if (SessionUtils.isLoggedIn()) {
			String loggedEmail = SessionUtils.getLoginSession().getEmail();
			return loggedEmail.equals(email);
		}
		return false;
	}

}

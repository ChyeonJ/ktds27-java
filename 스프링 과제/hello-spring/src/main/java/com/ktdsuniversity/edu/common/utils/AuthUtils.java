package com.ktdsuniversity.edu.common.utils;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ktdsuniversity.edu.members.vo.request.SignVO;

public abstract class AuthUtils {
	
	// 익명 클래스 조차 만들지 못하게 막음
	private AuthUtils() {}
	
	// Authentication이 null이 아닌지 검사하는 기능
	public static boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder
				                        .getContext()
				                        .getAuthentication();
		return authentication != null;
	}
	
	// Authentication 토큰에서 MembersVO를 가져오는 기능
	public static SignVO getPrincipal() {
		if(isAuthenticated()) {
			Authentication authentication = SecurityContextHolder
						                    .getContext()
						                    .getAuthentication();
			return (SignVO) authentication.getPrincipal();
		}
		return null;
	}
	
	// Authentication 토큰에서 이메일을 가져오는 기능
	public static String getUsername() {
		if(isAuthenticated()) {
			return getPrincipal().getEmail();
		}
		return null;
	}
	
	// Authentication 토큰에 일부 권한이 부여되어 있는지 검사하는 기능
	public static boolean hasAnyRole(String ... roles) {
		
		if(isAuthenticated()) {
			List<String> grantedRoles = getPrincipal().getRoles();
			
			for (String role : roles) {
				if(grantedRoles.contains(roles)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
}
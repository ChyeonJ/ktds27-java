package com.ktdsuniversity.edu.security.authenticate.oauth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.security.user.SecurityUser;

public class NaverOAuthUserDetails extends SecurityUser implements OAuth2User{
	
	//클러스트링 할 때 시리얼 버전 아이디가 필요하다
	private static final long serialVersionUID = -7781093686223372145L;
	
	private Map<String, Object> oauthResult;
	
	public NaverOAuthUserDetails(MembersVO membersVO, Map<String, Object> oauthResult) {
		super(membersVO);
		this.oauthResult = (Map<String, Object>)oauthResult.get("response");
		
		membersVO.setEmail(this.oauthResult.get("email").toString());
		membersVO.setName(this.oauthResult.get("name").toString());
		List<String> userRoles = new ArrayList<>();
		userRoles.add("RL-20260414-00003");
		membersVO.setRoles(userRoles);
	}
	
	public String getEmail() {
		return super.getMembersVO().getEmail();
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return this.oauthResult;
	}

	@Override
	public String getName() {
		return super.getMembersVO().getName();
	}
	
}

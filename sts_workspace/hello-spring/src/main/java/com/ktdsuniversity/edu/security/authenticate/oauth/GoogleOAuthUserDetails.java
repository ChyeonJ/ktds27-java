package com.ktdsuniversity.edu.security.authenticate.oauth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.security.user.SecurityUser;

public class GoogleOAuthUserDetails extends SecurityUser implements OAuth2User{

	private Map<String, Object> oAuthResult;

	private static final long serialVersionUID = -8165293844486524919L;
	
	public GoogleOAuthUserDetails(MembersVO membersVO, Map<String, Object> oAuthResult) {
		super(membersVO);
		this.oAuthResult = oAuthResult;
		
		membersVO.setEmail(this.oAuthResult.get("email").toString());
		membersVO.setName(this.oAuthResult.get("given_name").toString());
		
		List<String> userRoles = new ArrayList<>();
		userRoles.add("RL-20260414-00003");
		membersVO.setRoles(userRoles);
		
	}

	public String getEmail() {
		return super.getMembersVO().getEmail();
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return this.oAuthResult;
	}

	@Override
	public String getName() {
		return super.getMembersVO().getName();
	}
}

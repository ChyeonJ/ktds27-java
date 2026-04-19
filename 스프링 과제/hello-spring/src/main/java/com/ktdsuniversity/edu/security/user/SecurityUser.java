package com.ktdsuniversity.edu.security.user;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktdsuniversity.edu.members.vo.request.SignVO;

										//Security 라이브러리
public class SecurityUser implements UserDetails{
	
	private static final long serialVersionUID = 9123597548972794910L;
	
	private SignVO membersVO;
	
	//인증 공급자가 가져갈 수 있게?
	public SignVO getMembersVO() {
		return this.membersVO;
	}
	
	// 디테일에서 객체 받아옴
	public SecurityUser (SignVO membersVO) {
		this.membersVO = membersVO;
	}
	
	// 리스트면 하나씩 뽑아서 데이터를 넣어줌
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.membersVO.getRoles()
							  .stream()
							  //권한의 경우 텍스트를 붙임 ACTION이면 DELETE이런걸 붙임
							  .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
							  .toList();
	}
	
	// 비밀번호 가져옴
	@Override
	public @Nullable String getPassword() {
		return this.membersVO.getPassword();
	}
	
	// 이메일 가져옴
	@Override
	public String getUsername() {
		return this.membersVO.getEmail();
	} 
	
	//잠겼는지 확인
	@Override
	public boolean isAccountNonLocked() {
		return this.membersVO.getBlockYn().equals("N");
	}
	
}

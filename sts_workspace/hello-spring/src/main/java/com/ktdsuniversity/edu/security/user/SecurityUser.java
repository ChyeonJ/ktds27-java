package com.ktdsuniversity.edu.security.user;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktdsuniversity.edu.members.vo.MembersVO;

/**
 * Spring Security가 사용자를 식별할 때 사용
 */
public class SecurityUser implements UserDetails{
	
	/**
	 * UserDetails 인터페이스로 사용자의 세부 내용을 알 수 없기 때문에
	 * 사용자의 정보를 가지고 있는 membersVO를 멤버변수로 추가해준다.
	 */
	private MembersVO membersVO;
	
	//DB에서 조회한 사용자의 정보를 파라미터로 받음
	public SecurityUser(MembersVO membersVO) {
		this.membersVO = membersVO;
	}
	
	
	
	public MembersVO getMembersVO() {
		return this.membersVO;
	}

	public void setMembersVO(MembersVO membersVO) {
		this.membersVO = membersVO;
	}

	/**
	 * 여러가지 서버에 객체를 공유하기 위해 식별하기 위한 코드
	 */
	private static final long serialVersionUID = 7907191462472441568L;

	/**
	 * 사용자의 권한 목록을 관리.
	 * 추후 권한별 서비스 제공시 사용
	 * ROLES 테이블에서 조회
	 * 
	 * GrantedAuthority <-- 사용자에게 허용된 권한
	 * Collection <-- List /Set
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}

	/**
	 * 로그인한 회원의 비밀번호
	 */
	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return this.membersVO.getPassword();
	}
	
	/**
	 * 사용자의 아이디 (식별가능한)
	 * => 이메일
	 */
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.membersVO.getEmail();
	}
	
	//계정이 잠겼냐 안잠겼냐를 다룰 메소드
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.membersVO.getBlockYn().equals("N");
	}
	
	
	
}

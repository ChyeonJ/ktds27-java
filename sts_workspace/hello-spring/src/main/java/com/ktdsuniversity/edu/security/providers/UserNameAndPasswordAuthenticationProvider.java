package com.ktdsuniversity.edu.security.providers;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.security.authenticate.service.SecurityPasswordEncoder;
import com.ktdsuniversity.edu.security.user.SecurityUser;

/**
 * 인증 공급자 계층
 * Spring Security의 인증(아이디와 비밀번호 일치 검사)을 수행하는 공급자
 * 사용자의 인증정보가 일치할 경우 Authentication Token을 발급해 SecurityContext에 저장하도록 한다.
 */
public class UserNameAndPasswordAuthenticationProvider implements AuthenticationProvider{
	
	/**
	 * 사용자가 로그인할 때 전송한 아이디로 회원의 정보를 조회
	 */
	private UserDetailsService userDetailsService;
	
	/**
	 * 사용자가 로그인할 때 전송한 비밀번호와 회원의 비밀번호를 비교
	 */
	private PasswordEncoder passwordEncoder;
	
	
	public UserNameAndPasswordAuthenticationProvider(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}


	/**
	 * 사용자로부터 Spring Security 로그인 요청이 있을 때 마다 실행.
	 * 
	 * 사용자가 보내준 아이디와 비밀번호를 이용해 인증을 수행한다.
	 * UserDetailsService 인터페이스를 이용해 사용자의 정보를 조회하고
	 * PasswordEncoder 인터페이스를 이용해 사용자의 비밀번호를 검증하고
	 * 인증정보가 일치할 때만 UsernamePasswordAuthenticationToken을 발급한다.
	 * 
	 * @param authentication : 사용자가 로그인 요청한 정보 (아이디, 비밀번호)
	 * @return UsernamePasswordAuthenticationToken
	 */
	@Override
	public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		// 로그인에 사용된 사용자의 아이디(이메일)
		String email = authentication.getName();
		
		// UserDetails ==> SecurityUser
		// username ==> 아이디
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
		if(!userDetails.isAccountNonLocked()) {
			throw new LockedException("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		//getCredentials 타입이 Object인데, 그이유는 다른 인증 방식도 사용할 수 있기 때문이다(지문, 패턴, 음성 로그인) 우리는 비밀번호만 사용하니까 ,toString 붙임
		String rawPassword = authentication.getCredentials().toString(); //사용자가 입력한 패스워드의 원형
		
		// SecurityUser에서 implements로 userDetails를 받고 있기에 형변환이 가능하다
		MembersVO membersVO = ((SecurityUser) userDetails).getMembersVO();
		
		SecurityPasswordEncoder passwordComparator = (SecurityPasswordEncoder)this.passwordEncoder;
		
		boolean isMatch = passwordComparator.matches(rawPassword, membersVO.getSalt(),userDetails.getPassword()); //userDetails.getPassword() => DB에서 가져온 비밀번호
		
		//password를 암호화해서 비교한 결과가 일치하지 않는다면
		if(!isMatch) {
			throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다");
		}
		
		
		//SecurityContext에 저장할 인증 토큰								암호화 된 패스워드						
		return new UsernamePasswordAuthenticationToken(membersVO, userDetails.getPassword(), userDetails.getAuthorities());
	}
	
	
	/**
	 * 이 인증 공급자가 발급하는 토큰의 종류를 설정.
	 * @param authentication(위의 메소드): authenticate() 메소드가 발급한 토큰의 클래스
	 * @return authenticate()가 발급한 토큰의 클래스가 적절한지 여부
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

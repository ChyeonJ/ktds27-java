package com.ktdsuniversity.edu.security.providers;

import java.time.Duration;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * 사용자의 정보를 이용해 인증 객체를 생성하고 검증하는 클래스(객체)
 * Spring Security AuthenticationProvider와는 무관
 * 뜻 => implements의 AuthenticationProvider가 무관하다
 * 사용 목적 : API를 호출할 때 인증수단으로 사용하기 위해
 */
public class JsonWebTokenAuthenticationProvider {
	
	/**
	 * 사용자의 이메일을 이용해 인증용 JWT를 생성
	 * 
	 * @param email 사용자의 이메일
	 * @param expiredAt JWT의 유효기간 (지금으로부터 ~분(시간, 일, 월, 연) 까지 유효
	 * @return email과 expiredAt으로 생성한 JsonWebToken 반환
	 */
	public String makeJsonWebToken(String email, Duration expiredAt) {
		
		//JsonWebToken이 발행되는 날짜와 시간을 생성
		Date issuDate = new Date();
		
		//JsonWebToken이 만료되는 날짜와 시간을 생성
		// 발행 날짜 시간 + expiaredAt
		Date expirationDate = new Date(issuDate.getTime() + expiredAt.toMillis());
		
		// 암/복호화 키 생성									application.yml에 작성한 비밀키
		SecretKey signKey = Keys.hmacShaKeyFor("spring-security-secret-key-random-token-key".getBytes());
		
		String jsonWebToken = Jwts.builder()
							  // JsonWebToken을 발생한 시스템 이름
							  // TODO application.yml에서 가져올 내용
						      .issuer("hello-spring")
						      // JsonWebToken의 이름 _token도 정하기 나름 abc 가능
						      .subject(email + "_token")
						      // JsonWebToken에 포함 되어야 할 회원의 정보들 (여러 개 추가가능) K, V
						      .claim("identiFy", email)
						      // JsonWebToken을 발행한 (시간, 일, 월, 연)
						      .issuedAt(issuDate) //Date타입 필요
						      // JsonWebToken의 유효한 (시간, 일, 월, 연)(생명주기)
						      .expiration(expirationDate) //Date 타입
						      // 평문으로 구성된 JsonWebToken을 암호화 또는 복호화 시킬 때 사용할 키(Salt와 가까움)
						      .signWith(signKey)
						      // Jwts에 제공된 데이터를 이용해 String Type의 Token을 생성
							  .compact();
		return jsonWebToken;
	}
	
	public static void main(String[] args) {
		
		JsonWebTokenAuthenticationProvider jwtProvider = new JsonWebTokenAuthenticationProvider();
		
		String jwt = jwtProvider.makeJsonWebToken("test@gmail.com", Duration.ofHours(3));
		
		System.out.println(jwt);
	}
	
	
}

package com.ktdsuniversity.edu.security.providers;

import java.time.Duration;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * 사용자의 정보를 이용해 인증 객체를 생성하고 검증하는 클래스(객체)
 * Spring Security AuthenticationProvider와는 무관
 * 뜻 => implements의 AuthenticationProvider가 무관하다
 * 사용 목적 : API를 호출할 때 인증수단으로 사용하기 위해
 */
public class JsonWebTokenAuthenticationProvider {
	
	private String secretKey;
	private String issuer;
	
	
	public JsonWebTokenAuthenticationProvider(String secretKey, String issuer) {
		this.secretKey = secretKey;
		this.issuer = issuer;
	}
	
	/**
	 * 사용자가 요청할 떄마다 Request Header[Authorization]에 전달한 jsonWebToken을 가져와서 복호화 시킨다.
	 * 복호화 된 결과에서 사용자의 이메일(identify)을 추출하여 반환시킨다.
	 * 
	 * @param jsonWebToken 사용자가 전달한 토큰
	 * @return jsonWebToken에서 추출한 사용자의 이메일
	 */
	public String decrpytJsonWebToken(String jsonWebToken) {
		
		// 암호화 시켰던 키를 복호화할 때 다시 사용하기 위해 가져옴
		SecretKey signKey = Keys.hmacShaKeyFor(this.secretKey.getBytes());
		
		// 밑에 에미엘(identify) 값을 얘기함
		Claims claims = Jwts.parser() // JsonWebToken을 분석하기 위한 선언
							.verifyWith(signKey)//JsonWebToken을 복호화 하기 위한 비밀키 지정
							.requireIssuer(this.issuer)// 사용자가 전달한 JsonWebToken이 hello-spring 시스템에서 만든 것인지 확인
							.build() // JsonWebToken을 복호화 시작
							.parseSignedClaims(jsonWebToken) // 사용자가 전달한 JsonWebToken을 복호화 한다
							.getPayload(); // 복호화 된 결과에서 claim들만 모아서 반환함 형태는(Map => Key, Value)
		
		// 이 값을 스트링형태로 넣어라ㅣ
		// 사용자가 전달한 JsonWebToken을 복호화 한 뒤 identity 값을 추출한다.
		String email = claims.get("identiFy", String.class );
		return email;
	}
	
	/**
	 * 사용자의 이메일을 이용해 인증용 JWT를 생성하고
	 * 결과를 사용자에게 보내주어야 한다.
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
		SecretKey signKey = Keys.hmacShaKeyFor(this.secretKey.getBytes());
		
		String jsonWebToken = Jwts.builder()
							  // JsonWebToken을 발생한 시스템 이름
							  // TODO application.yml에서 가져올 내용
						      .issuer(this.issuer)
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
		
		JsonWebTokenAuthenticationProvider jwtProvider = new JsonWebTokenAuthenticationProvider("spring-security-secret-key-random-token-key","hello-spring");
																	//ofHours(3)
		String jwt = jwtProvider.makeJsonWebToken("admin@a.com", Duration.ofMillis(10000)); //0.01초 유효한 토큰
		
		System.out.println(jwt);
		
		String email = jwtProvider.decrpytJsonWebToken(jwt);
		System.out.println(email);
		
	}
	
	
}

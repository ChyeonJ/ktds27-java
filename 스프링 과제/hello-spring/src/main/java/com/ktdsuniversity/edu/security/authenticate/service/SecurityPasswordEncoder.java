package com.ktdsuniversity.edu.security.authenticate.service;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ktdsuniversity.edu.members.helpers.SHA256Util;

public class SecurityPasswordEncoder implements PasswordEncoder{
	
	// 이건 쓰이지도 않는데 왜 있죠?
	@Override
	public @Nullable String encode(@Nullable CharSequence rawPassword) {
		return null;
	}
	
	//이것도
	@Override
	public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
		return false;
	}	
	
	// salt랑 평문 비번이랑 암호화
	public String encode(String rawPassword, String salt) {
		return SHA256Util.getEncrypt(rawPassword, salt);
	}
	
	// 암호화한 비번이랑 입력한 비번이랑 비교
	public boolean matches(String rawPassword, String salt, String encodedPassword) {
		return this.encode(rawPassword, salt).equals(encodedPassword);
	}

}

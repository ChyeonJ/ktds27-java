package com.ktdsuniversity.edu.members.service;

import com.ktdsuniversity.edu.members.vo.MemberVO;

import jakarta.validation.Valid;

public interface MemberService {

	boolean createMemberJoin(@Valid MemberVO memberVO);

	MemberVO findMemberByEmail(String email);

}

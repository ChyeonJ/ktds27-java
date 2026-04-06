package com.ktdsuniversity.edu.members.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.members.vo.MemberVO;

@Mapper
public interface MemberDao {

	MemberVO selectMemberByEmail(String email);

	int createJoinMember(MemberVO memberVO);

}

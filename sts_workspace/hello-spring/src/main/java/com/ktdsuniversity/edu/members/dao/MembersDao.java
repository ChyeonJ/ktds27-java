package com.ktdsuniversity.edu.members.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.members.vo.request.SignVO;

@Mapper
public interface MembersDao {

	int insertRegist(SignVO signVO);

	SignVO selectByArticleId(String articleId);

	int updateMember(SignVO signVO);

	int deleteOne(String id);

	int selectCount();

	List<SignVO> selectAllMembers();
	

}

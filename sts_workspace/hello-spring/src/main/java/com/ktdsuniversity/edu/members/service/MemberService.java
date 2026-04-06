package com.ktdsuniversity.edu.members.service;

import com.ktdsuniversity.edu.members.vo.request.LoginVO;
import com.ktdsuniversity.edu.members.vo.request.SignVO;
import com.ktdsuniversity.edu.members.vo.response.SearchVO;

import jakarta.validation.Valid;

public interface MemberService {

	boolean createRegist(SignVO signVO);

	SignVO findMembersByArticleId(String articleId);

	boolean updateMemberById(SignVO signVO);

	boolean doDeleteMember(String id);

	SearchVO findMemberList();

	SignVO findMembersByEmailAndPassword(@Valid LoginVO loginVO);

}

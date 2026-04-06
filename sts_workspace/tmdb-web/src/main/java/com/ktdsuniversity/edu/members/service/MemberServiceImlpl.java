package com.ktdsuniversity.edu.members.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.members.dao.MemberDao;
import com.ktdsuniversity.edu.members.helpers.SHA256Util;
import com.ktdsuniversity.edu.members.vo.MemberVO;

@Service
public class MemberServiceImlpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public MemberVO findMemberByEmail(String email) {
		
		MemberVO result = this.memberDao.selectMemberByEmail(email);
		
		return result;
	}
	
	@Override
	public boolean createMemberJoin(MemberVO memberVO) {
		
		MemberVO memberCO = this.memberDao.selectMemberByEmail(memberVO.getEmail());
		if(memberCO != null) {
			throw new IllegalArgumentException(memberVO.getEmail() + "은 이미 사용중");
		}
		
		String newSalt = SHA256Util.generateSalt();
		String userPassword = memberVO.getPassword();
		
		userPassword = SHA256Util.getEncrypt(userPassword, newSalt);
		
		memberVO.setSalt(newSalt);
		memberVO.setPassword(userPassword);
		
		int resultCount = this.memberDao.createJoinMember(memberVO);
		
		return resultCount == 1;
	}
	
}

package com.ktdsuniversity.edu.security.authenticate.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.request.SignVO;
import com.ktdsuniversity.edu.security.user.SecurityUser;


//DB에서 사용자 정보 조회
/**
* 로그인 인증 수행 시 로그인 요청 정보중 아이디로 회원의 정보를 조회한다.
*/
public class SecurityUserDetailService implements UserDetailsService{
	
	private MembersDao membersDao;
	
	// 이것도 아직 이해가 안감
	public SecurityUserDetailService(MembersDao membersDao) {
		this.membersDao = membersDao;
	}

	/**
	 * 아이디로 데이터베이스에서 회원의 정보를 조회한다.
	 * @param username : 아이디 (이메일)
	 * @return DB에서 조회한 회원의 정보 (SecurityUser)
	 * @throws UsernameNotFoundException : DB에 회원의 정보가 없을 때 던져지는 예외.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) 
						throws UsernameNotFoundException {
		// 회원의 정보를 조회
		SignVO loadedUser = this.membersDao.selectByArticleId(username);
		
		if(loadedUser == null) {
			throw new UsernameNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다");
		}
		
		// List<String>을 쓰는 이유 슈퍼관리자면 3개가 나옴
		List<String> userRole = this.membersDao.selectMemberRolesByEmail(username);
		loadedUser.setRoles(userRole);
		
		//객체 반환
		return new SecurityUser(loadedUser);
	}
}

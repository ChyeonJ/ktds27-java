package com.ktdsuniversity.edu.members.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.members.vo.request.SignVO;

public class SearchVO {
	
	private List<SignVO> searchList;
	private int searchCount;
	
	public List<SignVO> getSearchList() {
		return this.searchList;
	}
	public void setSearchList(List<SignVO> searchList) {
		this.searchList = searchList;
	}
	public int getSearchCount() {
		return this.searchCount;
	}
	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}
	
	

}

package com.ktdsuniversity.edu.fp.basic.kakao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FriendList {
	
	private List<Friend> friends;
	
	public FriendList() {
		this.friends = new ArrayList<>();
	}
	
	public void add(Friend friend) {
		this.friends.add(friend);
	}
	
	//람다
	public void printSpecialFriends(Search search) {
		
//		LocalDate tempBirthdate = null;
		for(Friend f : this.friends) {
//			tempBirthdate = LocalDate.parse(f.getBirtadte().toString());
//			tempBirthdate =  tempBirthdate.withYear(LocalDate.now().getYear());
			
			//인터페이스에서 받아온 LocalDate가 있다면 true를 반환하고 출력해라
			if(search.check(f)) {
				System.out.println(f);
			}
		}
	}
	
	//Stream Predicate(Boolean)
	public void printSpecialFriends2(Predicate<Friend> predicate) {
		
		for(Friend f : this.friends) {
			
			//predicate에서 제공하는 인터페이스 사용
			if(predicate.test(f)) {
				System.out.println(f);
			}
		}
	}
	
	@Override
	public String toString() {
		
		StringBuffer buffer = new StringBuffer();
		
		if (this.friends.size() == 0) {
			buffer.append("등록된 친구가 없습니다");
		}
		else {
			for(Friend f : this.friends) {
				buffer.append(f);
				buffer.append("\n");
			}
		}
		
		return buffer.toString();
	}

}

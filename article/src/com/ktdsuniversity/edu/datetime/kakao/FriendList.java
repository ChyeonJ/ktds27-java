package com.ktdsuniversity.edu.datetime.kakao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FriendList {
	
	private List<Friend> friends;
	
	public FriendList() {
		this.friends = new ArrayList<>();
	}
	
	public void add(Friend friend) {
		this.friends.add(friend);
	}
	
	public void printSpecialFriends(Base base) {
		
		LocalDate tempBirthdate = null;
		
		for(Friend f : this.friends) {
			
			tempBirthdate = LocalDate.parse(f.getBirtadte().toString());
			tempBirthdate =  tempBirthdate.withYear(LocalDate.now().getYear());
			
			if(base == Base.FUTURE) {
				// 오늘을 기준으로 7일 이내에 생일을 맞이하는 친구 출력.
				if(tempBirthdate.isAfter(LocalDate.now()) && 
						tempBirthdate.isBefore(LocalDate.now().plusDays(8))) {
					System.out.println("7일 이내에 생일을 맞이하는 친구 : " + f);
				}
			}
			else if(base == Base.NOW) {
				// 오늘이 생일인 친구를 출력
				if(tempBirthdate.isEqual(LocalDate.now())) {
					System.out.println("오늘 생일을 맞이하는 친구 : " + f);
				}
			}
			else if(base == Base.PAST) {
				// 오늘을 기준으로 7일 이내에 생일이 지난친구
				if (tempBirthdate.isBefore(LocalDate.now()) && 
						tempBirthdate.isAfter(LocalDate.now().minusDays(8))) {
					System.out.println("7일 이내에 생일이 지난 친구 : " + f);
				}
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

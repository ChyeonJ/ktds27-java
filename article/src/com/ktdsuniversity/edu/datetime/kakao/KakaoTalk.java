package com.ktdsuniversity.edu.datetime.kakao;

public class KakaoTalk {
	
	public static void main(String[] args) {
		FriendList fl = new FriendList();
		
		fl.add(new Friend("A", "2000-01-01"));
		fl.add(new Friend("B", "2001-11-01"));
		fl.add(new Friend("C", "2004-05-16"));
		fl.add(new Friend("D", "2001-02-28"));
		fl.add(new Friend("E", "2001-02-04"));
		fl.add(new Friend("F", "2001-02-09"));
		fl.add(new Friend("G", "2001-01-30"));
		fl.add(new Friend("H", "2001-02-01"));
		
		fl.printSpecialFriends(Base.FUTURE); // 생일이 다가오는 친구
		fl.printSpecialFriends(Base.NOW); // 오늘이 생일인 친구
		fl.printSpecialFriends(Base.PAST); // 생일이 지난 친구
		
		System.out.println(fl);
	}
	
}

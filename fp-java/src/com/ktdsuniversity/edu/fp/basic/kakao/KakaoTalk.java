package com.ktdsuniversity.edu.fp.basic.kakao;

import java.time.LocalDate;
import java.time.Period;

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
		
		//람다
		System.out.println("7일 이내에 생일을 맞이하는 친구 목록 : ");
		//인터페이스 타입 하나, 파라미터 하나 여서 타입(LocalDate), 소괄호() 안쓴다 / return이 있으므로 {}작성
//		fl.printSpecialFriends(birthday -> birthday.isAfter(LocalDate.now()) && 
//				birthday.isBefore(LocalDate.now().plusDays(8)));
		
		fl.printSpecialFriends(friend -> {
			
			LocalDate birthdate = friend.getBirtadte().withYear(LocalDate.now().getYear());
			
			return birthdate.isAfter(LocalDate.now()) && 
					birthdate.isBefore(LocalDate.now().plusDays(8));	//return 존재하고 여러줄 {}사용
		});
		
		
		System.out.println("오늘이 생일인 친구 목록");
//		fl.printSpecialFriends(birthday -> birthday.isEqual(LocalDate.now()));
		fl.printSpecialFriends2(friend -> {
			LocalDate now = LocalDate.now();
			LocalDate birthdate = friend.getBirtadte().withYear(now.getYear());
			return birthdate.isEqual(now);
		});
		
		System.out.println("7일 이내에 생일이 지난 친구 목록");
//		fl.printSpecialFriends(birthday -> birthday.isBefore(LocalDate.now()) 
//				&& birthday.isAfter(LocalDate.now().minusDays(8)));
		fl.printSpecialFriends2(friend -> {
			LocalDate now = LocalDate.now();
			LocalDate birth = friend.getBirtadte().withYear(now.getYear());
			return birth.isBefore(now) && birth.isAfter(now.minusDays(8));
		});
		
		System.out.println("오늘 만 30세가 된 친구 목록");
		fl.printSpecialFriends2(friend -> {
			Period period = Period.between(friend.getBirtadte(), LocalDate.now());
			System.out.println(period.getDays());
			return period.getYears() == 30 && period.getMonths() == 0 && period.getDays() == 0;
		});
		
		System.out.println("이름이 'A'인 친구 목록");
		fl.printSpecialFriends2(friend -> friend.getName().equals("A"));
		
		System.out.println("이름이 '김'으로 시작하는 친구목록");
		fl.printSpecialFriends2(friend -> friend.getName().startsWith("김"));
		
		System.out.println("이름이 '이'로 시작하면서 나이가 20세 이상인 친구 목록");
		//이거는 직접 해보자. 코드 안보고
		
		System.out.println("전체 친구 목록");
		
		
		System.out.println();
		
		System.out.println("=========================================");
//		System.out.println(fl);
		fl.printSpecialFriends2(friend -> true);
	}
	
}

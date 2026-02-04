package com.ktdsuniversity.edu.datetime.mart;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.datetime.kakao.Friend;

public class Store {
	
	private List<Item> items;
	
	public Store() {
		this.items = new ArrayList<Item>();
	}
	
	public void add(Item item) {
		this.items.add(item);
	}
	
	LocalDate expirationDate = null;
	
	public void sell(Status status) {
		
		/*
		 * index에 할당되어 있는 제품의 소비기한이 당일이라면
		 * "오늘까지 드세요"를 출력.
		 * 
		 * 소비기한이 3일 내에 도래한다면
		 * "가능한 빨리 드세여"를 출력.
		 * 
		 * 소비기한이 지났다면
		 * "소비기한이 지나 판매하지 않습니다"를 출력.
		 * 
		 */
		for(Item i : this.items) {
			expirationDate = LocalDate.parse(i.getExpireDate().toString());
			expirationDate = expirationDate.withYear(LocalDate.now().getYear());
			
			if(status == Status.TODAY) {
				if(expirationDate.isEqual(LocalDate.now())) {
					System.out.println("오늘까지 드세요");
				}
				
			}
			else if(status == Status.PAST) {
				if(expirationDate.isAfter(LocalDate.now()) &&
						expirationDate.isBefore(LocalDate.now().plusDays(4))) {
					System.out.println("가능한 빨리 드세요");
				}
			}
			else if(status == Status.NOSELL) {
				if (expirationDate.isBefore(LocalDate.now()) && 
						expirationDate.isAfter(LocalDate.now().minusDays(8))) {
					System.out.println("소비기한이 지나 판매하지 않습니다.");
				}
			}
		}
		
		
	}
	
	@Override
	public String toString() {
		
		StringBuffer buffer = new StringBuffer();
		
		if (this.items.size() == 0) {
			buffer.append("상품이 없습니다.");
		}
		else {
			for(Item i : this.items) {
				buffer.append(i);
				buffer.append("\n");
			}
		}
		
		return super.toString();
	}
	
}

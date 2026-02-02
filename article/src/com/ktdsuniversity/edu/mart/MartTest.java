package com.ktdsuniversity.edu.mart;
/**
 * 테스트
 */
public class MartTest {
	public static void main(String[] args) {
		
		Product a = new Product("비싼옷", 100_000, 0);
	Product b = new Product("싼옷", 1_000, 0);
	Product c = new Product("땡처리", 50000, 0);
	
	NomalMart pr = new NomalMart(a, b, c);
	
	Customer cs = new Customer(100_000, 1000, null);
	Customer cs1 = new Customer(100_000, 100000, "일반");
	Customer cs2 = new Customer(100_000, 100000, "일반");
	Customer cs3 = new Customer(100_000, 1000, "VIP");
	Customer cs4 = new Customer(10_000, 100000, "VVIP");
	
	
	NomalMart conven = new Convenience(a, b, c);
	
	//NomalMart 일반마트
	pr.sell(cs, "땡처리");
	
	System.out.println("----------------------------------------");
	
	//Convenience 편의점
	if(conven instanceof Convenience cv) {
		cv.sell(cs1, "싼옷");
	}
	
	System.out.println("----------------------------------------");
	
	NomalMart dpt = new Department(a, b, c);
	
	//Department 백화점
	if(dpt instanceof Department dp) {
		dp.sell(cs2, "비싼옷");
		System.out.println("----------------------------------------");
		dp.sell(cs3, "비싼옷");
		System.out.println("----------------------------------------");
		dp.sell(cs4, "땡처리");
		}
		
	}
}

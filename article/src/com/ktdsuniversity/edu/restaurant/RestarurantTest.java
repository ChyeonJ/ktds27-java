package com.ktdsuniversity.edu.restaurant;

import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.restaurant.customer.Customer;
import com.ktdsuniversity.edu.restaurant.exception.DrunkenException;
import com.ktdsuniversity.edu.restaurant.exception.FullException;
import com.ktdsuniversity.edu.restaurant.exception.NoMoneyException;
import com.ktdsuniversity.edu.restaurant.exception.SoldOutException;
import com.ktdsuniversity.edu.restaurant.menu.RestaurantMenu;
import com.ktdsuniversity.edu.restaurant.restaurant.Restaurant;

public class RestarurantTest {
	
	public static void main(String[] args) {
	
		RestaurantMenu rest1 = new RestaurantMenu("감자탕", 27000, 0, 500, 120);
		RestaurantMenu rest2 = new RestaurantMenu("김치찌개", 14000, 0, 500, 230);
		RestaurantMenu rest3 = new RestaurantMenu("소주", 5000, 16.5d, 0, 10);
		RestaurantMenu rest4 = new RestaurantMenu("맥주", 6000, 5.5d, 0, 100);
		
		RestaurantMenu rest5 = new RestaurantMenu("순대국", 5000, 0, 500, 0);
		RestaurantMenu rest6 = new RestaurantMenu("머릿고기", 24000, 0, 1000, 230);
		RestaurantMenu rest7 = new RestaurantMenu("양주", 60000, 55.6d, 0, 100);
		RestaurantMenu rest8 = new RestaurantMenu("보드카", 6000, 78.9d, 0, 100);
		
		
		RestaurantMenu[] restPotato = new RestaurantMenu[4];
		restPotato[0] = rest1;
		restPotato[1] = rest2;
		restPotato[2] = rest3;
		restPotato[3] = rest4;
		
		RestaurantMenu[] restPigSoup = new RestaurantMenu[4];
		restPigSoup[0] = rest5;
		restPigSoup[1] = rest6;
		restPigSoup[2] = rest7;
		restPigSoup[3] = rest8;
		
//		List<RestaurantMenu> menu = new ArrayList<RestaurantMenu>();			//=> 이런식으로 리스트화 하면 됨
//		menu.add(new RestaurantMenu("감자탕", 27000, 0, 500, 120));				//=> 레스토랑 코드는 배열이 중점이기 때문에 못바꿈
//		menu.add(new RestaurantMenu("김치찌개", 14000, 0, 500, 230));
//		menu.add(new RestaurantMenu("소주", 5000, 16.5d, 0, 10));
//		menu.add(new RestaurantMenu("맥주", 6000, 5.5d, 0, 100));
		
		Restaurant restAdmin = new Restaurant("감자탕집", 16.0d, 700, 0);
		Restaurant restAdmin1 = new Restaurant("순대국집", 13.0d, 1000, 0);
		
		Restaurant[] restA = new Restaurant[3];
		restA[0] = restAdmin;
		restA[1] = restAdmin1;
		
		Customer cs1 = new Customer("고객1", 16.0d, 100, 100_000);
		Customer cs2 = new Customer("고객2", 0.0d, 1000, 1000000);
		Customer cs3 = new Customer("고객2", 0.0d, 700, 1);
		Customer cs4 = new Customer("고객2", 0.0d, 700, 100);
		
		Customer[] cs = new Customer[2];
		cs[0] = cs1;
		cs[1] = cs2;
		
		/*
		 * 잔액부족
		 * 재고부족
		 * 취함기준
		 * 배부름기준
		 */
		
		
		try {
			restAdmin.order(cs1, rest3);
		}
		catch(NoMoneyException nme){
			System.out.println(nme.getMessage());
		}
		catch(SoldOutException soe) {
			System.out.println(soe.getMessage());
		}
		catch(DrunkenException de) {
			System.out.println(de.getMessage());
		}
		catch(FullException fe){
			System.out.println(fe.getMessage());
			
		}
		try {
			restAdmin.order(cs2, rest3);
		}
		catch(NoMoneyException nme){
			System.out.println(nme.getMessage());
		}
		catch(SoldOutException soe) {
			System.out.println(soe.getMessage());
		}
		catch(DrunkenException de) {
			System.out.println(de.getMessage());
		}
		catch(FullException fe){
			System.out.println(fe.getMessage());
			
		}
		try {
			restAdmin.order(cs3, rest3);
		}
		catch(NoMoneyException nme){
			System.out.println(nme.getMessage());
		}
		catch(SoldOutException soe) {
			System.out.println(soe.getMessage());
		}
		catch(DrunkenException de) {
			System.out.println(de.getMessage());
		}
		catch(FullException fe){
			System.out.println(fe.getMessage());
			
		}
		try {
			restAdmin.order(cs1, rest5);
		}
		catch(NoMoneyException nme){
			System.out.println(nme.getMessage());
		}
		catch(SoldOutException soe) {
			System.out.println(soe.getMessage());
		}
		catch(DrunkenException de) {
			System.out.println(de.getMessage());
		}
		catch(FullException fe){
			System.out.println(fe.getMessage());
			
		}

		
	}

}

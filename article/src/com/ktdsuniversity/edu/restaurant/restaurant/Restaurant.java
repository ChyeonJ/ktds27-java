package com.ktdsuniversity.edu.restaurant.restaurant;

import java.util.List;

import com.ktdsuniversity.edu.restaurant.customer.Customer;
import com.ktdsuniversity.edu.restaurant.exception.DrunkenException;
import com.ktdsuniversity.edu.restaurant.exception.FullException;
import com.ktdsuniversity.edu.restaurant.exception.NoMoneyException;
import com.ktdsuniversity.edu.restaurant.exception.SoldOutException;
import com.ktdsuniversity.edu.restaurant.menu.RestaurantMenu;

public class Restaurant {
	
	private String name;
	private double drunk;
	private int full;
	private int money;
	
	//0202 컬렉션 이후로 배열은 사용하지 않는다.
//	private RestaurantMenu[] menu;
//	private Customer[] cs;
	private List<RestaurantMenu> menu;
	private List<Customer> cs;
	
	public Restaurant(String name, double drunk, int full, int money) {
		this.name = name;
		this.drunk = drunk;
		this.full = full;
		this.money = money;
	}

	public List<RestaurantMenu> getMenu() {
		return menu;
	}
	
	public void setMenu(List<RestaurantMenu> menu) {
		this.menu = menu;
	}
	
	public List<Customer> getCs() {
		return cs;
	}
	
	public void setCs(List<Customer> cs) {
		this.cs = cs;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public double getDrunk() {
		return drunk;
	}
	public void setDrunk(double drunk) {
		this.drunk = drunk;
	}
	public int getFull() {
		return full;
	}
	public void setFull(int full) {
		this.full = full;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public boolean fullCheck(Customer cs) {
		return cs.getCustomerFull() >= this.full;
	}
	
	public boolean drunkCheck(Customer cs) {
		return cs.getCustomerDrunk() >= this.drunk;
	}
	
	public void order(Customer cs, RestaurantMenu rest) {
		//고객1이 주문해
		//돈 있어?
		if (cs.getCoustomerMoney() < rest.getProductPrice()) {
			throw new NoMoneyException("잔액 부족");
		}		//재고 있어?
		else if(rest.getStock() <= 0) {
			throw new SoldOutException("재고 없음");
		}		//취했어?
		else if(drunkCheck(cs)) {
			throw new DrunkenException("취함 기준 초과");
		}		//배불러?
		else if(fullCheck(cs)) {
			throw new FullException("배부름 기준 초과");
		}
		else {		//ㅇㅋ 주문해 		//재고 빠지고, 고객 돈 나가고, 내 통장에 돈 들어옴
			cs.setCoustomerMoney(cs.getCoustomerMoney() - rest.getProductPrice());
			this.money += rest.getProductPrice();
			
			rest.setStock(rest.getStock() - 1);
			if(rest.getAlcohol() > 0) {
			cs.setCustomerDrunk(cs.getCustomerDrunk() + rest.getAlcohol() / 10);
			}
			else if(rest.getProductWeight() > 0) {
				cs.setCustomerFull(cs.getCustomerFull() + rest.getProductWeight());
			}
			System.out.println("주문성공");
			System.out.println(cs.getCoustomerMoney() + "돈 이만큼 있어");
			System.out.println(cs.getCustomerDrunk() + "이만큼 취했어");
			System.out.println(cs.getCustomerFull() + "이만큼 배불러");
			System.out.println(cs.getCustomerName() + "내 이름은");
			
		}
	}
	
	
}

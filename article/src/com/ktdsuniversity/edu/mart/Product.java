package com.ktdsuniversity.edu.mart;
/**
 * 상품 관리 클래스
 */
public class Product {
	
	//멤버변수
	private String productName;
	private int price;

	private int discountPrice;
	
	public Product(String productName, int price, int discountPrice) {
		this.productName = productName;
		this.price = price;
		this.discountPrice = discountPrice;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

}

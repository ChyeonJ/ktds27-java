package com.ktdsuniversity.edu.mart;
/**
 * 일반 마트
 * 진열한 상품을 판매
 * 판매는 구매자로부터 돈을 받고 판매하고 남은 거스름돈을 구매자에게 돌려준다.
 */
public class NomalMart {

	//멤버변수 => 할당은 따로 받지는 않으나, 손님이 내는 돈을 활용할 변수
	private int moenyBox;
	private int csMoney;
	

	private Product[] product;
	
	public NomalMart(Product product1, Product product2, Product product3) {
		this.product = new Product[3];
		this.product[0] = product1;
		this.product[1] = product2;
		this.product[2] = product3;
		
	}
	

	public int getMoenyBox() {
		return moenyBox;
	}
	
	public void setMoenyBox(int moenyBox) {
		this.moenyBox = moenyBox;
	}
	
	public int getCsMoney() {
		return csMoney;
	}
	
	public void setCsMoney(int csMoney) {
		this.csMoney = csMoney;
	}
	public Product[] getProduct() {
		return product;
	}
	
	
	public void setProduct(Product[] product) {
		this.product = product;
	}
	
	public void sell(Customer cs, String productName) {
		
		//배열 쓰려면 이게 짐이다...
		Product product = findProduct(productName);
		
		//상품 있는지 조회
		if(product == null) {
			System.out.println("없는 상품");
			return;
		}
		
		//돈 체크
		if(cs.getMoney() < product.getPrice()) {
			System.out.println("잔액 부족");
			return;
		}
		
		//결제
		totalResult(cs, product);
	}
	
	
	public Product findProduct(String productName) {
	    for (int i = 0; i < this.product.length; i++) {
	        if (this.product[i].getProductName().equals(productName)) {	//상품이름 검증
	        	return product[i];
	        }
	    }
	    return null;
	}
	
	public void totalResult(Customer cs, Product product) {
		//구매자로부터 돈을 받고 판매하고 남은 거스름돈을 구매자에게 돌려준다.
		this.moenyBox += product.getPrice();
		cs.setMoney(cs.getMoney() - product.getPrice());
		System.out.println("결제완료");
		System.out.println("거스름돈 : " + cs.getMoney());
		return;
	}
	
}
	


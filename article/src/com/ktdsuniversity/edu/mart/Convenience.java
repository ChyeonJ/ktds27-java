package com.ktdsuniversity.edu.mart;
/**
 * 편의점
 * 상속 : NomalMart
 * discount, afterDiscountPrice, NomalMart[]
 */
public class Convenience extends NomalMart {
	
	public Convenience(Product product1, Product product2, Product product3) {
		super(product1, product2, product3);
	}
	
	@Override
	public void sell(Customer cs, String productName) {
		
		//부모 클래스 상품 조회 사용
		Product product = super.findProduct(productName);

		//상품 있는지 조회
		if(product == null) {
			System.out.println("없는 상품");
			return;
		}
		
		//구매자로부터 돈을 받고 판매하고 남은 거스름돈을 구매자에게 돌려준다.
		//포인트 계산을 담아둠
		int totalPrice = pointCalcu(cs, product);
		
		//돈 체크 => 부족하면 종료
		if(cs.getMoney() < totalPrice) {
			System.out.println("금액이 부족합니다.");
			return;
		}
		
		//결제
		super.setMoenyBox(super.getMoenyBox() + totalPrice);
		cs.setMoney(cs.getMoney() - totalPrice);
		
		//포인트 적립
		addPoint(cs, product);

		
	}
	
	public int pointCalcu(Customer cs, Product product) {
		int afterAddPointPrice = product.getPrice();
		if(cs.getPoint() >= 100) {
			afterAddPointPrice -= cs.getPoint();
			cs.setPoint(0);
		}
		
		if(afterAddPointPrice < 0) {
			afterAddPointPrice = 0;
		}
		
		return afterAddPointPrice;
	}
	
	public void addPoint(Customer cs, Product product) {
		double bonusPoint = product.getPrice() * 0.001;
		cs.setPoint((int)(cs.getPoint() + bonusPoint));
		System.out.println("포인트 적립 후 최종 잔액 : " + cs.getPoint());
		System.out.println("수익금 : " + super.getMoenyBox());
		System.out.println("거스름돈 : " + cs.getMoney());
	}
	
}

package com.ktdsuniversity.edu.mart;
/**
 * 백화점
 * 고객 등급 차등할인
 * 일반 : 0.5%
 * -VIP : 3%할인 - 포인트 미적립
 * -VVIP : 10%할인 - 3%포인트 적립
 * 10000포인트 이상이면 전액 or 일부 사용가능
 */
public class Department extends NomalMart{
	

	private double discount;
	
	public Department(Product product1, Product product2, Product product3) {
		super(product1, product2, product3);
	}
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	@Override
	public void sell(Customer cs, String productName) {
		
		//부모 클래스 상품 조회
		Product product = super.findProduct(productName);
		
		//상품 있는지 조회
		if(product == null) {
			System.out.println("없는 상품");
			return;
		}
		
		//포인트 계산
		int totalPrice = pointCalcu(cs, product);
		
		//돈 체크 종료
		if(cs.getMoney() < totalPrice) {
			System.out.println("금액 부족");
			return;
		}
		
		//결제
		super.setMoenyBox(super.getMoenyBox() + totalPrice);
		cs.setMoney(cs.getMoney() - totalPrice);

		
		//포인트 적립
		addPoint(cs, product);
		System.out.println("포인트 적립 후 최종 잔액 : " + cs.getPoint());
		System.out.println("수익금 : " + super.getMoenyBox());
		System.out.println("거스름돈 : " + cs.getMoney());
		
	}
	
	public int pointCalcu (Customer cs, Product product) {
		
		int afterAddPointPrice = priceDiscount(cs, product);
		int csPoint = cs.getPoint();
		
		if(cs.getPoint() >= 10000) {
			
			if(csPoint >= afterAddPointPrice) {
				cs.setPoint(csPoint - afterAddPointPrice);
				afterAddPointPrice = 0;
			}
			else {
				afterAddPointPrice = afterAddPointPrice - csPoint;
				cs.setPoint(0);
			}
			
		}
		
		return afterAddPointPrice;
	}
	
	public int priceDiscount(Customer cs, Product product) {
		String customerGrade = cs.getGrade();
		int productPrice = product.getPrice();
		
		if(customerGrade.equals("VVIP")) {
			this.discount = product.getPrice() - (productPrice * 0.1);
			product.setDiscountPrice((int)this.discount);
			System.out.println("고객님의 할인 등급 : " + customerGrade);
			return (int)this.discount;
		}
		else if(customerGrade.equals("VIP")) {
			this.discount = product.getPrice() - (productPrice * 0.03);
			product.setDiscountPrice((int)this.discount);
			System.out.println("고객님의 할인 등급 : " + customerGrade);
			return (int)this.discount;
		}
		else if(customerGrade.equals("일반")) {
			System.out.println("고객님의 할인 등급 : " + customerGrade + "할인 대상이 아닙니다.");
			product.setDiscountPrice(productPrice);
			return productPrice;
		}
		
		return productPrice;
	}
	
	public void addPoint(Customer cs, Product product) {
		
		String customerGrade = cs.getGrade();
		double bonusPoint = product.getPrice();
		
		if(customerGrade.equals("VVIP")) {
			cs.setPoint((int)(cs.getPoint() + bonusPoint * 0.03));
			System.out.println("고객님의 적립 등급 : " + customerGrade);
			return;
		}
		else if(customerGrade.equals("VIP")) {
			System.out.println("고객님의 적립 등급 : " + customerGrade + "적립 대상이 아닙니다.");
			//포인트 안받음
			return;
		}
		else if(customerGrade.equals("일반")) {
			cs.setPoint((int)(cs.getPoint() + bonusPoint * 0.005));
			System.out.println("고객님의 적립 등급 : " + customerGrade);
			return;
		}
		
	}
	
	

}

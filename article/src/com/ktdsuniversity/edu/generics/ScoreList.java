package com.ktdsuniversity.edu.generics;

public class ScoreList<T, SUM_RESULT> {	
	
	// ScoreList에서 사용할 멤버변수
	// 점수들의 배열 <?>
	private Object[] scoreArray;	//멤버변수를 T말고 Object로 선언해준다
	
	// 배열에 몇개의 데이터가 있는지 확인
	private int size;
	
	public ScoreList() {	//제네릭이 붙어 있는 클래스의 생성자는 제네릭을 붙이지 않아도 됨
		//T[] scoreArray 초기화 : 인덱스가 2개인.
		this.scoreArray = new Object[2];	//제네릭으로는 타입이 뭔지 몰라 인스턴스 생성이 불가능하다.
											//할당 받으려면 Object로 모든 것을 할당 받겠다로 선언하면됨
	}
	
	public void add(T score) {	//T는 들어올 타입으로 파라미터를 가지겠다 라는 뜻이다
		if(this.size >= this.scoreArray.length) {
			//배열의 길이를 늘린다.
			// 1. 기존 배열의 길이보다 2개 더 많은 인덱스를 가진 배열을 새롭게 만든다.
			Object[] newArray = new Object[this.scoreArray.length + 2];
			
			// 2. 기존 배열의 데이터를 새로운 배열에 복사한다.
			//원본 배열,
			//복사를 시작할 원본 배열의 인덱스 번호,
			//복사를 받을 새로운 배열, 새로운 배열의 복사를 받을 시작 인덱스 번호,
			//복사할 개수
			System.arraycopy(this.scoreArray, 0, newArray, 0,this.scoreArray.length);
			
			//3. 새로운 	배열을 기존의 배열에 할당한다.
			this.scoreArray = newArray;
			
			
		}
		this.scoreArray[this.size++] = score;
	}
	
	public T get(int index) {
		if(this.size <= index) {
			throw new IndexOutOfBoundsException(this.size + "보다 크거나 같은 인덱스는 없습니다.");
		}
		T value = (T) this.scoreArray[index];
		return value;
	}
	
//	//반환 타입을 int로 선언하면 T도 전부 int가 되어야 한다.
//	public void sum() {	//제네릭에서는 못구한다. T가 타입이 뭔지 모르기 때문이다.
//		// 배열에 들어있는 모든 요소들의 합을 구해 반환하려 한다.
//	}
	
	public SUM_RESULT sum(Reducer<T, SUM_RESULT> reducer, SUM_RESULT defaultValue) {
		//결과 값을 저장할 변수를 만들고, 처음 받은 값(defaultValue)를 넣어둔다
		SUM_RESULT result = defaultValue;
		
		T t = null; //반복문 내부는 정의 하지 않는다.
		// 배열 처음부터 끝까지 루프를 돈다.
		for (int i = 0; i < this.size; i++) {
			t = (T) this.scoreArray[i];	//배열에서 데이터 하나 꺼낸다.
			
	        // "지금까지 계산된 값(result)"이랑 "방금 꺼낸 값(t)"을 reducer한테 던진다.
	        // reducer가 "둘이 합치면 이거야!"라고 새로 주면 그걸 다시 result에 저장(덮어쓰기)한다.
			result = reducer.reduce(t, result);
		}
		return result;
	}
	
}

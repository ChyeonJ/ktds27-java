package notepad.begin;

import java.util.ArrayList;
import java.util.List;

public class IntroduceVariable {
	
	/**
	 * 클래스의 실행을 위한 코드
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * 변수란?
		 * 
		 * 간단한 소개
		 * 계산을 위해 값을 저장하기 위한 임시의 공간.
		 * - 계산의 정의
		 *  -붙이거나
		 *  -더하거나
		 *  -뺴거나
		 *  -곱하거나
		 *  -나누기 등의 연산
		 *  
		 *  -크거나
		 *  -크거나 같거나
		 *  -작거나
		 *  -작거나 같거나
		 *  -같거나
		 *  -다르거나
		 *  
		 *  -논리식.
		 *  - 두 논리식이 모두 맞거나
		 *  - 두 논리식이 모두 틀렸거나
		 *  - 두 논리식중 하나만 맞거나
		 *  
		 *  
		 */
		
		List<Integer> lsit = new ArrayList<>();
		
		lsit.add(10);
		lsit.add(10);
		lsit.add(10);
		
		int sum = 0;
		for(int i = 0; i < lsit.size(); i++) {
			System.out.println(lsit.get(i));
			sum += lsit.get(i);
			
		}
		
		System.out.println(sum);
		
		//자바 공부
		
		
	}

}

package com.ktdsuniversity.edu.file;

public class Recursive {	//재귀 호출
	
	public void printNumber(int number) {
		System.out.println( (number) + " 스택 실행을 완료했습니다.");
		if (number > 0) {	//끝없이 콜스택이 되면 스택오버플로우 발생
			printNumber(number - 1);
		}
	}
	
	public void print(int number) {
		System.out.println("출력합니다." + number);
		if (number < 3) {	//끝없이 콜스택이 되면 스택오버플로우 발생
			print(number + 1);
			System.out.println( (number) + " 스택 실행을 완료했습니다.");
		}
	}
	
	public int sumToZero(int start) {
		//start ==> 5
		// > start == 1
		if (start == 1) {
			return 1;
		}
		
		return start * sumToZero(start - 1);
	}
	
	public static void main(String[] args) {
		Recursive r = new Recursive();
		//r.print(1);
		r.printNumber(2000); // 결과 => 2000 ~ 0
		//int result = r.sumToZero(5);
		//System.out.println(result);
	}

}

package com.ktdsuniversity.edu.file;

import java.io.File;

public class Recursive {	//재귀 호출
	
	public void deleteDirectory(File target) {
		if(target.isFile()) {
			System.out.println(target.getAbsolutePath());
			target.delete();
		} else if(target.isDirectory()) {
			//폴더 내부의 목록을 조회한다.
			File[] children = target.listFiles(); //파일을 배열로 받아온다
			
			for(File child : children) {
				//System.out.println(child);	//.getAbsolutePath() / 폴더나 파일의 절대 경로를 알려준다
				this.deleteDirectory(child);
			}
			
			System.out.println(target.getAbsolutePath());
			target.delete();
		}
	}
	
	public void printFiles(File target) {
		
		if(target.isFile()) {
			System.out.println(target.getAbsolutePath());
		} else if(target.isDirectory()) {
			//폴더 내부의 목록을 조회한다.
			File[] children = target.listFiles(); //파일을 배열로 받아온다
			
			for(File child : children) {
				//System.out.println(child);	//.getAbsolutePath() / 폴더나 파일의 절대 경로를 알려준다
				this.printFiles(child);
			}
		}
		
	}
	
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
		//r.printNumber(2000); // 결과 => 2000 ~ 0
		//int result = r.sumToZero(5);
		//System.out.println(result);
		
		//금일 수업에서는  user_home경로가 아닌 C밑에 우리의 워크스페이스에서 진행할 거여서 아래처럼 작성
//		File root = new File("C:/DevPrograms");
//		r.printFiles(root);
		
		File root = new File("C:\\Devprograms\\삭제대상");
		r.deleteDirectory(root);
		
	}

}

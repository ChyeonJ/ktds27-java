package com.ktdsuniversity.edu.generics.collections.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class School {
	
	public static void printStudents(Map<Integer, List<Student>> classes, int classNum) {
		
		List<Student> students = classes.get(classNum);
		
		if(students != null) {
			
			for(Student student : students) {
				System.out.println(classNum + "반의 학생" + student);
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		
		Map<Integer, List<Student>> classes = new HashMap<>();
		
		classes.put(1, new ArrayList<>());
		classes.put(2, new ArrayList<>());
		
		List<Student> students = classes.get(1);
		students.add(new Student(1, "김범수"));
		students.add(new Student(2, "나얼"));
		students.add(new Student(3, "박효신"));
		students.add(new Student(4, "이수"));
		
		printStudents(classes, 0);
		printStudents(classes, 1);
//		printStudents(classes, 2);
//		printStudents(classes, 3);
		
//		System.out.println(classes);
//		System.out.println("사이즈는 " + classes.size());	//=> 키값을 세기 떄문에 2개가 나옴
		
		//리스트는 반복을 시킬 수 있지만 맵은 반복을 시킬 수 없다./ 굳이 아래처럼 반복을 쓰지 않는다.
//		for (Integer key : classes.keySet()) {
//			System.out.println(classes.get(key));
//		}
		
		
	}

}

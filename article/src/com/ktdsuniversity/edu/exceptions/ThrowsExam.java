package com.ktdsuniversity.edu.exceptions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.ktdsuniversity.edu.mart.Customer;

public class ThrowsExam {
	
	
	/**
	 *리플렉션 스킬
	 * createNewInstance("om.ktdsuniversity.edu.restaurant.customer")
	 * 	=> Customer의 인스턴스가 반환.
	 * @param classPath
	 * @return
	 */
	public static Object createNewInstance(String classPath) {
		
		
		Class clazz = null;
		// classPath : "om.ktdsuniversity.edu.restaurant.customer" 이게 들어감
		try {
			clazz = Class.forName(classPath);
		}
		catch(ClassNotFoundException cnfe){
			System.out.println(classPath + ": 존재하지 않는 클래스입니다.");
		}
		
		if(clazz != null) {
			Constructor constructor = null;
			try {
															//생성자를 가지고 와라
				constructor =  clazz.getConstructor(String.class, double.class, int.class, int.class);
			}
			catch(NoSuchMethodException nsme) {
				System.out.println("생성자를 찾을 수 없습니다.");
			}
			
			if(constructor != null) {
				try {
					Object instance = constructor.newInstance("리플렉트", 10d, 123, 123);
					return instance;
				} catch (InstantiationException e) {
					System.out.println("인스턴스 생성 실패");
				} catch (IllegalAccessException e) {
					System.out.println("접근 권한이 없스빈다.");
				} catch (IllegalArgumentException e) {
					System.out.println("생성 파라미터가 잘못되었습니다,");
				} catch (InvocationTargetException e) {
					System.out.println("생성자를 실행할 때 에러가 발생했습니다.");
				}
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Customer cust = (Customer) createNewInstance("com.ktdsuniversity.edu.restaurant.customer");
		System.out.println(cust.getMoney());
	}
	
}

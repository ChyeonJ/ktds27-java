package com.ktdsuniversity.edu.common.utils;

public class ObjectUtils {

	private ObjectUtils() {
	}

	public static boolean isNull(Object ... object) {
		for (Object obj : object) {
			if (obj == null) {
				return true;
			}
		}
		return false;
	}
	
	// 가변길이 파라미터 복수개의 파라미터를 무한대로 넣을 수 있음 ...
	public static boolean isNotNull(Object ... object) {
		for (Object obj : object) {
			if (obj == null) {
				return false;
			}
		}
		return true;
	}

}

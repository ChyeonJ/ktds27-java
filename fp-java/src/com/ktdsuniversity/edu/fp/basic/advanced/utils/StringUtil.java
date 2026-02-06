package com.ktdsuniversity.edu.fp.basic.advanced.utils;

public abstract class StringUtil {
	
	//생성자 숨김
	public StringUtil() {}
	
	public static int toInt(String str) {
		if(str == null) {
			return 0;
		}
		
		try {
			return Integer.parseInt(str);
		}
		catch (NumberFormatException nfe) {
			return 0;	
		}
		
	}

}

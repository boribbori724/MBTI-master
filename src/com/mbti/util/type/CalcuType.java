package com.mbti.util.type;

public class CalcuType {

	public String ei(String str) {
		
		if(Long.parseLong(str) > 2) {
			
			str = "E";
			
		} else {
			
			str = "I";
			
		}
		
		return str;
		
	}
	
	public String sn(String str) {
		
		if(Long.parseLong(str) > 2) {
			
			str = "S";
			
		} else {
			
			str = "N";
			
		}
		
		return str;
		
	}

	public String tf(String str) {
		
		if(Long.parseLong(str) > 2) {
			
			str = "T";
			
		} else {
			
			str = "F";
			
		}
		
		return str;
		
	}
	
	public String jp(String str) {
		
		if(Long.parseLong(str) > 2) {
			
			str = "J";
			
		} else {
			
			str = "P";
			
		}
		
		return str;
		
	}
	
}

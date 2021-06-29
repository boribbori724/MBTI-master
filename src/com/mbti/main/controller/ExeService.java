/*
 * 서비스를 실행시켜 주며 실핼 시키는 객체를 보여주는 Class
 * 작성자 : 궁금해조
 * 작성일 : 2021.03.21
 * ver_1.0
 */
package com.mbti.main.controller;

public class ExeService {

	public static Object execute(Service service, Object obj) throws Exception {
		
		System.out.println("실행되는 객체  : " + service.getClass().getSimpleName() + ".service");
		
		System.out.println("전달되는 data : " + obj);
		
		Object result = service.service(obj);
		
		System.out.println("실행한 결과 : " + result);
		
		return result;
		
	}
	
}

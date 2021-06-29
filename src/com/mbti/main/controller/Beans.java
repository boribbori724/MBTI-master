/**
 *  Service, Controller, DAO를 저장및 사용시켜주는 Class
 *  작성자 : 궁금해조
 *  작성일 : 2021.03.21
 *  ver_1.0
 */
package com.mbti.main.controller;

import java.util.HashMap;
import java.util.Map;

public class Beans {

	private static Map<String, Service> serviceMap = new HashMap<>();
	
	private static Map<String, Controller> controllerMap = new HashMap<>();
	
	private static Map<String, Object> daoMap = new HashMap<>();
	
	public static Service getService(String key) {	// service객체 가져오기
		
		return serviceMap.get(key);
		
	}
	
	public static void putService(String key, Service service) {	// Service객체에 넣기
		
		serviceMap.put(key, service);
		
	}
	
	public static Controller getController(String key) {	// Controller 객체 가져오기
		
		return controllerMap.get(key);
		
	}
	
	public static void putController(String key, Controller controller) {	// controller 객체 넣기
		
		controllerMap.put(key, controller);
		
	}
	
	public static Object getDAO(String key) {	// dao 객체 가져오기
		
		return daoMap.get(key);
		
	}
	
	public static void putDAO(String key, Object dao) {	// dao객체 넣기
		
		daoMap.put(key, dao);
		
	}

	public static Service get(String key) {
		return serviceMap.get(key);
	}
	
}

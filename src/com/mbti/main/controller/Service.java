/**
 *  DAO를 저장하고 처리문을 실행시키는 InterFace
 *  작성자 : 궁금해조
 *  작성일 : 2021.03.21
 *  ver_1.0
 */
package com.mbti.main.controller;

public interface Service {

	public Object service(Object obj) throws Exception;		// 처리문을 실행하는 method
	
	public void setDAO(Object dao);		// DAO 저장하는 method
	
}

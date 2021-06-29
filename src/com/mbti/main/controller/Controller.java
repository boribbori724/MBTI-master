/**
 * Controller를 실행시켜주는 InterFace
 * 작성자 : 궁금해조
 * 작성일 : 2021.03.21
 * ver_1.0
 */
package com.mbti.main.controller;

import javax.servlet.http.HttpServletRequest;

public interface Controller {

	public String execute(HttpServletRequest request) throws Exception;	// Controller를 실행
	
}

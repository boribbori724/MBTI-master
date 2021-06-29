package com.mbti.main.controller;

import javax.servlet.http.HttpServletRequest;

import com.mbti.util.filter.AuthorityFilter;

public class MainController implements Controller {

	private String jspInfo = null;
	
	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		switch (AuthorityFilter.url) {
		
			case "/main.do" :
				
				jspInfo = "redirect:/main/main.do";
				
				break;
				
			case "/main/main.do" :
				
				jspInfo = "/main/main";
				
				break;
				
			default:
				
				throw new Exception("404 Not Found : 존재하지 않는 페이지 입니다.");
			
		}
		
		return jspInfo;
	}

}

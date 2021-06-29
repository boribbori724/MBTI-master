package com.mbti.error.controller;

import javax.servlet.http.HttpServletRequest;

import com.mbti.main.controller.Controller;
import com.mbti.util.filter.AuthorityFilter;

public class ErrorController implements Controller {

	private final String MODULE = "error";
	
	private String jspInfo = null;
		
	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		switch(AuthorityFilter.url) {
		
			case "/" + MODULE + "/auth_error.do" :
				
				jspInfo = MODULE + "/auth_error";
			
				break;
		
		}
		
		return jspInfo;
		
	}

}

package com.mbti.hit.controller;

import javax.servlet.http.HttpServletRequest;

import com.mbti.main.controller.Beans;
import com.mbti.main.controller.Controller;
import com.mbti.main.controller.ExeService;
import com.mbti.util.filter.AuthorityFilter;

public class HitController implements Controller {

	private final String MODULE = "mbti";
	
	private String jspInfo = null;
	
	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		
		switch (AuthorityFilter.url) {
		
			case "/" + MODULE + "/movieMBTI.html":
				
				increase(request);
			
				jspInfo = MODULE + "/movieMBTI";
			
				break;

		default:
			break;
		}
		
		return jspInfo;
	}


	private void increase(HttpServletRequest request) throws Exception {
		
		ExeService.execute(Beans.getService(AuthorityFilter.url), Long.parseLong(request.getParameter("no")));
		
	}
}

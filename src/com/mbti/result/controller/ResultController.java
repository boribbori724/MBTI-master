package com.mbti.result.controller;

import javax.servlet.http.HttpServletRequest;

import com.mbti.main.controller.Beans;
import com.mbti.main.controller.Controller;
import com.mbti.main.controller.ExeService;
import com.mbti.type.vo.TypeVO;
import com.mbti.util.filter.AuthorityFilter;
import com.mbti.util.type.CalcuType;

public class ResultController implements Controller {

	private final String MODULE = "result";
	
	private String jspInfo = null;
	
	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("resultController 실행");
		
		switch (AuthorityFilter.url) {
		 
			case "/" +  MODULE + "/result.do" :
				
				view(request);
				
				jspInfo = MODULE + "/result";
			
				System.out.println(jspInfo);
				
				break;
				
			case "/mbti/movieMBTI.html" :
				
				jspInfo = "mbti:/mbti/movieMBTI";
				
				break;
				
			default:

				throw new Exception("MainController - 404 Not Found : 존재하지 않는 URL입니다.");
					
		}
		
		return jspInfo;
		
	}

	private void view(HttpServletRequest request) throws Exception {

		CalcuType calcuType = new CalcuType();
		
		String EI = calcuType.ei(request.getParameter("EI"));
		
		String SN = calcuType.sn(request.getParameter("SN"));
		
		String TF = calcuType.tf(request.getParameter("TF"));
		
		String JP = calcuType.jp(request.getParameter("JP"));

		String type = EI + SN + TF + JP;
		
		System.out.println(type);
		
		System.out.println(request.getParameter("no"));

		TypeVO vo = (TypeVO) ExeService.execute(Beans.getService(AuthorityFilter.url), new Object[] {Long.parseLong(request.getParameter("no")), type});
		
		request.setAttribute("vo", vo);
		
	}
	
}

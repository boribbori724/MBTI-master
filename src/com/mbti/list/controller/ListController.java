package com.mbti.list.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.mbti.list.vo.ListVO;
import com.mbti.main.controller.Beans;
import com.mbti.main.controller.Controller;
import com.mbti.main.controller.ExeService;
import com.mbti.util.filter.AuthorityFilter;
import com.mbti.util.page.PageObject;

public class ListController implements Controller {

	private final String MODULE = "list";
	
	private String jspInfo = null;
	
	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		PageObject pageObject = PageObject.getInstance(request);
		
		request.setAttribute("pageObject", pageObject);
		
		switch (AuthorityFilter.url) {
		
			case "/" + MODULE + "/list.do" :
				
				list(request, pageObject); 
			
				jspInfo = MODULE + "/list";
				
				break;
				
			case "/" + MODULE + "/adminList.do" :
				
				adminList(request, pageObject); 
			
				jspInfo = MODULE + "/adminList";
			
				break;
				
			case "/" + MODULE + "/view.do" :
				
				view(request);
			
				jspInfo = MODULE + "/view";
			
				break;

			case "/" + MODULE + "/registerForm.do" : 
				
				jspInfo = MODULE + "/registerForm";
			
				break;
				
			case  "/" + MODULE + "/register.do" :
				
				register(request);
			
				jspInfo = "redirect:adminList.do?page=1&perPageNum" + + pageObject.getPerPageNum();
				
				break;
				
			case "/" + MODULE + "/updateForm.do" :
				
				updateForm(request);
			
				jspInfo = MODULE + "/updateForm"; 
				
				break;
			
			case "/" + MODULE + "/update.do" :
				
				long no = update(request);
			
				jspInfo = "redirect:view.do?no=" + no + "&page=" + pageObject.getPage() + "&perPageNum=" + pageObject.getPerPageNum() ;
				
				break;
				
			case "/" + MODULE + "/delete.do" :
				
				delete(request);
				
				jspInfo = "redirect:adminList.do?" + request.getQueryString();
				
				break;
				
			default:
				
				throw new Exception("404 Not Found : 존재하지 않는 페이지 입니다.");
			
		}
		
		
		return jspInfo;
	}

	private void list(HttpServletRequest request, PageObject pageObject) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<ListVO> list = (List<ListVO>) ExeService.execute(Beans.getService(AuthorityFilter.url), pageObject);
		
		request.setAttribute("list", list);
		
	}
	
	private void adminList(HttpServletRequest request, PageObject pageObject) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<ListVO> list = (List<ListVO>) ExeService.execute(Beans.getService("/list/list.do"), pageObject);
		
		request.setAttribute("list", list);
		
	}
	
	private void register(HttpServletRequest request) throws Exception {
		
		System.out.println(request.getParameter("title"));
		
		ListVO vo = new ListVO();
		
		vo.setTitle(request.getParameter("title"));
		vo.setImage(request.getParameter("image"));
		vo.setUrl(request.getParameter("url"));
		
		ExeService.execute(Beans.getService(AuthorityFilter.url), vo);
		
	}
	
	private void delete(HttpServletRequest request) throws Exception {
		
		ExeService.execute(Beans.getService(AuthorityFilter.url), Long.parseLong(request.getParameter("no")));
		
	}
	
	private void view(HttpServletRequest request) throws Exception {
		
		ListVO vo = (ListVO) ExeService.execute(Beans.getService(AuthorityFilter.url), Long.parseLong(request.getParameter("no")));
		
		request.setAttribute("vo", vo);
		
	}
	
	private void updateForm(HttpServletRequest request) throws Exception {
		
		ListVO vo = (ListVO) ExeService.execute(Beans.getService("/list/view.do"), Long.parseLong(request.getParameter("no")));
		
		request.setAttribute("vo", vo);
		
	}
	
	private long update(HttpServletRequest request) throws Exception {
		
		long no = Long.parseLong(request.getParameter("no"));
		
		
		
		ListVO vo = new ListVO();
		
		vo.setNo(no);
		vo.setTitle(request.getParameter("title"));
		vo.setImage(request.getParameter("image"));
		vo.setUrl(request.getParameter("url"));
		
		ExeService.execute(Beans.getService(AuthorityFilter.url), vo);
		
		return no;
		
	}
	
}

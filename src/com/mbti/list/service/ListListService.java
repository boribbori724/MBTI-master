package com.mbti.list.service;

import com.mbti.list.dao.ListDAO;
import com.mbti.main.controller.Service;
import com.mbti.util.page.PageObject;

public class ListListService implements Service {

	ListDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		int totalRow = (int) dao.getTotalRow();
		
		PageObject pageObject = (PageObject) obj;
		
		pageObject.setTotalRow(totalRow);
		
		return dao.list(pageObject);
		   
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		
		this.dao = (ListDAO) dao;

	}

}

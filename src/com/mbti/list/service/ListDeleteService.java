package com.mbti.list.service;

import com.mbti.list.dao.ListDAO;
import com.mbti.main.controller.Service;

public class ListDeleteService implements Service {

	ListDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		return dao.delete((long) obj);
		   
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		
		this.dao = (ListDAO) dao;

	}

}

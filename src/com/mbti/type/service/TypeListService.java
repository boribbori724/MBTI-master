package com.mbti.type.service;

import com.mbti.main.controller.Service;
import com.mbti.type.dao.TypeDAO;
import com.mbti.util.page.PageObject;

public class TypeListService implements Service {

	private TypeDAO dao;
	
	public TypeListService() {
		System.out.println("TypeListService()- 생성완료");
	}
	@Override
	public void setDAO(Object dao) {
		System.out.println("TypeListService.setDAO().dao : " + dao);
		this.dao = (TypeDAO) dao;
	}
	
	@Override
	public Object service(Object obj) throws Exception {
		System.out.println("TypeListService.obj : " + obj);
		((PageObject) obj).setTotalRow(dao.getTotalRow());
		return dao.list((PageObject)obj);
	}
	
}

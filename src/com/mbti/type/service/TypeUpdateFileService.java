package com.mbti.type.service;

import com.mbti.type.dao.TypeDAO;
import com.mbti.type.vo.TypeVO;
import com.mbti.main.controller.Service;

public class TypeUpdateFileService implements Service{

	private TypeDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateFile((TypeVO) obj);
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		this.dao = (TypeDAO) dao;
		
	}

}

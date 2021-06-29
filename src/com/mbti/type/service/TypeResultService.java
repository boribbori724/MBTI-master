package com.mbti.type.service;

import com.mbti.list.dao.ListDAO;
import com.mbti.main.controller.Service;


public class TypeResultService implements Service{

	//dao가 필요하다. 밖에서 생성한 후 넣어준다. - 1. 생성자. 2. setter()
	private ListDAO dao;

	// 기본 생성자 만들기 -> 확인 시 필요
	public TypeResultService() {
		
		System.out.println("TypeViewService() - 생성 완료");
		
	}
	
	
	@Override
	public void setDAO(Object dao) {
		
		System.out.println("TypeViewService.setDAO().dao : " + dao);
		
		this.dao = (ListDAO) dao;
		
	}
	
	// url 요청에 따른 처리
	// 넘어오는 데이터가 TypeVO ==> obj
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 넘어오는 데이터 확인
		
		Object[] objs = (Object[]) obj;
		
		dao.increase((long) objs[0]);

		return dao.result((String) objs[1]);
		
	}

}

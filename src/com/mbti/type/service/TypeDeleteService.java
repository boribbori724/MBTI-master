package com.mbti.type.service;

import com.mbti.type.dao.TypeDAO;
import com.mbti.main.controller.Service;

  
public class TypeDeleteService implements Service{

	//dao가 필요하다. 밖에서 생성한 후 넣어준다. - 1. 생성자. 2. setter()
	private TypeDAO dao;

	// 기본 생성자 만들기 -> 확인 시 필요
	public TypeDeleteService() {
		System.out.println("TypeDeleteService() - 생성 완료");
	} 
	
	
	@Override
	public void setDAO(Object dao) {
		System.out.println("TypeDeleteService.setDAO().dao : " + dao);
		this.dao = (TypeDAO) dao;
	}
	  
	// url 요청에 따른 처리
	// 넘어오는 데이터가 TypeVO ==> obj
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 넘어오는 데이터 확인
		System.out.println("TypeDeleteService.obj : " + obj);
		return dao.delete((Long)obj); 
	} 
 
}

package com.mbti.feedback.service;

import com.mbti.feedback.dao.FeedbackDAO;
import com.mbti.main.controller.Service;

import com.mbti.util.page.PageObject;

public class FeedbackAdminListService implements Service{

	//dao가 필요하다. 밖에서 생성한 후 넣어준다. - 1. 생성자. 2. setter()
	private FeedbackDAO dao;
	
	// 기본 생성자 만들기 -> 확인 시 필요하다.
	public FeedbackAdminListService() {
		// TODO Auto-generated constructor stub
		System.out.println("FeedbackAdminListService.FeedbackAdminListService() - 생성 완료");
	}
	
	@Override
	public void setDAO(Object dao) {
		System.out.println("FeedbackAdminListService.setDAO().dao : " + dao);
		this.dao = (FeedbackDAO) dao;
	}
	
	// url 요청에 따른 처리
	// 넘어오는 데이터가 PageObject ==> obj
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 넘어오는 데이터 확인
		System.out.println("FeedbackAdminListService.obj : " + obj);
		// 전체 데이터를 가져오기
		long totalRow = dao.adminGetTotalRow();
		PageObject pageObject = (PageObject) obj;
		pageObject.setTotalRow(totalRow);
		// 전체 페이지 셋팅 후 페이지 객체 출력
		System.out.println("FeedbackAdminListService.pageObject : " + pageObject);
		return dao.adminList(pageObject);
	}

}

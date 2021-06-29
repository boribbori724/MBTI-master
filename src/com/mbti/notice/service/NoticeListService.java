package com.mbti.notice.service;

import com.mbti.main.controller.Service;
import com.mbti.notice.dao.NoticeDAO;
import com.mbti.util.page.PageObject;

public class NoticeListService implements Service{
	// dao가 필요하므로 밖에서 생성
	NoticeDAO dao;
	// 기본 생성자 만들기
	public NoticeListService() {
		System.out.println("NoticeListSerivce.NoticeListService() - 생성 확인");
	}
	
	@Override
	public void setDAO(Object dao) {
		//System.out.println("NoticeListService.setDAO().dao : " + dao);
		this.dao = (NoticeDAO) dao;
	}
	// url 요청에 따른 처리
	// 넘어오는 데이터가 PageObject ==> obj
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 넘어오는 데이터 확인
		System.out.println("NoticeListService.obj : " + obj);
		// 전체 데이터 가져오기
		//long totalRow = dao.getTotalRow(pageObject);
		PageObject pageObject = (PageObject) obj;
		long totalRow = dao.getTotalRow(pageObject);
		pageObject.setTotalRow(totalRow);
		// 전체 페이지 세팅후 페이지 객체 출력
		System.out.println("NoticeListService.pageObject : " + pageObject);
		return dao.list(pageObject);
	}

}



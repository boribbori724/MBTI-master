package com.mbti.board.service;

import com.mbti.board.dao.BoardDAO;
import com.mbti.main.controller.Service;
import com.mbti.util.page.PageObject;

public class BoardListService implements Service {
	
	//dao가 필요하다. 밖에서 생성한 후 넣어준다. - 1. 생성자. 2. setter()
	private BoardDAO dao;
	
	// 기본 생성자 만들기 -> 확인 시 필요
	public BoardListService() {
		System.out.println("BoardListService() - 생성 완료");
	}

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		//넘어오는 데이터를 확인
		System.out.println("BoardListService.obj : " + obj);
		//전체 데이터 가져오기
		int totalRow = (int) dao.getTotalRow();
		PageObject pageObject = (PageObject) obj;
		pageObject.setTotalRow(totalRow);
		//전체 페이지 세팅 후 페이지 객체 출력
		System.out.println("BoardListService.pageObject : " + pageObject);
		
		return dao.list(pageObject);
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		System.out.println("BoardListService.setDAO().dao : " + dao);
		this.dao = (BoardDAO) dao;
	}

}

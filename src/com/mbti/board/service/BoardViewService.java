package com.mbti.board.service;

import com.mbti.board.dao.BoardDAO;
import com.mbti.main.controller.Service;

public class BoardViewService implements Service {

	//dao가 필요하다. 밖에서 생성한 후 넣어준다. - 1. 생성자. 2. setter()
	private BoardDAO dao;
	
	// 기본 생성자 만들기 -> 확인 시 필요
	public BoardViewService() {
		System.out.println("BoardViewService() - 생성 완료");
	}
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		//글보기(list->view)의 경우는 조회수 1이 증가해야하고 글수정(update->view)의 경우에는 조회수 1증가가 일어나면 안 된다
		//데이터가 두 개가 넘어오게 된다(class나 배열(배열로 캐스팅해서 사용할 것)을 사용할 수 있다)
		//obj[0] - no / obj[1] - inc
		Object[] objs = (Object[]) obj;
		Long no = (Long) objs[0];
		Long inc = (Long) objs[1];
		
		if(inc ==1) dao.increase(no);
		return dao.view(no);
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		System.out.println("BoardViewService.setDAO().dao : " + dao);
		this.dao = (BoardDAO) dao;
	}

}

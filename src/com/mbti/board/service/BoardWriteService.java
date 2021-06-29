package com.mbti.board.service;

import com.mbti.board.dao.BoardDAO;
import com.mbti.board.vo.BoardVO;
import com.mbti.main.controller.Service;

public class BoardWriteService implements Service {
	
	//dao가 필요하다. 밖에서 생성한 후 넣어준다. - 1. 생성자. 2. setter()
	//writrForm -> write -> BoardWriteService -> Init
	//실행은 서버가 시작될 때 시작된다
	private BoardDAO dao;

	// 기본 생성자 만들기 -> 확인 시 필요
	public BoardWriteService() {
		System.out.println("BoardWriteService() - 생성 완료");
	} 	
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 넘어오는 데이터 확인
		System.out.println("BoardWriteService.obj : " + obj);
		return dao.write((BoardVO) obj);
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		System.out.println("BoardWriteService.setDAO().dao : " + dao);
		this.dao=(BoardDAO) dao;		
	}

}

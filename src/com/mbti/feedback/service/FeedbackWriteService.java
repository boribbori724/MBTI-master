package com.mbti.feedback.service;

import com.mbti.feedback.dao.FeedbackDAO;
import com.mbti.feedback.vo.FeedbackVO;
import com.mbti.main.controller.Service;

public class FeedbackWriteService implements Service{

	//dao가 필요하다. 밖에서 생성한 후 넣어준다. - 1. 생성자. 2. setter()
	private FeedbackDAO dao;
	
	// 기본 생성자 만들기 -> 확인 시 필요하다.
	public FeedbackWriteService() {
		// TODO Auto-generated constructor stub
		// 서버가 시작될 때 확인 - 안나오면 Init.inti()
		System.out.println("FeedbackWriteService.FeedbackWriteService() - 생성 완료");
	}
	
	@Override
	public void setDAO(Object dao) {
		// Init.init() 조립을 할때 dao 확인 - null이면 안된다.(서버가 시작될때 확인)
		System.out.println("FeedbackWriteService.setDAO().dao : " + dao);
		// 받아온 dao를 저장한다.
		this.dao = (FeedbackDAO) dao;
	}
	
	// url 요청에 따른 처리
	// 넘어오는 데이터가 FeedbackVO ==> obj
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 넘어오는 데이터 확인
		System.out.println("FeedbackWriteService.obj : " + obj);
		// dao의 write()를 실행해서 결과를 리턴해 준다.
		return dao.write((FeedbackVO) obj);
	}

}

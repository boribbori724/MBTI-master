package com.mbti.feedback.service;

import com.mbti.main.controller.Service;
import com.mbti.feedback.dao.FeedbackDAO;
import com.mbti.feedback.vo.FeedbackVO;

public class FeedbackAnswerService implements Service {
	
	// dao가 필요하다. 밖에서 생성한 후 넣어준다. - 1. 생성자 2. setter()
	FeedbackDAO dao;
	
	public FeedbackAnswerService() {
		System.out.println("FeedbackDAO.FeedbackListService() - 생성완료");
	}
	
	@Override
	
	public void setDAO(Object dao) {
		this.dao = (FeedbackDAO) dao;
	}

	// url 요청에 따른 처리
	// 넘어오는 데이터가 PageObject ==> obj
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 넘어오는 데이터 확인
		dao.increaseOrdNo((FeedbackVO) obj); // 순서 번호에 맞는 자리를 비워 놓는다.
		return dao.answer((FeedbackVO) obj);
	}
 
}

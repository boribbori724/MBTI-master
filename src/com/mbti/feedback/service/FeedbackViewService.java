package com.mbti.feedback.service;

import com.mbti.feedback.dao.FeedbackDAO;
import com.mbti.main.controller.Service;

public class FeedbackViewService implements Service {
	
	// dao가 필요하다. 밖에서 생성한 후 넣어준다. - 1. 생성자 2. setter()
	private FeedbackDAO dao;
	@Override
	
	public void setDAO(Object dao) {
		
		System.out.println("FeadBackDAO : " + dao);
		
		this.dao = (FeedbackDAO) dao;
	}

	@Override
	public Object service(Object obj) throws Exception {
		return dao.view((Long) obj);
	}

}

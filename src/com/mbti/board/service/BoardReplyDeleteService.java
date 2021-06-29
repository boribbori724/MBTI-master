package com.mbti.board.service;

import com.mbti.board.dao.BoardReplyDAO;
import com.mbti.main.controller.Service;

public class BoardReplyDeleteService implements Service {

	private BoardReplyDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return  dao.replyDelete((Long) obj);
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		this.dao=(BoardReplyDAO) dao;
	}

}

package com.mbti.notice.service;

import com.mbti.notice.dao.NoticeReplyDAO;
import com.mbti.main.controller.Service;

public class NoticeReplyDeleteService implements Service {

	private NoticeReplyDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return  dao.replyDelete((Long) obj);
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		this.dao=(NoticeReplyDAO) dao;
	}

}

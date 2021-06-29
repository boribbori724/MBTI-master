package com.mbti.notice.service;

import com.mbti.notice.dao.NoticeReplyDAO;
import com.mbti.notice.vo.NoticeReplyVO;
import com.mbti.main.controller.Service;

public class NoticeReplyWriteService implements Service{
	private NoticeReplyDAO dao;
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.replyWrite((NoticeReplyVO) obj);
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		this.dao=(NoticeReplyDAO) dao;
	}

}

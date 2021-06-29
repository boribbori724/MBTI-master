package com.mbti.board.service;

import java.util.Arrays;

import com.mbti.board.dao.BoardReplyDAO;
import com.mbti.main.controller.Service;
import com.mbti.util.page.PageObject;

public class BoardReplyListService implements Service{
	
	private BoardReplyDAO dao;

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 넘어오는 데이터 확인
		Object[] objs = (Object[])obj;
		System.out.println("BoardReplyListService.obj : " + Arrays.toString(objs));
		// 배열로 되어 있는 것은 순서에 맞게 데이터 분할 [0] - no, [1] -pageObject
		Long no = (Long) objs[0];
		PageObject pageObject = (PageObject) objs[1];
		
		// 전체 데이터를 가져오기
		int replyTotalRow = (int) dao.getReplyTotalRow(no);
		pageObject.setTotalRow(replyTotalRow);
		// 전체 페이지 셋팅 후 페이지 객체 출력
		System.out.println("BoardReplyListService.pageObject : " + pageObject);
		return dao.replyList(no, pageObject);
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		this.dao = (BoardReplyDAO) dao;	
	}
}

package com.mbti.feedback.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mbti.feedback.vo.FeedbackVO;
import com.mbti.main.controller.Beans;
import com.mbti.main.controller.Controller;
import com.mbti.main.controller.ExeService;
import com.mbti.member.vo.LoginVO;
import com.mbti.util.page.PageObject;
import com.mbti.util.filter.AuthorityFilter;

public class feedbackController implements Controller {
	
	private final String MODULE = "feedback";
	private String jspInfo = null;

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		
		PageObject pageObject = PageObject.getInstance(request);
		request.setAttribute("pageObject", pageObject);

		switch (AuthorityFilter.url) {
		case "/" + MODULE + "/list.do":
			list(request, pageObject);
		jspInfo = MODULE + "/list";
		break;

		case "/" + MODULE + "/adminList.do":
			adminList(request, pageObject);
		jspInfo = MODULE + "/adminList";
		break;
		
		case "/" + MODULE + "/view.do" :
			view(request);
		jspInfo = MODULE + "/view";
		break;
		
		case "/" + MODULE + "/write.do":
			write(request);
		jspInfo = "redirect:list.do";
		break;

		case "/" + MODULE + "/writeForm.do":
		jspInfo = MODULE + "/writeForm";
		break;

		case "/" + MODULE + "/answer.do":
			answer(request);
		jspInfo = "redirect:adminList.do";
		break;
		
		case "/" + MODULE + "/answerForm.do":
			answerForm(request);
			jspInfo = MODULE + "/answerForm";
		break;
		
		case "/" + MODULE + "/delete.do":
			delete(request);
		jspInfo = "redirect:adminList.do";
		break;

		default:
			break;
		}
		
		return jspInfo;
	}
	
	private void list(HttpServletRequest request, PageObject pageObject) throws Exception {
		
		pageObject.setAccepter(((LoginVO) request.getSession().getAttribute("login")).getId());

		@SuppressWarnings("unchecked")
		List<FeedbackVO> list = (List<FeedbackVO>) ExeService.execute(Beans.get(AuthorityFilter.url), pageObject);

		request.setAttribute("list", list);
		request.setAttribute("pageObject", pageObject);	
		
	}
	
	private void adminList(HttpServletRequest request, PageObject pageObject) throws Exception {
		
		pageObject.setAccepter(((LoginVO) request.getSession().getAttribute("login")).getId());
		
		@SuppressWarnings("unchecked")
		List<FeedbackVO> list = (List<FeedbackVO>) ExeService.execute(Beans.get(AuthorityFilter.url), pageObject);
		
		request.setAttribute("list", list);
		request.setAttribute("pageObject", pageObject);	
		
	}
	
	private void view(HttpServletRequest request) throws Exception {

		// 넘어오는 데이터 받기 - 글 번호
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);

		FeedbackVO vo = (FeedbackVO)ExeService.execute(Beans.get(AuthorityFilter.url),no);
		// 서버객체 request에 담는다.
		request.setAttribute("vo", vo);
	}
	
	private void write(HttpServletRequest request) throws Exception {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
//		String accepter = request.getParameter("accepter");

		LoginVO vo = (LoginVO) request.getSession().getAttribute("login");
		String sender = vo.getId();

		FeedbackVO feedbackVO = new FeedbackVO();
		feedbackVO.setTitle(title);
		feedbackVO.setContent(content);
		feedbackVO.setSender(sender);
//		feedbackVO.setAccepter(accepter);

		ExeService.execute(Beans.get(AuthorityFilter.url), feedbackVO);
	}
	
	private void answer(HttpServletRequest request) throws Exception {
		String strNo = request.getParameter("no");
//		long no = Long.parseLong(strNo);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String sender = request.getParameter("sender");

		String strRefNo = request.getParameter("refNo");
		String strOrdNo = request.getParameter("ordNo");
		String strLevNo = request.getParameter("levNo");
		// LoginVO vo = (LoginVO) session.getAttribute("login");
		// String id = vo.getId();
//		String id = ((LoginVO) session.getAttribute("login")).getId();

		// vo객체를 만들어서 넣는다.
		FeedbackVO vo = new FeedbackVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setSender(sender);
		vo.setRefNo(Long.parseLong(strRefNo));
		vo.setOrdNo(Long.parseLong(strOrdNo) + 1); // 관련 글 번호가 같고 순서 번호가 같거나 큰 것의 순서번호는 먼저 1증가를 시킨다.
		vo.setLevNo(Long.parseLong(strLevNo) + 1);
		vo.setParentNo(Long.parseLong(strNo)); // 번호(no)는 insert할 시 시퀀스를 사용한다. 넘겨 받은 번호는 부모번호로 셋팅

		// DB에 데이터를 저장하는 처리를 한다.
		// Integer writeResult = (Integer)ExeService.execute(Beans.get(AuthorityFilter.url), vo);
		ExeService.execute(Beans.get(AuthorityFilter.url), vo);
		
//		return strNo;
		
	}
	
	private void answerForm(HttpServletRequest request) throws Exception {
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
		// DB에서 데이터 가져오기 배열로 가져오기 때문에 배열 타입 Long[]{}을 씀.
		FeedbackVO vo = (FeedbackVO) ExeService.execute(Beans.get("/feedback/view.do"), no);

		// 서버에 저장
		request.setAttribute("vo", vo);
		
//		return no;

	}
	
	private void delete(HttpServletRequest request) throws Exception {

		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
		// vo객체를 만들어서 넣는다.

		// DB에 데이터를 저장하는 처리를 한다.
		Integer result = (Integer)ExeService.execute(Beans.get(AuthorityFilter.url), no);
		if(result == 0) throw new Exception("피드백 글 삭제 - 삭제할 데이터가 존재하지 않습니다.");
	}

}

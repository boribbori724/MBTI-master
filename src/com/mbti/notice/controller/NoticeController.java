package com.mbti.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mbti.notice.vo.NoticeReplyVO;
import com.mbti.main.controller.Beans;
import com.mbti.main.controller.Controller;
import com.mbti.main.controller.ExeService;
import com.mbti.member.vo.LoginVO;
import com.mbti.notice.vo.NoticeVO;
import com.mbti.util.filter.AuthorityFilter;
import com.mbti.util.page.PageObject;

public class NoticeController implements Controller{
	
	private final String MODULE = "notice";
	private String jspInfo = null;
	
	@Override
	public String execute(HttpServletRequest request) throws Exception{
		
		System.out.println("NoticeController.execute()");
		// 페이지 처리를 한다.
		// 페이지는 여기서 처리하므로 따로 처리해줄 필요가 없다.
		PageObject pageObject = PageObject.getInstance(request);
		request.setAttribute("pageObject", pageObject); // 페이지를 보여주기 위해 서버객체에 담
		
		switch (AuthorityFilter.url) {
		// 1.리스트 케이스
		// 위에 MODULE을 notice로 따로 지정을 해주어 간편화
		case "/" + MODULE + "/list.do":
			System.out.println("NoticeController.list");
			list(request, pageObject);
			// "notice/list" 넘긴다. -> /WEB-INF/views/ + notice/list + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/list";
			
			break;
		// 2.보기(뷰) 케이스
		case "/" + MODULE + "/view.do":
//			Long no = view(request);
			replyList(view(request), pageObject, request);
//			view(request);
			// "notice/view" 넘긴다. -> /WEB-INF/views/ + notice/view + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/view";
		
			break;
		// 3.삭제 케이스
		case "/" + MODULE + "/delete.do":
			
			delete(request);
			// delete를 하고 난 뒤 리스트 1페이지로 이동시키며 사용자가 설정해둔 perPageNum을 그대로 가져와 넘겨준다.
			jspInfo = "redirect:list.do?page=1&perPageNum=" + pageObject.getPerPageNum();
		
			break;
		// 4.작성 폼 케이스
		case "/" + MODULE + "/writeForm.do":
			
			// "notice/list" 넘긴다. -> /WEB-INF/views/ + notice/list + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/writeForm";
		
			break;
		// 4-1.작성  케이스
		case "/" + MODULE + "/write.do":
			
			write(request);
			// 글을 작성을 하고 리스트 1페이지로 자동으로 넘겨주고 사용자가 설정해둔 perPageNum을 저장해서 넘겨준다.
			jspInfo = "redirect:list.do?page=1&perPageNum=" + pageObject.getPerPageNum();
		
			break;
			
		// 7. 공지 댓글 등록 처리
		case "/" + MODULE +"/replyWrite.do":
			// service - dao --> request에 저장까지 해준다.
			replyWrite(request);
				
			// list.do로 자동으로 이동
			jspInfo = "redirect:view.do?" + request.getQueryString(); 
			break;
			
		// 8. 공지 댓글 수정 처리
		case "/" + MODULE +"/replyUpdate.do":
			// service - dao --> request에 저장까지 해준다.
			replyUpdate(request);
			
			// list.do로 자동으로 이동
			jspInfo = "redirect:view.do?no=" + request.getParameter("no");		
			break;
			
		// 9. 공지 댓글 삭제 처리
		case "/" + MODULE +"/replyDelete.do":
			// service - dao --> request에 저장까지 해준다.
			replyDelete(request);
			
			// list.do로 자동으로 이동
			jspInfo = "redirect:view.do?no=" + request.getParameter("no");	
			break;
		default: 
			throw new Exception("페이지 오류 404 - 존재하지 않는 페이지입니다.");
		}
		return jspInfo;
	}
	// 리스트 처리 스크립트
	// 리스트에서 pageObject를 사용하기에 따로 request와 pageObject 두개를 받는다.
	private void list(HttpServletRequest request, PageObject pageObject) throws Exception{
		
		// 넘겨 받은 데이터를 처리 - 공지 종류
		String period = request.getParameter("period");
		if(period == null) period = "pre";
		pageObject.setPeriod(period);
		// list.jsp에 자바 스크립트 부분에 있는 스크립트를 떼어와 여기서 처리를 시켜주고 list.jsp를 간소화 시킨다.
		@SuppressWarnings("unchecked")
		List<NoticeVO> list = (List<NoticeVO>) ExeService.execute(Beans.get(AuthorityFilter.url), pageObject);
		// 서버객체 request에 담는다.
		request.setAttribute("list", list);
	}
	// 보기(뷰) 처리 스크립트
	private Long view(HttpServletRequest request) throws Exception{
		
		
		// 넘어오는 번호 받아내기
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
		// vo 객체 
		NoticeVO vo = (NoticeVO) ExeService.execute(Beans.get(AuthorityFilter.url), no);

		//서버 객체에 데이터 저장하기
		request.setAttribute("vo", vo);
		
		return no;
	}
	private void delete(HttpServletRequest request) throws Exception{
		// 1. 넘어오는 번호 받아 오기
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
		
		// 2. DB 처리 - delete.jsp -> service -> dao
		String url = request.getServletPath();
		Integer result = (Integer) ExeService.execute(Beans.get(url), no);
		// 전달되는 데이터가 0 = 데이터가 전달되지 않음(데이터가 없음)
		if(result ==0 ) throw new Exception("게시판 글삭제 오류 - 존재하지 않는 글은 삭제할 수 없습니다.");
	}
	private void write(HttpServletRequest request) throws Exception{
		// 작성할 때 쓸 데이터 받아오기
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
	
		// 저장할 vo 객체 생성 - 가져오는게 아닌 작성(세팅) 하는것 이므로 get이 아닌 set을 써야 됨
		NoticeVO vo = new NoticeVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setStartDate(startDate);
		vo.setEndDate(endDate);
		// 생성 확인
		System.out.println("NoticeController.write().vo - " + vo );
		// db에 데이터 저장
		ExeService.execute(Beans.get(AuthorityFilter.url), vo);
	}
	// 6. 댓글 리스트 가져오기
		private void replyList(Long no, PageObject pageObject, HttpServletRequest request) throws Exception{
			// DB에서 데이터 가져오기
			// 연결URL => /notice/view.do -> 게시판 글보기
			// 댓글 리스트는 URL이 존재하지 않으나 데이터를 가져오기 위해 강제 셋팅해준다.
			// 처리되는 정보를 출력하지 않는다.
//			request.setAttribute("list", 
//			Beans.get("/notice/replyList.do").service(new Object[] {no, pageObject}));
			// 처리되는 정보를 출력하는 프록시 구조의 프로그램을 거쳐 간다.
			request.setAttribute("list", 
					ExeService.execute(Beans.get("/notice/replyList.do"), new Object[] {no, pageObject}));
		}
		
		// 7. 댓글 등록
		private void replyWrite(HttpServletRequest request) throws Exception {
			// 데이터 수집
			HttpSession session = request.getSession();
			String strNo = request.getParameter("no");
			String ncontent = request.getParameter("ncontent");
			String id = ((LoginVO) session.getAttribute("login")).getId();
//			String id = ((LoginVO) request.getSession().getAttribute("login")).getId();
			id = request.getParameter("id");
			// VO 객체 생성과 저장
			NoticeReplyVO vo = new NoticeReplyVO();
			vo.setNo(Long.parseLong(strNo));
			vo.setNcontent(ncontent);
			vo.setId(id);
			// 정보를 출력하는 필터 처리가 된다.
			ExeService.execute(Beans.get(AuthorityFilter.url), vo);
			// 정보를 출력하지 않고 직접 호출해서 실행은 된다.
//			Beans.get(AuthorityFilter.url).service(vo);
		}
		
		// 8. 댓글 수정
		private void replyUpdate(HttpServletRequest request) throws Exception {
			// 데이터 수집
			String strRno = request.getParameter("rno");
			String ncontent = request.getParameter("ncontent");
			String id = request.getParameter("id");
			// VO 객체 생성과 저장
			NoticeReplyVO vo = new NoticeReplyVO();
			vo.setRno(Long.parseLong(strRno));
			vo.setNcontent(ncontent);
			vo.setId(id);
			// 정보를 출력하는 필터 처리가 된다.
			ExeService.execute(Beans.get(AuthorityFilter.url), vo);
		}
		
		// 9. 댓글 삭제
		private void replyDelete(HttpServletRequest request) throws Exception {
			// 데이터 수집
			String strRno = request.getParameter("rno");
			// 정보를 출력하는 필터 처리가 된다.
			ExeService.execute(Beans.get(AuthorityFilter.url), Long.parseLong(strRno));
		}
}

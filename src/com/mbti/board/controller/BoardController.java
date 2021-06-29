package com.mbti.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mbti.board.vo.BoardReplyVO;
import com.mbti.board.vo.BoardVO;
import com.mbti.main.controller.Beans;
import com.mbti.main.controller.Controller;
import com.mbti.main.controller.ExeService;
import com.mbti.member.vo.LoginVO;
import com.mbti.util.filter.AuthorityFilter;
import com.mbti.util.page.PageObject;

public class BoardController implements Controller {
	
	private final String MODULE = "board";
	private String jspInfo = null;
	@SuppressWarnings("unused")
	private HttpSession session = null;
//	private LoginVO loginVO = null;
//	private String id = null;

	@Override
	public String execute(HttpServletRequest request) throws Exception{
		System.out.println("BoardController.execute()");
		
		// 넘어오는 세션 저장하기
		session = request.getSession();
//		loginVO = (LoginVO) session.getAttribute("login");
//		if(loginVO != null) id = loginVO.getId();
		
		// 페이지를 위한 처리
		PageObject pageObject = PageObject.getInstance(request);
		request.setAttribute("pageObject", pageObject); // 페이지를 보여주기 위해 서버객체에 담는다.
		
		switch (AuthorityFilter.url) {
		//1. 게시판 리스트
		case "/" + MODULE + "/list.do":
			// service - dao --> request에 저장까지 해준다.
			
			list(request, pageObject);
			
			// "board/list" 넘긴다. -> /WEB-INF/views/ + board/list + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/list";
			break;
		
		//2. 게시판 글보기	
		case "/" + MODULE + "/view.do":
//			Long no = view(request);
			// 댓글 리스트를 글보기쪽에 추가 
			replyList(view(request), pageObject, request);
			jspInfo = MODULE + "/view";
			break;
			
		//3-1. 게시판 글쓰기 폼
		case "/" + MODULE + "/writeForm.do":
			writeForm(request);
			jspInfo = MODULE + "/writeForm";
			break;
			
		//3-2. 게시판 글쓰기 처리
		case "/" + MODULE + "/write.do":
			write(request);
		
			jspInfo = "redirect:list.do?page=1&perPageNum" + pageObject.getPerPageNum();
			break;
			
		//4-1. 게시판 글수정 폼
		case "/" + MODULE + "/updateForm.do":
			updateForm(request);
		
			jspInfo = MODULE + "/updateForm";
			break;
			
		//4-2. 게시판 글수정 처리 --오류나면 여기랑 view 확인
		case "/" + MODULE + "/update.do":
			Long no = update(request);
			
			jspInfo = "redirect:view.do?no=" + no + "&inc=0&page=" + pageObject.getPage()
			+ "&perPageNum=" + pageObject.getPerPageNum();
			break;
			
		//5. 게시판 글삭제 처리
		case "/" + MODULE + "/delete.do":
			delete(request);
		
			jspInfo = "redirect:list.do?page=1&perPageNum=" + pageObject.getPerPageNum();
			break;
					
		//6. 댓글 리스트의 URL은 존재하지 않습니다. 게시판 보기에 포함되어 있습니다.
			
		// 7. 게시판 댓글 등록 처리
		case "/" + MODULE +"/replyWrite.do":
			// service - dao --> request에 저장까지 해준다.
			replyWrite(request);
				
			// list.do로 자동으로 이동
			jspInfo = "redirect:view.do?" + request.getQueryString() + "&inc=0";			
			break;
			
		// 8. 게시판 댓글 수정 처리
		case "/" + MODULE +"/replyUpdate.do":
			// service - dao --> request에 저장까지 해준다.
			replyUpdate(request);
			
			// list.do로 자동으로 이동
			jspInfo = "redirect:view.do?no="+request.getParameter("no")+"&inc=0";			
			break;
			
		// 9. 게시판 댓글 삭제 처리
		case "/" + MODULE +"/replyDelete.do":
			// service - dao --> request에 저장까지 해준다.
			replyDelete(request);
			
			// list.do로 자동으로 이동
			jspInfo = "redirect:view.do?no="+request.getParameter("no")+"&inc=0";			
			break;
			
		default:
			throw new Exception("BoardController - 404 페이지 오류 : 존재하지 않는 페이지입니다.");
		}
		
		return jspInfo;

	}
	
	//1. 게시판 리스트 처리
	private void list(HttpServletRequest request, PageObject pageObject) throws Exception{
		//자바 코드 부분
		@SuppressWarnings("unchecked")
		List<BoardVO> list
		= (List<BoardVO>) ExeService.execute(Beans.getService(AuthorityFilter.url), pageObject);
		//서버 객체에 담는다
		request.setAttribute("list", list);
	}
	
	//2. 게시판 글보기
	private Long view(HttpServletRequest request) throws Exception{
		//넘어오는 데이터 받기
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
		
		//내 아이디 정보 꺼내오기
		HttpSession session = request.getSession();
		String id = ((LoginVO) session.getAttribute("login")).getId();
		
		//vo 객체 생성 - 데이터 세팅
		LoginVO vo = new LoginVO();
		vo.setId(id);
		
		//조회수 1증가
		String strInc = request.getParameter("inc");
		long inc = Long.parseLong(strInc);
		
		//게시판 글보기 데이터 한 개 가져오기
		BoardVO viewVO = (BoardVO) ExeService.execute(Beans.getService(AuthorityFilter.url),
				new Long[] {no, inc});
		
		//서버 객에 request에 담는다
		request.setAttribute("vo", viewVO);
		
		// 글번호를 리턴한다.
		return no;
	}
	
	//3. 게시판 글쓰기 처리
	private void write(HttpServletRequest request) throws Exception{
		
		//1.데이터 수집
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		
		//아이디 정보 꺼내기
		HttpSession session = request.getSession();
		id = ((LoginVO) session.getAttribute("login")).getId();
		
		//vo 객체 생성 - 데이터 세팅
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setId(id);
		
		//2. DB처리
		Integer result = (Integer) ExeService.execute(Beans.getService(AuthorityFilter.url), vo);
		System.out.println("BoardController.write().result : " + result);
		
		//전달 메시지 저장
		request.getSession().setAttribute("msg", "게시글 등록이 완료되었습니다.");
	}	
	
	//3-2 글쓰기 폼
	private void writeForm(HttpServletRequest request) throws Exception{
		//넘어오는 데이터 받기 : id
		HttpSession session = request.getSession();
		String id = ((LoginVO) session.getAttribute("login")).getId();
		
		//vo 객체 생성 - 데이터 세팅
		BoardVO vo = new BoardVO();
		vo.setId(id);
		
		//3. 서버 객체에 넣기
		request.setAttribute("vo", vo);
	}
	
	//4-1. 게시판 글수정 폼 
	private void updateForm(HttpServletRequest request) throws Exception{
		//1. 넘어오는 데이터 받기
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
		//조회수 1 증가하는 부분은 inc=0으로 강제 세팅해서 넘긴다
		
		//2. 글번호에 맞는 데이터 가지고 오기
		String url = "/board/view.do";
		BoardVO vo = (BoardVO) ExeService.execute(Beans.getService(url), new Long[] {no, 0L});
		
		//3. 서버 객체에 넣기
		request.setAttribute("vo", vo);
	}
	
	//4-2. 게시판 글수정 처리
	private Long update(HttpServletRequest request) throws Exception{
		
		//1. 데이터 수집
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		
		//내 아이디 정보 꺼내
		HttpSession session = request.getSession();
		id = ((LoginVO) session.getAttribute("login")).getId();
		
		//vo 객체 생성 - 데이터 세팅
		BoardVO vo = new BoardVO();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setId(id);
		
		//2. DB처리;
		String url = request.getServletPath();
		Integer result = (Integer) ExeService.execute(Beans.getService(url), vo);
		
		if(result < 1) throw new Exception("수정할 게시글이 존재하지 않습니다.");
		
		return no;
	}
	
	//5. 게시판 글삭제 처리
	private void delete(HttpServletRequest request)throws Exception{
		//1. 데이터 수집
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
		
		//2. DB처리
		String url = request.getServletPath();
		Integer result = (Integer) ExeService.execute(Beans.getService(url), no);
		if(result == 0 ) throw new Exception("글 삭제 중 오류가 발생했습니다.");
	}
	
	// 6. 댓글 리스트 가져오기
	private void replyList(Long no, PageObject pageObject, HttpServletRequest request) throws Exception{
		// DB에서 데이터 가져오기
		// 연결URL => /board/view.do -> 게시판 글보기
		// 댓글 리스트는 URL이 존재하지 않으나 데이터를 가져오기 위해 강제 셋팅해준다.
		// 처리되는 정보를 출력하지 않는다.
//		request.setAttribute("list", 
//		Beans.get("/board/replyList.do").service(new Object[] {no, pageObject}));
		// 처리되는 정보를 출력하는 프록시 구조의 프로그램을 거쳐 간다.
		request.setAttribute("list", 
				ExeService.execute(Beans.get("/board/replyList.do"), new Object[] {no, pageObject}));
	}
	
	// 7. 댓글 등록
	private void replyWrite(HttpServletRequest request) throws Exception {
		//넘어오는 데이터 받기 : id
		HttpSession session = request.getSession();
		String id = ((LoginVO) session.getAttribute("login")).getId();
		
		// 데이터 수집
		String strNo = request.getParameter("no");
		String rcontent = request.getParameter("rcontent");
		id = request.getParameter("id");
		
		// VO 객체 생성과 저장
		BoardReplyVO vo = new BoardReplyVO();
		vo.setNo(Long.parseLong(strNo));
		vo.setRcontent(rcontent);
		vo.setId(id);
		
		// 정보를 출력하는 필터 처리가 된다.
		ExeService.execute(Beans.get(AuthorityFilter.url), vo);
		
		// 정보를 출력하지 않고 직접 호출해서 실행은 된다.
//		Beans.get(AuthorityFilter.url).service(vo);

	}
	
	// 8. 댓글 수정
	private void replyUpdate(HttpServletRequest request) throws Exception {
		// 데이터 수집
		String strRno = request.getParameter("rno");
		String rcontent = request.getParameter("rcontent");
		String id = request.getParameter("id");
//		String id = ((LoginVO) session.getAttribute("login")).getId();
		
		System.out.println("id : " + id);
		
		// VO 객체 생성과 저장
		BoardReplyVO vo = new BoardReplyVO();
		vo.setRno(Long.parseLong(strRno));
		vo.setRcontent(rcontent);
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
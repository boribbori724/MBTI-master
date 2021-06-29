package com.mbti.type.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mbti.main.controller.Beans;
import com.mbti.main.controller.Controller;
import com.mbti.main.controller.ExeService;
import com.mbti.type.vo.TypeVO;
import com.mbti.util.filter.AuthorityFilter;
import com.mbti.util.page.PageObject;

public class TypeController implements Controller {

	private final String MODULE = "type";
	private String jspInfo = null;
	private HttpSession session = null;
	
	@Override
	public String execute(HttpServletRequest request) throws Exception{
		System.out.println("TypeController.execute()");
		
		// 넘어오는 세션 저장
		session = request.getSession();
		
		//페이지 처리
		PageObject pageObject = PageObject.getInstance(request);
		request.setAttribute("pageObject", pageObject); 
		
		switch (AuthorityFilter.url) {
		// 1. 유형 이미지 리스트
		case "/" + MODULE + "/list.do":
			list(request, pageObject);
		
			jspInfo = MODULE + "/list";
			break;
		
		// 2. 유형 관리 보기
		case "/" + MODULE + "/view.do":
			view(request);
		
		jspInfo = MODULE + "/view";
		break;
		
		//3-1. 유형 관리 이미지 게시판 글쓰기 폼
		case "/" + MODULE + "/writeForm.do":
		jspInfo = MODULE + "/writeForm";
		break;
		
		//3-2. 유형 관리 이미지 게시판 글쓰기 
		case "/" + MODULE + "/write.do":
			write(request);
		
		session.setAttribute("msg", "유형 관리 게시판 글쓰기가 성공적으로 완료되었습니다.");
		// "type/view" 넘긴다. -> /WEB-INF/views/ + type/view + .do를 이용해서 HTML을 만든다.
		jspInfo = "redirect:list.do?page=1&perPageNum=" + pageObject.getPerPageNum();			
		break;

		// 4-1. 유형관리 이미지 게시판 글수정 폼
		case "/" + MODULE +"/updateForm.do":
			updateForm(request);
			// "type/updateForm" 넘긴다. -> /WEB-INF/views/ + type/updateForm + .do 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/updateForm";			
			break;
		
		// 4-2. 유형관리 이미지 게시판 글수정 처리
		case "/" + MODULE + "/update.do":
			// service - dao --> request에 저장까지 해준다.
			Long no = update(request);
		
			//글수정 처리가 끝난 후 경고창으로 나타나는 메시지
			session.setAttribute("msg", "이미지 게시판 글수정이 성공적으로 완료되었습니다.");
		
			// "do/view" 넘긴다. -> view.do로 자동으로 이동
			jspInfo = "redirect:view.do?no=" + no + "&page="
					+ pageObject.getPage() + "&perPageNum=" + pageObject.getPerPageNum();			
			break;
		
			// 5. 유형이미지 게시판 글삭제 처리
			case "/" + MODULE +"/delete.do":
				// service - dao --> request에 저장까지 해준다.
				delete(request);
			
				//글삭제 처리가 끝난 후 경고창으로 나타나는 메시지
				session.setAttribute("msg", "이미지 게시판 글삭제가 성공적으로 완료되었습니다.");
			
				// list.do로 자동으로 이동
				jspInfo = "redirect:list.do?page=1&perPageNum=" + pageObject.getPerPageNum();			
				break;
			
			
	
		default:
			throw new Exception("TypeController - 페이지 오류 404 - 존재하지 않는 페이지입니다.");
		}
		return jspInfo;
		}
	//1. 유형 관리 리스트 처리
	private void list(HttpServletRequest request, PageObject pageObject) throws Exception {
		// TODO Auto-generated method stub
		
		 @SuppressWarnings("unchecked")
		 // Beans안에 있는 Service(url)을 가져와서 실행한다.
		 List<TypeVO> list
		 = (List<TypeVO>) ExeService.execute(Beans.getService(AuthorityFilter.url), pageObject);
		 request.setAttribute("list", list);
		 
	}
	
	//2. 유형관리 글보기 처리.
	private Long view (HttpServletRequest request) throws Exception {
		
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
		
		//유형관리 글보기 데이터 1개 가져오기
		TypeVO vo = (TypeVO) ExeService.execute(Beans.getService(AuthorityFilter.url), no );
		
		// 서버객체 request에 담는다.
		request.setAttribute("vo", vo);
		return no;
	}
	
	//3. 유형관리 글쓰기 처리.
	private void write (HttpServletRequest request) throws Exception {
		
		// 1. 데이터 수집
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String image = request.getParameter("image");
		String gType = request.getParameter("gType");
		String gImage = request.getParameter("gImage");
		String bType = request.getParameter("bType");
		String bImage = request.getParameter("bImage");
		
		

		TypeVO vo = new TypeVO();
		vo.setName(name);
		vo.setType(type);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setImage(image);
		vo.setgType(gType);
		vo.setgImage(gImage);
		vo.setbType(bType);
		vo.setbImage(bImage);

		// 2. DB 처리 - write.jsp -> service -> dao
		Integer result = (Integer) ExeService.execute(Beans.getService(AuthorityFilter.url), vo);

		System.out.println("TypeController.write().result : " + result);
		// 전달 메시지 저장
		request.getSession().setAttribute("msg", "유형 관리 게시판에 글이 등록되었습니다.");
	}
	// 4-1. 이미지 게시판 글수정 폼
	private void updateForm(HttpServletRequest request) throws Exception {
		// 자바 부분입니다.
		// 1. 넘어오는 데이터 받기 - 글번호
		String strNo = request.getParameter("no");
		
		long no = Long.parseLong(strNo);
		// 조회수 1증가하는 부분은 inc=0으로 강제 셋팅해서 넘긴다.
		// 2. 글번호에 맞는 데이터 가져오기 -> ImageViewService => /image/view.jsp
		String url = "/type/view.do"; // 현재 URL과 다르므로 강제 셋팅했다.
		TypeVO vo = (TypeVO) ExeService.execute(Beans.getService(url),no);

		// 3. 서버 객체에 넣기
		request.setAttribute("vo", vo);

	}

	// 4-2. 이미지 게시판 글수정 처리
	private Long update(HttpServletRequest request) throws Exception {

		// 1. 데이터 수집
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
		
		
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String image = request.getParameter("image");
		String gType = request.getParameter("gType");
		String gImage = request.getParameter("gImage");
		String bType = request.getParameter("bType");
		String bImage = request.getParameter("bImage");

		TypeVO vo = new TypeVO();
		vo.setNo(no);
		vo.setName(name);
		vo.setTitle(title);
		vo.setType(type);
		vo.setContent(content);
		vo.setImage(image);
		vo.setgType(gType);
		vo.setgImage(gImage);
		vo.setbType(bType);
		vo.setbImage(bImage);

		// 2. DB 처리 - update.jsp -> service -> dao
		String url = request.getServletPath();
		Integer result = (Integer) ExeService.execute(Beans.getService(url), vo);
 
		if(result < 1) throw new Exception("이미지 게시판 글수정 - 수정할 데이터가 존재하지 않습니다.");
		 
		return no;
	}
	// 5. 이미지 게시판 글삭제 처리
	private void delete(HttpServletRequest request) throws Exception {
		// 1. 데이터 수집
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
 
		// 2. DB 처리 - delete.jsp -> service -> dao
		String url = request.getServletPath();
		Integer result = (Integer) ExeService.execute(Beans.getService(url), no);
		if(result ==0 ) throw new Exception("이미지 게시판 글삭제 오류 - 존재하지 않는 글은 삭제할 수 없습니다.");
	}
		

}

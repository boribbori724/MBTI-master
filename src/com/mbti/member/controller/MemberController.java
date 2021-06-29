package com.mbti.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mbti.main.controller.Beans;
import com.mbti.main.controller.Controller;
import com.mbti.main.controller.ExeService;
import com.mbti.main.controller.Service;
import com.mbti.member.vo.LoginVO;
import com.mbti.member.vo.MemberVO;
import com.mbti.util.filter.AuthorityFilter;
import com.mbti.util.page.PageObject;

public class MemberController implements Controller{
	
	private final String MODULE = "member";
	private String jspInfo = null;
	private HttpSession session = null;
	private String id = null;
	
	@Override
	public String execute(HttpServletRequest request) throws Exception {
		System.out.println("MemberController.execute()");
		
		session = request.getSession();
		
		// 페이지 처리
		PageObject pageObject = PageObject.getInstance(request);
		request.setAttribute("pageObject", pageObject);
		
		switch (AuthorityFilter.url) {
		
		// 1. 로그인 처리
		case "/" + MODULE + "/login.do":
			login(request);
			
	//		jspInfo = MODULE + "/list";
			jspInfo = "return:" + (request.getSession().getAttribute("url"));
			break;
			
		// 1-1. 로그인  폼 처리
		case "/" + MODULE + "/loginForm.do":
			
	//		jspInfo = MODULE + "/list";
			jspInfo = MODULE + "/loginForm";
			break;
			
		// 2. 로그아웃 처리
		case "/" + MODULE + "/logout.do":
			logout(request);
			// "member/view" 넘긴다 -> WEB-INF/views/ + member/writeForm + .jsp를 이용해서 HTML을 만든다.
			jspInfo = "return:" + (request.getSession().getAttribute("url"));
			break;
		
		// 3. 회원가입 처리
		case "/" + MODULE + "/write.do":
			write(request);
			jspInfo = "redirect:/member/loginForm.do";
			break;
		
		// 3-1. 회원가입 폼 처리
		case "/" + MODULE + "/writeForm.do":
			jspInfo = MODULE + "/writeForm";
			break;
		
		// 3-2. 아이디 중복 체크
		case "/ajax/checkId.do":
			// DB에서 입력한 아이디를 찾아온다.
			// 찾아온 아이디를 request에 넣는다.
			checkId(request);
			
			// div안에 들어갈 코드만 있는 jsp로 이동시킨다.
			jspInfo = "member/checkId";
			break; 
			
		// 4. 회원 정보 보기
		case "/" + MODULE + "/view.do":
			view(request);
			// "member/view" 넘긴다. -> /WEB-INF/views/ + member/view + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/view";
			break;
		
		// 5. 회원 리스트 처리
		case "/" + MODULE + "/list.do":
			list(request, pageObject);
			jspInfo = MODULE + "/list";
			break;
			
		// 6. 회원 등급 수정
		case "/" + MODULE + "/gradeModify.do":
			gradeModify(request);
			jspInfo = "redirect:/member/list.do";
			break;
			
		// 7. 회원 탈퇴
		case "/" + MODULE + "/delete.do":
			delete(request);
			jspInfo = MODULE + "/list";
			break;
			
			// 4-1. 회원 정보 수정 폼
		case "/" + MODULE +"/updateForm.do":
			updateForm(request);
			// "member/updateForm" 넘긴다. -> /WEB-INF/views/ + member/updateForm + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/updateForm";			
			break;
		
		// 4-2. 회원 정보 수정 처리
		case "/" + MODULE +"/update.do":
			// service - dao --> request에 저장까지 해준다.
			update(request);
		
			session.setAttribute("msg", "회원 정보 수정이 정상적으로 적용되었습니다.~~~");
			// 회원가입이 끝나면 자동으로 로그인 페이지로 이동시킨다.

			// "member/view" 넘긴다. -> view.do로 자동으로 이동
			jspInfo = "redirect:view.do?page=" 
			+ pageObject.getPage() + "&perPageNum=" + pageObject.getPerPageNum();			
			break;
					
			
		default:
			throw new Exception("페이지 오류 404 - 존재하지 않는 페이지입니다.");
		}
		// jhp의 정보를 가지고 리턴
		// TODO Auto-generated method stub
		return jspInfo;
	}
	
	// 1. 로그인 처리
	private void login(HttpServletRequest request) throws Exception{
		
		// 자바부분
		// 데이터 받기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		// 받은 데이터를 VO객체에 저장하기
		LoginVO vo = new LoginVO();
		vo.setId(id);
		vo.setPw(pw);
		// jsp - service - dao
		LoginVO loginVO = (LoginVO) ExeService.execute(Beans.getService(AuthorityFilter.url), vo); 
		
		// id, pw 틀린 경우 처리
		if(loginVO == null) throw new Exception("아이디와 비밀번호를 확인해 주세요");
		// 로그인 처리
		request.getSession().setAttribute("login", loginVO);
	}
	
	// 2. 로그아웃 처리
	private void logout(HttpServletRequest request) throws Exception{
		request.getSession().removeAttribute("login");;
		System.out.println("MemberController.logout [login] : " + request.getSession().getAttribute("login"));
		System.out.println("로그아웃 처리가 되었습니다.");
	}
	
	// 3. 회원가입 처리
	private void write(HttpServletRequest request) throws Exception{
		
		// 자바 
		// 데이터 수집
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birth= request.getParameter("birth");
		String tel1= request.getParameter("tel");
		String tel2= request.getParameter("tel2");
		String tel3= request.getParameter("tel3");
		String email= request.getParameter("email");
		
		String tel = tel1 + "-" + tel2 + "-" + tel3;
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		vo.setName(name);
		vo.setGender(gender);
		vo.setBirth(birth);
		vo.setTel(tel);
		vo.setEmail(email);
		
		Integer result = (Integer) ExeService.execute(Beans.get(AuthorityFilter.url), vo);
		System.out.println("BoardController.write().result : " + result); 
	}
	
	// 3-1. 아이디 중복체크
	private void checkId(HttpServletRequest request) throws Exception{
		
		// 자바
		// 넘어온 아이디 받기
		String id = request.getParameter("id");
		// DB처리 -> id 가져오기
		String result = (String) ExeService.execute(Beans.getService(AuthorityFilter.url), id);
		// 서버 객체 저장
		request.setAttribute("id", result);
	}
	
	// 4. 회원 정보 보기
	private void view(HttpServletRequest request) throws Exception{
		
		// 자바
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("login");
		System.out.println(loginVO);
		
		String id = loginVO.getId();
		MemberVO vo = (MemberVO) ExeService.execute(Beans.getService(AuthorityFilter.url), id);
		
		System.out.println(id);
		request.setAttribute("vo", vo);
	}
	
	// 5. 회원 리스트 처리
	private void list(HttpServletRequest request, PageObject pageObject) throws Exception{
		
		// 자바
		@SuppressWarnings("unchecked")
		List<MemberVO> list = (List<MemberVO>) ExeService.execute(Beans.getService(AuthorityFilter.url), pageObject);
		// 서버객체 request에 담는다.
		request.setAttribute("list", list);
	}
	
	// 6. 회원 등급 수정
	private void gradeModify(HttpServletRequest request) throws Exception{
		
		// 자바
		System.out.println("gradeModify.jsp - 실행 ---------");

		// 아이디 받아내기 
		String id = request.getParameter("id");
		System.out.println("gradeModify.jsp [id] - " + id);
		//등급 문자열 받아내기
		String strGradeNo = request.getParameter("gradeNo");
		System.out.println("gradeModify.jsp [strGradeNo] - " + strGradeNo);
		int gradeNo = Integer.parseInt(strGradeNo);
		System.out.println("gradeModify.jsp [gradeNo] - " + gradeNo);

		//수집한 데이터를 DB처리 -
		// 저장할 VO객체 생성
		MemberVO vo = new MemberVO();
		System.out.println("gradeModify.jsp [vo] - " + vo);
		//생성된 vo객체에 데이터 넣기 
		vo.setId(id);
		vo.setGradeNo(gradeNo);

		//Service 선택해서 가져오기 
		String url = request.getServletPath();
		System.out.println("gradeModify.jsp [url] - " + url);
		Service service = Beans.get(url);
		System.out.println("gradeModify.jsp [service] - " + service);

		ExeService.execute(service, vo);

//		response.sendRedirect("list.do");
	}
	
	// 7. 회원 탈퇴 처리
	private void delete(HttpServletRequest request) throws Exception{
		
		// 자바
//		LoginVO vo = (LoginVO) request.getSession().getAttribute("login");
		
//		String id = ((LoginVO)request.getSession().getAttribute("login")).getId();
		
//		String id = vo.getId();
		
		LoginVO vo = (LoginVO) session.getAttribute("login");
		
		String id = vo.getId();
		
//		long id = Long.parseLong(strNo);
		System.out.println(id);
		// 2. DB 처리 - delete.jsp -> service -> dao
		// String url = request.getServletPath();
		ExeService.execute(Beans.getService(AuthorityFilter.url), id);
		
		request.getSession().invalidate();
		
		// 3. list로 자동 이동
		
//		response.sendRedirect("list.do");
//		request.setAttribute("id", result);
	}
	// 회원 수정 폼
	private void updateForm(HttpServletRequest request) throws Exception {
		// 자바 부분입니다.
		String url = "/member/view.do"; // 현재 URL과 다르므로 강제 셋팅했다.
		id = ((LoginVO)request.getSession().getAttribute("login")).getId();
		MemberVO vo = (MemberVO) ExeService.execute(Beans.get(url), id);

		// 3. 서버 객체에 넣기
		request.setAttribute("vo", vo);
	}
	// 회원 수정 처리
	private void update(HttpServletRequest request) throws Exception {

		// 1. 데이터 수집
		String pw = request.getParameter("pw");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		id = ((LoginVO)request.getSession().getAttribute("login")).getId();

		System.out.println("update : " + pw);
		System.out.println("update : " + tel);
		System.out.println("update : " + email);
		System.out.println("update : " + id);
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		vo.setTel(tel);
		vo.setEmail(email);

		// 2. DB 처리 - update.jsp -> service -> dao
		Integer result = (Integer) ExeService.execute(Beans.get(AuthorityFilter.url), vo);

		if(result < 1) throw new Exception("MemberController.update() - 회원 정보 수정 - 수정할 데이터가 존재하지 않습니다.");
	}

}

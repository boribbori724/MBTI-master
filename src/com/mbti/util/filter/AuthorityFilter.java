/*
 * 권한 설정을 위한 필터
 * 작성자 : 궁금해조
 * 작성일 : 2021.03.21
 * ver_1.0
 */
package com.mbti.util.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mbti.member.vo.LoginVO;

/**
 * Servlet Filter implementation class AuthorityFilter
 */
//@WebFilter("/AuthorityFilter")
public class AuthorityFilter implements Filter {

	private static Map<String, Integer> authMap = new HashMap<>();	// url에 대한 권한 정보를 저장하는 MAP 서버가 로딩될 때 정해져야 한다.
	
	{	// 페이지에 대한 등급 정보를 저장하는 method
		
		//==================== 테스트 목록 ===========================================
		authMap.put("/list/update.do", 9);
		authMap.put("/list/register.do", 9);
		authMap.put("/list/registerForm.do", 9);
		
		//=================== 게시판 ===============================================     
		authMap.put("/board/view.do", 1);
		authMap.put("/board/write.do", 1);
		authMap.put("/board/writeForm.do", 1);
		authMap.put("/board/update.do", 1);
		authMap.put("/board/updateForm.do", 1);
		authMap.put("/board/delete.do", 1);
		
		// =================== 공지사항 ==============================================
//		authMap.put("/notice/view.do", 1);
		authMap.put("/notice/writeForm.do", 9);
		authMap.put("/notice/delete.do", 9);
		authMap.put("/notice/delete.do", 9);
		
		//================== 피드백 ================================================
		authMap.put("/feedback/list.do", 1);
		authMap.put("/feedback/adminList.do", 9);
		authMap.put("/feedback/view.do", 1);
		authMap.put("/feedback/delete.do", 9);
		authMap.put("/feedback/write.do", 1);
		authMap.put("/feedback/writeForm.do", 1);
		authMap.put("/feedback/answer.do", 9);
		authMap.put("/feedback/answerForm.do", 9);
		
		//=================== 로그인 및 회원관리 =============================================
		authMap.put("/member/list.do", 9);
		authMap.put("/member/gradeModify.do", 9);
		
		
		
		//=================== 유형관리 =============================================
		authMap.put("/type/list.do", 9);
		authMap.put("/type/writeForm.do", 9);
		authMap.put("/type/write.do", 9);
		authMap.put("/type/updateForm.do", 9);
		authMap.put("/type/update.do", 9);
		authMap.put("/type/delete.do", 9);


		
	}
	
	public static String url;
	
    /**
     * Default constructor. 
     */
    public AuthorityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest req = (HttpServletRequest) request;
		
		System.out.println("AuthorityFilter.doFilter : 권한처리");
		
		url = req.getServletPath();
		
		int pathIndex = url.indexOf("/", 1);
		
		String path = "";
		
		if(pathIndex >= 0) {
			
			path = url.substring(0, pathIndex);
			
		}
		
		System.out.println("path!!! : " + path);
		
		System.out.println("url!!!! : " + url);
		
		if(!path.equals("/member") && !path.equals("/ajax") && !path.equals("/error")) {
				
			if(req.getQueryString() != null) {
				
				req.getSession().setAttribute("url", url + "?" + req.getQueryString());
				
				System.out.println("저장된 URL : " + url + "?" + req.getQueryString() );
				
			} else {
				
				req.getSession().setAttribute("url", url);
				
				System.out.println("저장된 URL : " + url);
			}
				
		} else {
			
			System.out.println("저장된 URL : " + req.getSession().getAttribute("url"));
			
		}
		
		HttpSession session = req.getSession();
		
		LoginVO vo = (LoginVO)session.getAttribute("login");
		
		// 권한이 없는 경우의 처리
		if(!checkAuthority(vo)) {
			
			// 오류 페이지로 이동시킨다
			((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/error/auth_error.do");
			
			// 호출한 쪽으로 되돌아 간다. : 없으면 계속 아래로 실행이 된다.
			return;
			
		}
		
		System.out.println("Authority.doFilter.url : " + url);
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	
	private boolean checkAuthority(LoginVO vo) {
		
		Integer pageGradeNo = authMap.get(url);

		if(pageGradeNo == null) {
			
			System.out.println("AuthorityFilter.checkAuthority() : 권한이 필요없는 페이지 입니다.");
			
			return true;
			
		}
		
		if(vo == null) {
			
			System.out.println("AuthorityFilter.doFilter)_: 로그인이 필요합니다.");
			
			return false;
			
		}
		
		// 여기서 부터는 로그인이 필요한 처리 입니다.(VO != null)
		System.out.println("AuthorityFilter.checkAuthority().pageGradeNo : " + pageGradeNo);
		System.out.println("AuthorityFilter.checkAuthority().userGradeNo : " + vo.getGradeNo());
	
		// 권한이 없는 페이지 요청에 대한 처리
		if(pageGradeNo > vo.getGradeNo()) {
			
			System.out.println("AuthorityFilter.checkAuthority() : 권한이 없습니다.");
			
			return false;
			
		}
		
		System.out.println("AuthorityFilter.checkAuthority() : 권한이 있습니다.");
		
		return true;
		
	}
	 
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

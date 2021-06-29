<%@page import="com.mbti.util.filter.AuthorityFilter"%>
<%@page import="com.mbti.main.controller.Beans"%>
<%@page import="com.mbti.main.controller.ExeService"%>
<%@page import="com.mbti.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 자바
// 데이터 받기
String id = request.getParameter("id");
String pw = request.getParameter("pw");

// 받은 데이터 VO객체에 저장
LoginVO vo = new LoginVO();
vo.setId(id);
vo.setPw(pw);

LoginVO loginVO = (LoginVO) ExeService.execute(Beans.getService(AuthorityFilter.url) , vo);

// id, pw 틀린 경우 처리
if(loginVO == null) throw new Exception("로그인 정보를 확인해 주세요");

// 로그인 처리
session.setAttribute("login", loginVO);
response.sendRedirect("../main/main.jsp");
%>
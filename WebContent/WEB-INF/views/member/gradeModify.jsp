<%@page import="com.mbti.util.filter.AuthorityFilter"%>
<%@page import="com.mbti.main.controller.Beans"%>
<%@page import="com.mbti.main.controller.Service"%>
<%@page import="com.mbti.main.controller.ExeService"%>
<%@page import="com.mbti.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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
ExeService.execute(Beans.getService(AuthorityFilter.url), vo);

%>
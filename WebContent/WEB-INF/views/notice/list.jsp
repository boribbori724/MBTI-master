<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>▶ 공지사항</title>
<script type="text/javascript">
$(function(){
	// class가 dataRow인 대상을 클릭시 일어나는 함수
	$(".dataRow").click(function(){
		// dataRow 클래스에 해당하는 데이터가 가진 no값을  -> view으로 no값과 perPageNum,page값을 같이 넘겨준다.(되돌아올시 그대로 받는다.)
		// .do=.jsp
		var no = $(this).find(".no").text();
		location = "view.do?no=" + no + "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}";
	});
	$("#sel_perPageNum").change(function(){
		// 다시 리스트 불러오기 - 전달 정보는 페이지:1, perPageNum을 선택된 값을 전달.
		location = "list.do?page=1&perPageNum=" + $(this).val();
	});
	
});
</script>
<style type="text/css">
.dataRow:hover{
	cursor: pointer;
	background: graytext;
}
.total:hover{
	cursor: text;
}
thead{
	background: black;
	color: white;
}
td{
	color: white;
}
</style>
</head>
<body>
	<div class="container">
<!-- 		<h1 style="text-align: center; margin-bottom: -22.8px; color: red; user-select: none;">┏━━┓</h1> -->
<!-- 		<h1 style="text-align: center; color: red; user-select: none;">┃ -->
		<h1 style="text-align: center;">
		<a href="list.do?page=${pageObject.page }
		&perPageNum=${pageObject.perPageNum}&period=pre"${pageObject.period == "pre"?"Active":""} 
		style="color: white; text-decoration: none;" >공지</a>
		</h1>
<!-- 		┃</h1>	 -->
<!-- 		<h1 style="text-align: center; margin-top: -13.6px; color: red; user-select: none;" >┗━━┛</h1>	 -->
		
		<div class="pull-right form-inline">
			<select class="form-control" id="sel_perPageNum" style="margin: 2px;">
				<option ${(pageObject.perPageNum == 5)?"selected":"" }>5</option>
				<option ${(pageObject.perPageNum == 10)?"selected":"" }>10</option>
				<option ${(pageObject.perPageNum == 20)?"selected":"" }>20</option>
				<option ${(pageObject.perPageNum == 30)?"selected":"" }>30</option>
			</select>
		</div>
		
				<label class="total" style="float: left; font-weight: normal;  margin-top: 10px;">총 게시글 : 
				<span style="font-weight: bolder; color: red;">${pageObject.totalRow }</span>건</label>
		<table class="table">
		<thead>
			<tr>
				<th style="padding-left: 20px;">번호</th>
				<th style="padding-left: 50px;">제목</th>
				<th style="padding-left: 70px;">기간</th>
				<th style="padding-left: 23px;">작성일</th>
			</tr>
		</thead>
				<c:if test="${empty list }">
					<tr><td colspan="5" class="text-center">글이 존재하지 않습니다.</td></tr>
				</c:if>
			<c:forEach items="${list }" var="vo">
			<tr class="dataRow">
				<td class="no" hidden="no">${vo.no }</td>
				<td style="color: red;  font: bolder; padding-left: 20px;">공지</td>
				<td style="padding-left: 50px;">${vo.title }</td>
				<td>${vo.startDate } ~ ${vo.endDate }</td>
				<td>${vo.writeDate }</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="4">
			<div style="float: right;">
			<c:if test="${login.gradeNo == 9 }">
					<a href="writeForm.do" class="button" >작성</a>	
				
			</c:if>
			</div>
			<div style="float: left;">
				<c:if test="${login.gradeNo == 9 }">
					<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&period=48gh9rc83"
		 			${pageObject.period == "48gh9rc83"?"Active":""} class="button" >지난 공지</a>
					<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&period=92grcvbq6"
	 				${pageObject.period == "92grcvbq6"?"Active":""} class="button" >예정 공지</a>
					<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&period=pre"
		 			${pageObject.period == "pre"?"Active":""} class="button" >현재 공지</a>
					<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&period=74vwnm5r0"
	 				${pageObject.period == "74vwnm5r0"?"Active":""} class="button" >전체 공지</a>
				</c:if>
			</div>
			<br/>
					<pageObject:pageNav listURI="list.do" pageObject="${pageObject }"/>
				</td>
			</tr>
			
		</table>
	</div>
</body>
</html>
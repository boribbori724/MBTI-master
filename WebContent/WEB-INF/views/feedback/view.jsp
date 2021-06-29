
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>피드백 보기</title>

<script type="text/javascript">

// 삭제 여부 버튼 
$(function(){ 
	$("#deleteBtn").click(function(){
		if(!confirm("정말로 삭제하시겠습니까?")) return false;
	});
});

</script>

<style type="text/css">
td,th{
   border: 1px solid;
}
</style>
</head>
<body>
<div class="container" >
	<h1>피드백</h1>
	<table class="table">
	<tbody>
		<tr>
			<th class="no">번호</th>
			<td>${vo.no }</td>
		</tr>
		<tr> 
			<th class="b1">제목</th>
			<td>${vo.title }</td>
		</tr>
		<tr>
			<th class="b1">내용</th>
			<td height="250px" style="word-break:break-all; white-space:pre-wrap; font-size: 18px;">${vo.content }</td>
		</tr>
		<tr>
			<th class="b1">작성일</th>
			<td>${vo.writeDate }</td>
		</tr>
		</tbody>
	</table>
				<c:if test="${vo.id == login.id || login.gradeNo == 9}">
					<a href ="answerForm.do?no=${vo.no }" class="button">답변</a>
					<a href ="delete.do?no=${vo.no }" class="button" id="deleteBtn">삭제</a>
				</c:if>
					<a href ="list.do" class="button">리스트</a>
				<c:if test="${vo.id == login.id || login.gradeNo == 9}">
					<a href ="adminList.do" class="button">관리자 리스트</a>
				</c:if>
</div>
</body>
</html>
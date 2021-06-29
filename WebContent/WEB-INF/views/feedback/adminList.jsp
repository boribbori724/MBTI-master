<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>피드백</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<style type="text/css">
.dataRow:hover{
	cursor: pointer;
	background: #eee;
}
h1{
	text-align: center;
}
thead{
	background: black;
	color: white;
}
#all{
	display: run-in;
}
</style>

<script type="text/javascript">
$(function(){
	// 이벤트 처리
	// 메시지 보기로 이동
	$(".dataRow").click(function(){
		// alert("data 보기 클릭");
		// $(this) : 자기 자신(이벤트가 일어 난 곳 - 현재는 tr).클래스가 no인 객체를 찾아라.태그안에 있는 글자가져오기
		var no = $(this).find(".no").text();
		location = "view.do?no=" + no;
	});
});
</script>

</head>
<body>
<div class="container">
<h1>관리자 피드백</h1>

<label class="total"></label>


<table class="table">
<thead>
	<tr>
		<th>문의번호</th>
		<th>제목</th>
		<th>작성일</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${list }" var="vo">
		<tr class="dataRow">
			<td class="no">${vo.no }</td>
			<td>
				<c:forEach begin="1" end="${vo.levNo }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
				<c:if test="${vo.levNo > 0 }">
					<i class="material-icons">subdirectory_arrow_right</i>
				</c:if>${vo.title }
			</td>
			<td>${vo.writeDate }</td>
		</tr>
	</c:forEach>
	</tbody>
	<tfoot>
	<tr>
		<td colspan="5">
			<pageObject:pageNav listURI="adminList.do" pageObject="${pageObject }" />
		</td>
	</tr>
	</tfoot>
</table>
</div>
</body>
</html>
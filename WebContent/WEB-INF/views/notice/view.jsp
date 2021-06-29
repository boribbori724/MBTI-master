<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>▶ 공지 사항</title>
<script type="text/javascript">
$(function(){
	//수정버튼 숨기기
	$(".upButton").hide();
	
	$(".upButton").click(function name() {
		
		location = "replyUpdate.do?page=1&perPageNum=10&no=${vo.no}";
		
	});
	$("#replyDeleteBtn").click(function(){
		if(!confirm("삭제 하시겠습니까?")) 
			return false;
	});
	$("#deleteA").click(function(){
		if(!confirm("삭제 하시겠습니까?")) 
			return false;
	});
	//댓글 작성
	$(".wrButton").click(function(){
	$("#replyForm").attr("action", "replyWrite.do?page=1&perPageNum=10&no=${vo.no}");
	$("#replyForm").sumbit();
	});
	
	//댓글 수정처리
	$(".replyUpdateBtn").click(function(){
 		//댓글 -> 댓글 수정 
 		
 		var dataRow = $(this).closest(".dataRow");
//  		alert(dataRow);
 		
		var no = $(".table").find(".no").text();
 		
		var rno = parseInt(dataRow.find(".rno").text());
		$(".dataRow").val(rno);
// 		alert(rno);
		
		$(".chRno").val(rno);
		
		$(".wrButton").hide();
		$(".upButton").show();
		
		var ncontent = dataRow.find(".ncontent").text();
		
		$(".chData").val(ncontent);
		 		
 		$(".dataRow").hide();
		var t = $("#replyForm").attr("action", "replyUpdate.do?page=1&perPageNum=10&no=${vo.no }");
	});
	

	
});
</script>
<style type="text/css">
td,th{
   border: 1px solid;
}
table{
color:white;
}
</style>
</head>
<body>
<input name="perPageNum" type="hidden" value="${pageObject.perPageNum }">
	<br/>
	<br/>
	<div class="container">
		<div style="float:right;">
				<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}"  class="button">목록</a>
		</div>
	<table class="table">
	<tbody>
		<tr>
			<th class="no" hidden="no">번호</th>
			<td hidden="no">${vo.no }</td>
		</tr>
		<tr> 
			<th class="b1">분류</th>
			<td>공 지</td>
		</tr>
		<tr>
			<th class="b1">제목</th>
			<td>${vo.title }</td>
		</tr>
		<tr>
			<th class="b1">내용</th>
			<td width="1050px" height="250px" style="word-break:break-all; white-space:pre-wrap; font-size: 13px;">${vo.content }</td>
		</tr>
		<tr>			
			<th class="b1">기 간</th>
			<td>${vo.startDate } ~ ${vo.endDate }</td>
		</tr>
	</tbody>
		<tfoot>
					<c:if test="${login.gradeNo == 9 }">
			<tr>
			<td colspan="2">
					<div style="float: right;">
				<a href="delete.do?no=${vo.no }&perPageNum=${pageObject.perPageNum}" id="deleteA" class="button">삭제</a>
					</div>
			</td>
			</tr>
					</c:if>
		</tfoot>
	</table>		
	</div>	
	<!-- 댓글 -->
	<div class="container">
		<div class="w3-border w3-padding form-group" style="font-size: large; color: white;">댓글 (${pageObject.totalRow })</div>
				<div id="replyList" class="form-group">
			<!-- 댓글 리스트 -->
			<c:if test="${empty list }">
				<textarea rows="2" cols="50"  class="w3-input w3-border newLogin form-control w3" readonly>등록된 댓글이 없습니다.</textarea>
			</c:if>
				<c:if test="${!empty list }">
						<c:forEach items="${list }" var="rvo">
<li class="list-group-item dataRow" id="ncontent">
<pre style="background: #fff; border: none; padding: 0px;"class="pre" id="pre"><span class="rno" style="position:absolute; overflow:hidden; border:0;width:1px;height:1px; 
clip: rect(1px, 1px, 1px, 1px);clip-path:inset(50%);">${rvo.rno }</span><i class="glyphicon glyphicon-user"><span class="r_id" style="font-size:larger;  font-weight: 900;"> ${rvo.id }</span></i>
<span class="ncontent" id="ncontent">${rvo.ncontent }</span></pre>${rvo.writeDate }<span class="pull-right" style="margin-top: -10px">
							<!-- 댓글 작성자와 로그인한 사람이 같으면 삭제 버튼과 수정 버튼이 보인다 -->
					<c:if test="${rvo.id==login.id || login.gradeNo == 9 }">
						<a href="replyDelete.do?rno=${rvo.rno }&no=${vo.no}" class="button" id="replyDeleteBtn">삭제</a>
					</c:if>
				<c:if test="${rvo.id==login.id }">
<!-- 				<input /> -->
				<button name="replyUpdateBtn" class="replyUpdateBtn button" id="replyUpdateBtn" value="${vo.id }">수정</button>
			</c:if>
			</span>
		</li>
						</c:forEach>
					</c:if>
				</div>
				<div class="w3-border w3-padding form-group">
					<c:if test="${ login == null }">
						<textarea rows="5" cols="50" class="w3-input w3-border newLogin form-control" readonly>로그인을 해주세요.</textarea>
				<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}" style="float: right; margin-top: 5px;" class="button" >목록</a>
					</c:if>
				<c:if test="${ login != null }">
					<i class="fa fa-user w3-padding-16"></i> ${ login.id }
					<form action="replyWrite.do" method="post" id="replyForm">
						<input type="hidden" name="no" id="no" value="${vo.no } " class="chNo"> 
							<input type="hidden" name="rno" id="rno" value="" class="chRno"> 
								<input type="hidden" name="id" id="id" value="${login.id }"> 
							<textarea rows="5" cols="50" class="w3-input w3-border form-control chData" placeholder="댓글 작성" name="ncontent" id="ncontent"></textarea>
						<!-- 댓글 등록 버튼 -->
						<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}" style="float: right; margin-top: 5px;" class="button" >목록</a>
							<button class="button reply_btn upButton" id="reply_btn" style="float: left; margin-top: 5px;">등록</button>
							<button class="button reply_btn wrButton" id="reply_btn" style="float: left; margin-top: 5px;">등록</button>
								<input type="hidden" class="button Ureply_btn" name="Ureply_btn" id="Ureply_btn" value="수정">
					</form>
				</c:if>
				</div>
				<table>
					<tr>
				<td colspan="2">
				<!-- 페이지 오브젝트 같이 넘겨서 리스트로 돌아갈때 정보 그대로 받음 -->
				</td>
					</tr>
				</table>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판</title>

<style type="text/css">
.dataRow:hover{
	cursor: pointer;
	background: #eee;
}
</style>

<script type="text/javascript">
$(function(){
	$("#cancelBtn").click(function(){
		history.back();
	});
// 	//필수 입력 검사
// 	//필수 입력란 검사
// 	//제목
// 	if(!require($("#title"), "제목")) return false;
// 	//내용
// 	if(!require($("#content"), "내용")) return false;
// 	//작성자
// 	if(!require($("#writer"), "작성자")) return false;
		
// 	//길이 검사
// 	//제목 네 글자 이상 쓰기
// 	if(!checkLength($("#title"), "제목", 4)) return false;
// 	//내용 네 글자 이상 쓰기
// 	if(!checkLength($("#content"), "내용", 4)) return false;
// 	//작성자 두 자 이상 쓰기
// 	if(!checkLength($("#writer"), "작성자", 2)) return false;
// 	//submit 이벤트 취소
// 	//return false; --> 이게 있으면 등록을 눌러도 넘어가지 않아
});
</script>

</head>
<body>
<div class="container">
<form action="write.do" method="post" id="writeForm">
<!-- 페이지에 대한 정보 넘기기 -->
<input name="perPageNum" type="hidden" value="${pageObject.perPageNum }">
<h1>게시판 글쓰기</h1>
	<!--  제목 -->
	<div class="form-group">
		<label for="title">제목</label>
		<input name="title" class="form-control" id="title" 
		required="required" placeholder="제목을 입력해주세요.">
	</div>
	
	<!-- content -->
	<div class="form-group">
		<label for="content">내용</label>
		<textarea rows="7" name="content" class="form-control" id="content"
		 required="required" placeholder="내용을 입력해주세요."></textarea>	
	</div>
	
	<!-- id -->
	<div class="form-group">
		<label for="id">아이디</label>
		<input name="id" id="id" class="form-control" value="${vo.id }" readonly="readonly">
	</div>
	
	<!-- 버튼 -->
	<button class="button">등록</button>
	<button type="button" id="cancelBtn" class="button">취소</button>

</form>
</div>

</body>
</html>
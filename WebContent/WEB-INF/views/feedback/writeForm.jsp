<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>피드백 작성</title>

<script type="text/javascript">
$(function(){
	$("#cancelBtn").click(function(){
		// alert("취소 버튼");
		history.back();
	});
});
</script>
</head>
<body>
<div class="container">
	<h1>문의하기</h1>
	<form action="write.do" method="post">
		<div class="form-group">
			<label for="title">제목</label>
			<input name="title" id="title" class="form-control" required ="required"/>
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea name="content" id="content" class="form-control"
			 rows="18" required ="required"></textarea>
		</div>
		<button class="button">전송</button>
		<button type="reset" class="button">새로입력</button>
		<button type="button" class="button" id="cancelBtn">취소</button>
	</form>
</div>
</body>
</html>
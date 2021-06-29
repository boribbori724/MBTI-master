<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변하기</title>
 
<!-- formUtil.js 등록 -->
<script type="text/javascript" src="../js/formUtil.js"></script>

<script type="text/javascript">

$(function(){
	$("#cancleBtn").click(function(){
	history.back();
	});
	
});

</script>


</head>
<body>
<div class="container">

	<h1>답변하기</h1>

	<form action="answer.do" method="post" id="writeForm">
	<!-- 안보이면서 넘겨지는 데이터 셋팅 -->
	<input name="refNo" value="${vo.refNo }" type="hidden">
	<input name="ordNo" value="${vo.ordNo }" type="hidden">
	<input name="levNo" value="${vo.levNo }" type="hidden">
		<div class="form-group" hidden = "hidden">
			<label for="no">번호</label>
			<input name="no" class="form-control" id="no" readonly="readonly"
			value="${vo.no }" hidden = "hidden" >
		</div>
		<div class="form-group">
			<label for="title">제목</label>
			<input name="title" class="form-control" id="title" required="required"
			value="[답변] ${vo.title }" required ="required">
		</div>
		<div class="form-group" hidden = "hidden">
			<label for="sender">질문자</label>
			<input name="sender" class="form-control" id="sender" required="required"
			value="${vo.sender }" hidden = "hidden" >
		</div>
		<div class="form-group">
			<!-- 내용 -->
			<label for="content">내용</label>
			<textarea rows="18" name="content" class="form-control" id="content" required ="required"></textarea>
		</div>
		
		
		<!-- 데이터를 전송하는 type="submit" 버튼 - 버튼의 기본이므로 생략 가능한다. -->
		<button class="button">등록</button>
		<!-- 데이터를 새로 입력하는 type = "reset" 버튼 -->
		<button type="reset" class="button">새로입력</button>
		<!-- 취소하려면 버튼모양으로 사용(type="button")하고 취소의 동작을 JS로 작성한다.
			history.back() : 이전 페이지로 이동 -->
		<button type="button" onclick="history.back()" class="button">취소</button>
	</form>
	
</div>
</body>
</html>
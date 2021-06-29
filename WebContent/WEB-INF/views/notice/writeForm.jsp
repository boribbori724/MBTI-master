<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>☞공지☜</title>
<script type="text/javascript">
$(function(){
	$("#CBT").click(function(){
		// 리스트로 돌아가기 버튼
		location = "list.do?&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
	});
	 
});
</script>
<style type="text/css">
#CBT{
	cursor: pointer;
}
label:hover{
	cursor: text;
}
</style>
</head>
<body>
	<div class="container">
					<!-- 임시 텍스트 -->
	<h1 style="text-align: center;">〔공지〕</h1>	
		<form action="write.do" method="post">
			<div class="form-gruop">
				<label for="title" style="text-align: center;">제목</label>
				<input name="title" id="title" class="form-control" required="required" placeholder="제목을 입력해 주세요.">		
			</div>
			<div class="form-gruop">
				<label for="content" style="text-align: center;">내용</label>
				<textarea name="content" id="content" class="form-control" required="required" placeholder="내용을 입력하세요."
				style="height: 250px;"></textarea>
			</div>
			<div class="form-gruop">
			<br/>
				<label for="RevelDate" style="display: block;">게시기간</label>
<!-- 				<label for="endDate">게시 종료일</label> -->
				<input name="startDate" id="startDate" class="form-control" type="date" required="required" style="width:180px; display: inline-block;">
				 <label style="font-size: 25px; text-align: inherit;">~</label>
				<input name="endDate" id="endDate" class="form-control" type="date" required="required" style="width:180px; display: inline-block;">		
			</div>
			<br/>
			<div style="margin-bottom: 10px;">
			<button class="button" style="float: left;">작성</button>
			<a id="CBT" class="button" style="float: right;"> 취소</a>
			</div>
		</form>
	</div>
</body>
</html>
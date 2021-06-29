<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판</title>

<script type="text/javascript">
$(function(){
	//아래서 onclick 처리한 걸 위로 올려서 처리
	//이벤트 처리
	//취소버튼을 클릭했을 때 이전 페이지(list)로 돌아간다
	$("#cancelBtn").click(function(){
		//alert("취소"); --> 처리가 잘 되면 주석처리 하자
		//이전 페이지 이동
		history.back();
	});
});
</script>
</head>
<body>
<div class="container">
<form action="update.do" method="post" id="updateForm">
	<!-- 번호 -->
	<div class="form-group">
		<label for="no"></label>
		<input name="no" class="form-control" id="no" readonly="readonly" type="hidden" value="${vo.no}"/>
	</div>
	<!-- 제목 -->
	<div class="form-group">
		<label for="title">제목</label>
		<input name="title" class="form-control" id="title" value="${vo.title}"/>
	</div>
	
	<!-- 내용 -->
	<div class="form-group">
	<label for="content">내용</label>
	<!-- textarea : 글쓰는 영역, rows : 줄수 -->
	<textarea rows="7" name="content" class="form-control" 	id="content" 
	required="required" placeholder="내용을 입력해주세요.">${vo.content}</textarea>
	</div>
	
	<!-- 작성자 -->
	<div class="form-group">
	<label for="id">아이디</label>
	<input name="id" id="id" class="form-control" required="required" readonly="readonly"
	value="${vo.id}">
	</div>
	
	<!-- 버튼 -->
	<!-- 게시글 등록 -->
	<button class="button">등록</button>
	
	<!-- 새로 입력 -->
	<!-- type : 해당 버튼의 타입을 지정(원래 상태로 돌려준다). 새로 입력이니까 원래 입력한 데이터를 지운다 -->
	<button type="reset" class="button">다시 입력</button>
	
	<!-- 취소 : 이전 페이지로 이동 -->
	<!-- onclick="history.back()"은 위에 function(자바 스크립트)에서 작성 -->
	<button type="button" id="cancelBtn" class="button">취소</button>
</form>
</div>

</body>
</html>
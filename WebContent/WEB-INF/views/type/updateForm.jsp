<%@page import="com.mbti.main.controller.Beans"%>
<%@page import="com.mbti.main.controller.ExeService"%>
<%@page import="com.mbti.type.vo.TypeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유형관리 수정</title>
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
	<h1>유형관리 수정</h1>
<!-- 	  input tag의 type="file"로 지정한다. -->
	<form action="update.do" method="post" id="writeForm" >
		<input name="page" value="${pageObject.page }" type="hidden">
		<input name="perPageNum" value="${pageObject.perPageNum }" type="hidden">
		<div class="form-group">
			<label for="no">번호</label>
			<input name="no" id="no" class="form-control" value="${vo.no}" 
			readonly="readonly"/>
		</div>
		<div class="form-group">
			<label for="type">유형</label>
			<input name="type" id="type" class="form-control" value="${vo.type }" />
		</div>
		<div class="form-group">
			<label for="name">이름</label>
			<input name="name" id="name" class="form-control" value="${vo.name }" />
		</div>
		<div class="form-group">
			<label for="title">제목</label>
			<textarea name="title" id="title" class="form-control" rows="2">${vo.title }</textarea>
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea name="content" id="content" class="form-control" rows="5"
			 >${vo.content }</textarea>
		</div>
		<div class="form-group">
			<label for="image">유형 이미지</label>
			<textarea name="image" id="image" class="form-control" rows="5"
			 >${vo.image }</textarea>
		</div>
		<div class="form-group">
			<label for="gType">잘 맞는 유형</label>
			<textarea name="gType" id="gType" class="form-control" rows="2"
			 >${vo.gType }</textarea>
		</div>
		<div class="form-group">
			<label for="gImage">잘 맞는 유형 이미지</label>
			<textarea name="gImage" id="gImage" class="form-control" rows="5"
			 >${vo.gImage }</textarea>
		</div>
		<div class="form-group">
			<label for="bType">안 맞는 유형 </label>
			<textarea name="bType" id="bType" class="form-control" rows="2"
			 >${vo.bType }</textarea>
		</div>
		<div class="form-group">
			<label for="bImage">안 맞는 유형 이미지</label>
			<textarea name="bImage" id="bImage" class="form-control" rows="5"
			 >${vo.bImage }</textarea>
		</div>
		<button class="button">수정</button>
		<button type="reset" class="button">새로입력</button>
		<button type="button" id="cancelBtn" class="button">취소</button>
	</form>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">

//객체 선택에 문제가 있다. 아래 Document가 다 로딩이 되면 실행되는 스크립트 작성
//jquery -> $(function(){처리문 만들기;}) = jquery(function(){처리문 만들기;})
$(function(){ // jquery에서 익명함수를 전달해서 저장해놨다가 Document가 로딩이 다되면 호출해서 처리해준다.
	// 삭제 버튼을 클릭하면 실제적으로 삭제를 진행할 건지에 대한 여부를 물어본다.
	$("#deleteBtn").click(function(){
		if(!confirm("정말로 탈퇴 하시겠습니까?")) return false; // a tag의 이동 취소
	});
	
// 	$("#updateBtn").click(function() {
		
// 		alert("준비중인 서비스 입니다.");
		
// 		return false;
		
// 	});
// });

</script>

<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">

<meta charset="UTF-8">
<title>회원 정보 보기</title>
</head>
<body>
<div class="container">
	<h1>내 정보 보기</h1>
	<table class="table">
		<tr>
			<th>아이디</th>
			<td>${vo.id }</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${vo.name }</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${vo.gender }</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>${vo.birth }</td>
		</tr>
		<tr>
			<th>연락처</th>
			<td>${vo.tel }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${vo.email }</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<th>상태</th> -->
<%-- 			<td>${vo.status }</td> --%>
<!-- 		</tr> -->
		<tr>
			<th>등급번호</th>
			<td>${vo.gradeNo }</td>
		</tr>
		<tr>
			<th>등급명</th>
			<td>${vo.gradeName }</td>
		</tr>
</table>

<a href="updateForm.do" id="updateBtn" class="button">정보 수정</a>
<a href="delete.do" id="deleteBtn" class="button">탈퇴</a>
</div>
</body>
</html>
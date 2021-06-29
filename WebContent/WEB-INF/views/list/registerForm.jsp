<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MBTI Test 등록</title>
<script type="text/javascript">

	$(function() {
		
		$("#cancleBtn").click(function() {
			
			history.back();
			
		});
		
		$("#registerForm").submit(function() {
			
			alert(!require)
			
			if(!require($("#title"), "제목")) {
				
				return false;
				
			}
			
			if(!require($("#image"), "이미지 url")) {
				
				return false;
				
			}
			
			if(!require($("#url"), "url 주소")) {
				
				return false;
				
			}
			
			if(!checkLength($("#title"), "제목", 5)) {
				
				return false
			}
			
		});
		
	});

</script>
</head>
<body>
 <div class="container">
  <h1>Test 등록</h1>
  <form action="register.do" method="post" id="registerForm">
   <input name="perPageNum" type="hidden" value="${pageObject.perPageNum }"> 
   <div class="form-group">
    <label for="title">제목</label>
    <input class="form-control" id="title" name="title"  placeholder="테스트 제목은 5자 이상 입력하셔야 합니다." autofocus="autofocus">
   </div>
   <div class="form-group">
    <label for="image">이미지</label>
    <input class="form-control" id="image" name="image" required="required" placeholder="이미지 url을 입력해 주세요">
   </div>
   <div class="form-group">
    <label for="url">URL 주소</label>
    <input class="form-control" id="url" name="url" required="required" placeholder="Test Url을 입력해 주세요">
   </div>
   <button class="button">등록</button>
   <button type="button" id="cancleBtn" class="button">취소</button> 
  </form>
 </div>
</body>
</html>
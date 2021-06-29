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
  <form action="update.do" method="post" id="updateForm">
   <input name="perPageNum" type="hidden" value="${pageObject.perPageNum }"> 
   <input name="no" type="hidden" value="${vo.no }">	
   <div class="form-group">
    <label for="title">타이틀</label>
    <input class="form-control" id="title" name="title" value="${vo.title }" autofocus="autofocus">
   </div>
   <div class="form-group">
    <label for="image">이미지</label>
    <input class="form-control" id="image" name="image" required="required" value="${vo.image }">
   </div>
   <div class="form-group">
    <label for="url">URL 주소</label>
    <input class="form-control" id="url" name="url" required="required" value="${vo.url }">
   </div>
   <button class="button">수정</button>
   <button type="button" id="cancleBtn" class="button">취소</button> 
  </form>
 </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MBTI Test 등록</title>
<style type="text/css">


	th,td{
	   border: 1px solid white;
	}
</style>
<script type="text/javascript">

	$(function() {
		
		$(".updateBtn").click(function() {
			
			//alert("수정");
			
			location = "updateForm.do?no=${vo.no}&page=${param.page}&perPageNum=${param.perPageNum}";
			
		});
		
		$(".deleteBtn").click(function() {
			
			//alert("삭제");
			if(!confirm("정말 삭제하시겠습니까?")) {
				
				return false;
				
			}
			
			location = "delete.do?no=${vo.no }&page=${param.page}&perPageNum=${param.perPageNum}";
			
		});
		
		$(".listBtn").click(function() {
			
			//alert("리스트");
			
			location = "adminList.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum }";
			
		});
		
	});

</script>
</head>
<body>
 <div class="container">
  <h1>Test 등록</h1>
  <table class="table"> 
   <tr>
    <th>번호</th>
    <td width="1000px">${vo.no }</td>
   </tr> 
   <tr>
    <th>타이틀</th>
    <td height="100px">${vo.title }</td>
   </tr>
   <tr>
    <th>이미지URL</th>
    <td>${vo.image }</td>
   </tr>
   <tr>
    <th>이미지</th>
    <td><center><img alt="" src="${vo.image }"></center></td>
   </tr>
   <tr>
    <th>url 주소</th>
    <td>${vo.url }</td>
   </tr>
   <tr>
    <td colspan="4">
     <button type="button" class="updateBtn button" style="float: left;">수정</button>
     <button type="button" class="deleteBtn button" style="float: left;">삭제</button>
     <button type="button" class="listBtn button" >리스트</button>
    </td>
   </tr>
  </table>
 </div>
</body>
</html>
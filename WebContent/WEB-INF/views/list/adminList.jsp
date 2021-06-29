<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test list</title>
</head>
<body>
 <div class="container">
  <h1>Test List</h1>
 <div class="line"></div>
  <br>
  <div class="quotes">
   <c:forEach items="${list }" var="vo" varStatus="vs">
    <div class="card movie ">
     <div class="movie-image box box1"> <h2>${vo.title }</h2> <a href="view.do?no=${vo.no }&page=${pageObject.page }&perPageNum=${pageObject.perPageNum }"><img src="${vo.image }" alt="" /></a> </div>
      <div class="rating">
       <span class="comments">hit ${vo.hit }</span> 
      </div>
     </div>
   </c:forEach>
  </div>
  <c:if test="${login.gradeNo == 9 }">
   <div class="registerBtn"> 
    <a href="registerForm.do" class="button">등록</a>
   </div>
  </c:if>
  <div>
   <pageObject:pageNav listURI="list.jsp" pageObject="${pageObject }"/>
  </div>
 </div>
</body>
</html>
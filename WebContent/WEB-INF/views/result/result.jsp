<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>${vo.type }</title>
<style>
body, h1,h2,h3,h4,h5,h6 {font-family: "Montserrat", sans-serif}
/* Set the width of the sidebar to 120px */
/* Add a left margin to the "page content" that matches the width of the sidebar (120px) */
/* Remove margins from "page content" on small screens */
/* @media only screen and (max-width: 600px) {#main {margin-left: 0}} */
pre {
 
	background:none;	
	border:  none;
	color: #ccc; 
	font-style: italic;
}
pre {
white-space: pre-wrap;
}

img {
  border-radius: 8px;
}
</style>
</head>
<body>
 
 <div class="w3-container w3-padding-32 w3-center" id="title">
    <h1 class="w3-x-large" style=" font-style: italic; font-weight: bold;"><pre>${vo.name }</pre></h1>
    <pre style="font-size: 30pt;font-weight: bold;  font-style:italic;">${vo.title }</pre><br/>
    

  <div class="w3-content w3-justify w3-text-grey w3-padding-64" id="about">
  <center> <img src="${vo.image }" class="w3-image"></center>
    	<h3 style="color: #b3b3b3; line-height: 150%;"><pre style="font-family: inherit;">${vo.content }</pre> 
    </h3>
	<br>
	<br>
    <h2 class="w3-text-light-grey">Matching Type</h2>
    <hr style="width:200px" class="w3-opacity">
    <center><img src="${vo.gImage}" class="w3-image" style="margin-left: auto;
  margin-right: auto;"></center>
    	<h3 style="color: #b3b3b3; line-height: 150%; ">
    <pre style="font-family: inherit;">${vo.gType }</pre>
    </h3>
	<br>
	<br>
    <h2 class="w3-text-light-grey">Mismatching Type</h2>
    <hr style="width:200px" class="w3-opacity">
    <center><img src="${vo.bImage}" class="w3-image"></center>
    	<h3 style="color: #b3b3b3; line-height: 150%; font-style: normal;">
   <pre style="font-family: normal;">${vo.bType }</pre>
    </h3>
    
  </div>
  

<!-- END PAGE CONTENT -->
</div>
<a href="updateForm.do?no=${vo.no }" class="button" style="font-size: 14pt" >수정</a>
<a href="delete.do?no=${vo.no }&perPageNum=${pageObject.perPageNum }" class="button" style="font-size: 14pt">삭제</a>
<!--EL 객체 -pageObject.page =>reqest.getParameter("page")  -->
<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}" 
class="button" style="font-size: 14pt">list</a>
</body>
</html>
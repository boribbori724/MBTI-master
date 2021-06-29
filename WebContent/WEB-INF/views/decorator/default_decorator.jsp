<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="referrer" content="no-referrer"/>
<title>4walls<decorator:title /></title>
<!--  부트 스트랩 라이브러리 등록을 전체적으로 진행을 했다. Filter가 적용이 되면 개별적으로 한 것은 지워야 한다. -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/js/formUtil.js"></script>
<script type="text/javascript" src="/js/jquery-func.js"></script>

<link rel="stylesheet" href="../css/style.css" type="text/css" media="all" />
<decorator:head/>

</head>
<body>

  <div id="header"> 
    <h1 id="logo"><a href="../list/list.do"><img alt="abc" src="../css/images/MBTI_logo8.png">4Walls</a></h1>
    <div id="navigation">
      <ul>
        <li><a class="active" href="/list/list.do">TEST 하러가기</a></li>
        <c:if test="${!empty login && login.gradeNo == 9 }">
         <li><a class="active" href="/list/adminList.do">TEST 수정 하기</a></li>
        </c:if>
        <li><a href="/notice/list.do">공지사항</a></li>
        <li><a href="/board/list.do">게시판</a></li>
        <li><a href="/feedback/list.do">피드백 게시판</a></li>
        <c:if test="${!empty login && login.gradeNo == 9 }">
        	<li><a href="/feedback/adminList.do">관리자용 피드백 게시판</a></li>
        </c:if> 
        <c:if test="${!empty login && login.gradeNo == 9 }">
        	<li><a href="/type/list.do">유형관리</a></li>
        </c:if>
        <c:if test="${empty login }">
         <li><span class="glyphicon glyphicon-user"></span>&nbsp;<a href="/member/writeForm.do">회원가입</a></li>
         <li><span class="glyphicon glyphicon-log-in"></span>&nbsp;<a href="/member/loginForm.do">로그인</a></li>
        </c:if>
        <c:if test="${!empty login && login.gradeNo == 9 }">
         <li><a href="/member/list.do">회원관리</a></li>
        </c:if>
        <c:if test="${!empty login }">
         <li><span class="glyphicon glyphicon-user"></span>&nbsp;<a href="/member/view.do">${login.id }</a></li>
         <li><span class="glyphicon glyphicon-log-out"></span>&nbsp;<a href="/member/logout.do">로그아웃</a></li> 
        </c:if>

      </ul>
    </div> 
    <div id="sub-navigation">
<!--       <ul> -->
<!--         <li><a href="#">SHOW ALL</a></li>  -->
<!--         <li><a href="#">LATEST TRAILERS</a></li> -->
<!--         <li><a href="#">TOP RATED</a></li> -->
<!--         <li><a href="#">MOST COMMENTED</a></li> -->
<!--       </ul> -->

<!--       <div id="search"> -->
<!--         <form action="#" method="get" accept-charset="utf-8"> -->
<!--           <label for="search-field">시계를 넣어보자!</label> -->
<!--           <input type="text" name="search field" value="똑딱똑딱...." id="search-field" class="blink search-field"  /> -->
<!--           <input type="submit" value="GO!" class="search-button" /> -->
<!--         </form> -->
<!--       </div> -->
      
    </div>
  </div>
	<article>
		<decorator:body />
	</article>
	<footer>
		  <div id="sub-navigation">
<!--     <p class="rf">페이지의 저작권은 1조에게 있습니다.</p> -->
    <div style="clear:both;"></div>
  </div>
	</footer>
</body>
</html>
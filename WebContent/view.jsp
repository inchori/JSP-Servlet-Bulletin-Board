<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<meta name="viewport" content="width=device-width", initial-scale="1">
		<link rel="stylesheet" href="css/bootstrap.css">
		<title>JSP Web Bulletin Board</title>
	</head>
	<body>
		<%
			String userID = null;
			if(session.getAttribute("userID") != null) {
				userID = (String) session.getAttribute("userID");
			}
		%>
		<nav class="navbar navbar-default">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>				
				</button>
				<a class="navbar-brand" href="main.jsp">JSP Web Board</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="main.jsp">메인</a></li>
					<li class="active"><a href="./list">게시판</a></li>
				</ul>
				<%
					if(userID == null) {
				%>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">접속하기<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="login.jsp">로그인</a></li>
							<li><a href="join.jsp">회원가입</a></li>
						</ul>	
					</li>
				</ul>
				<%
					} else {
				%>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">회원관리<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="./logout">로그아웃</a></li>
						</ul>	
					</li>
				</ul>
				<%
					}
				%>
			</div>
		</nav>
		<div class="container">
			<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글보기</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="width: 20%;">글 제목</td>
							<td colspan="2">${BBS.bbsTitle}</td>
						</tr>
						<tr>
							<td>작성자</td>
							<td colspan="2">${BBS.userID}</td>						
						</tr>
						<tr>
							<td>작성일자</td>
							<td colspan="2">${BBS.bbsDate}</td>						
						</tr>
						<tr>
							<td>내용</td>
							<td colspan="2" style="height: 200px; text-align: left;">${BBS.bbsContent}</td>						
						</tr>
					</tbody>
				</table>
				<a href="./list" class="btn btn-primary">목록</a>
				<c:set var="str" value="${BBS.userID}"/>
				<%
					String str = (String) pageContext.getAttribute("str");
					if(str.equals(userID)) {
				%>
					<a href="modify?bbsID=${BBS.bbsID}" class="btn btn-primary">수정</a>
					<a href="delete?bbsID=${BBS.bbsID}" type="submit" class="btn btn-primary">삭제</a>
				<%
					}
				%>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		<script src="js/bootstrap.js"></script>
	</body>
</html>
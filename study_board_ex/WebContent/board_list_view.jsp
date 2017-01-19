<%@page import="com.board.dto.Dto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet">
</head>
<body>

<% 
	ArrayList<Dto> dtos = null;
	if(request.getAttribute("list")!=null){
		dtos = (ArrayList<Dto>)request.getAttribute("list");
	}
%>
	<table class="table table-striped" border="1">
		<tr align="center">
			<td>번호</td>
			<td>작성자</td>
			<td class="board_title">제목</td>
			<td>작성날짜</td>
			<td>조회수</td>
		</tr>
		<% 
			for(int i=0; i<dtos.size(); i++){
		%>
		<tr align="center">
			<td><%=dtos.get(i).getBid()%></td>
			<td><%=dtos.get(i).getBname()%></td>
			<td align="left">
			<a href= "board_content_view.do?bid=<%=dtos.get(i).getBid()%>"><%=dtos.get(i).getBtitle()%></a>
			</td>
			<td><%=dtos.get(i).getBdate()%></td>
			<td><%=dtos.get(i).getBhit()%></td>
		</tr>
		<%}	%>
	</table>
	<input class="btn" type="button" value="글작성" onclick="window.location.href='board_write_view.do'">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
</body>
</html>
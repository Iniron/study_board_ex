<%@page import="com.board.dto.Dto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
		Dto dto = null;
		if(request.getAttribute("content")!=null){
			dto = (Dto)request.getAttribute("content");
		}
%>
	<form action="board_write.do" method="post">
		<table border="1">
		<tr>
			<td size="200">글번호</td>
			<td><%=dto.getBid()%></td>
		</tr>
		<tr>
			<td>조회수</td>
			<td><%=dto.getBhit()%></td>
		</tr>
		<tr>
			<td>작성일</td>
			<td><%=dto.getBdate()%></td>
		</tr>	
		<tr>
			<td>이름</td>
			<td><%=dto.getBname()%></td>
		</tr>	
		<tr>
			<td>제목</td>
			<td><%=dto.getBtitle()%></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><%=dto.getBcontent()%></td>
		</tr>
		</table>
		<input type="button" value="수정" onclick="window.location.href='board_modify_view.do?bid=<%=dto.getBid()%>'">
		<input type="button" value="삭제" onclick="window.location.href='board_delete.do?bid=<%=dto.getBid()%>'">
		<input type="button" value="취소" onclick="window.location.href='board_list_view.do'">				
	</form>
</body>
</html>
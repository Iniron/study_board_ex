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
	<form action="board_update.do" method="post">
	<!-- bid, btitle, bcontent -->
		<input type="hidden" name="bid" value="<%=dto.getBid()%>">
		<table border="1">
		<tr>
			<td size="200">이름</td>
			<td><%=dto.getBname()%></td>
		</tr>	
		<tr>
			<td>제목</td>
			<td><input type="text" name="btitle" value="<%=dto.getBtitle()%>"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea rows="10" cols="50" name="bcontent"><%=dto.getBcontent()%></textarea></td>
		</tr>
		</table>
		<input type="submit" value="입력">
		<input type="button" value="취소" onclick="history.go(-1)">				
	</form>
</body>
</html>
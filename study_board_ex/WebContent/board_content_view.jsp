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
			<td size="200">�۹�ȣ</td>
			<td><%=dto.getBid()%></td>
		</tr>
		<tr>
			<td>��ȸ��</td>
			<td><%=dto.getBhit()%></td>
		</tr>
		<tr>
			<td>�ۼ���</td>
			<td><%=dto.getBdate()%></td>
		</tr>	
		<tr>
			<td>�̸�</td>
			<td><%=dto.getBname()%></td>
		</tr>	
		<tr>
			<td>����</td>
			<td><%=dto.getBtitle()%></td>
		</tr>
		<tr>
			<td>����</td>
			<td><%=dto.getBcontent()%></td>
		</tr>
		</table>
		<input type="button" value="����" onclick="window.location.href='board_modify_view.do?bid=<%=dto.getBid()%>'">
		<input type="button" value="����" onclick="window.location.href='board_delete.do?bid=<%=dto.getBid()%>'">
		<input type="button" value="���" onclick="window.location.href='board_list_view.do'">				
	</form>
</body>
</html>
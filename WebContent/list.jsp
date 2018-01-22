<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.dao.GuestbookDao" %>
<%@ page import="com.javaex.vo.GuestbookVo"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="gb" method="post">
	<table border=1 width=500>
		<tr>
			<td>이름</td><td><input type="text" name="name"></td>
			<td>비밀번호</td><td><input type="password" name="password"></td>
		
			<td><input type="hidden" name="a" value="add"></td></tr>
		<tr>
			<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
		</tr>
		<tr>
			<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
		</tr>
	</table>
	</form>
	<br/>
		    
<% GuestbookDao dao = new GuestbookDao();
		List<GuestbookVo> gList = dao.getList();
		
		for(GuestbookVo gVo : gList){
			int no = gVo.getNo();
			String name = gVo.getName();
			String content = gVo.getContent();
			String date = gVo.getRegDate();
	%>
	<table width=510 border=1>
			<tr>
	<td><%=no %></td>
	<td><%=name %></td>
	<td><%=date %></td>
	<td><a href="gb?a=deleteform&no=<%=no %>">삭제</a></td>
			</tr>
			<tr>
				<td colspan=4><%=content %></td>
			</tr>
		</table>
		<br />

	<% } %>
	
</body>
</html>
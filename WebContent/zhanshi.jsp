<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=request.getAttribute("sname")%>学生信息</title>
</head>
<body>
	姓名：<%=request.getAttribute("sname")%></br>
	班级：<%=request.getAttribute("scno")%></br>
	出生年月：<%=request.getAttribute("sage")%>	
</body>
</html>
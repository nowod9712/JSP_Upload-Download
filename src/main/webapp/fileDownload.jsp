<%@ page import="java.io.File" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 파일 다운로드</title>
</head>
<body>
<%
	//서버 외부에 업로드 위치 설정 - 시큐어 코딩
	String directory = "C:/ldw_data/upload";
	String files[] = new File(directory).list();
	
	for(String file : files){
		out.write("<a href=\"" + request.getContextPath() + "/downloadAction?file=" +
			java.net.URLEncoder.encode(file, "UTF-8") + "\">" + file + "</a><br>");
	}
	
%>
</body>
</html>
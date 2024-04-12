<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<%
	// Check if userId is present in the session
    String userId =(String) session.getAttribute("userId");
    if (userId != null) {
        // If userId is present, redirect to expenses page with parameters
        String redirectURL = "expenses.jsp?command=INIT"; // Add parameters to the URL
        response.sendRedirect(redirectURL);
    } else {
        // If userId is not present, redirect to login page
        response.sendRedirect("login.jsp");
    }
	%>
		
</body>
</html>
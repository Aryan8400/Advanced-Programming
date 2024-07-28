<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/welcome.css" />
</head>
<body>
	<%
		String userSession = (String) session.getAttribute("username");
		String cookieUsername  = null;
		String cookieSessionID = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie: cookies){
				if(cookie.getName().equals("user")) cookieUsername = cookie.getValue();
				if(cookie.getName().equals("JSESSIONID")) cookieSessionID = cookie.getValue();
			}
		}
	%>
	<div class="welcome-container">
		<h1>Hello <%=cookieUsername %>. Welcome to our page!</h1>
		<h3>Cookie session Id is <%=cookieSessionID %></h3>
		<p><%=userSession %></p>
		<a href="${pageContext.request.contextPath}/pages/home.jsp">
			<button class="home-button">Continue to Home Page</button>
		</a>
	</div>
</body>
</html>
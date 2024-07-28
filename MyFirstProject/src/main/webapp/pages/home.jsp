<%@ page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%
// Get the session and request objects
HttpSession userSession = request.getSession();
String currentUser = (String) userSession.getAttribute("username");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/home.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/footer.css" />
</head>
<body>
	<jsp:include page="header.jsp" />

	<%
	String successMessage = (String) request.getAttribute(StringUtils.SUCCESS_MESSAGE);
	String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);

	if (errorMessage != null && !errorMessage.isEmpty()) {
	%>
	<!-- Display error message with a designated style -->
	<p class="error-message"><%=errorMessage%></p>
	<%
	}

	if (successMessage != null && !successMessage.isEmpty()) {
	%>
	<!-- Display success message with a designated style -->
	<p class="success-message"><%=successMessage%></p>
	<%
	}
	%>

	<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/college_app" user="root" password="" />

	<!-- Executing Query Using SQL Tag Library -->
	<sql:query var="students" dataSource="${dbConnection}">
        SELECT first_name, last_name, user_name, subject, image FROM student_info WHERE role = "user"
    </sql:query>

	<!-- Executing Query Using SQL Tag Library -->
	<sql:query var="userType" dataSource="${dbConnection}">
        SELECT role FROM student_info WHERE user_name = '<%=currentUser%>'
    </sql:query>

	<div class="students-info">
		<div class="users">
			<c:forEach var="student" items="${students.rows}">
				<div class="card">
					<img src="http://localhost:8080/images/${student.image}"
						class="card-img-top" alt="...">
					<div class="card-body">
						<h4 class="card-title">${student.first_name}
							${student.last_name}</h4>
						<h5 class="card-text">${student.subject}</h5>
					</div>
					<c:if test="${userType.rows[0].role == 'admin'}">
						<form method="post" action="${pageContext.request.contextPath}/ModifyServlet">
							<input type="hidden" name="updateId" value="${student.user_name}" />
							<button type="submit">Update</button>
						</form>
						<form id="deleteForm-${student.user_name}" method="post"
							action="${pageContext.request.contextPath}/ModifyServlet">
							<input type="hidden" name="deleteId" value="${student.user_name}" />
							<button type="button"
								onclick="confirmDelete('${student.user_name}')">Delete</button>
						</form>
					</c:if>
				</div>
			</c:forEach>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
<script>
	function confirmDelete(userName) {
		if (confirm("Are you sure you want to delete this user: " + userName
				+ "?")) {
			document.getElementById("deleteForm-" + userName).submit();
		}
	}
</script>
</html>

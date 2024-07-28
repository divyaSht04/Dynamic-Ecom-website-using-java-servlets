<%@page import="util.Stringutil"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/LaptopGalaxy/stylesheet/login.css" />
</head>
<body>
	<div class="login-box">
		<%
		String errorMessage = (String) request.getAttribute(Stringutil.ERROR_MESSAGE);
		if (errorMessage != null && !errorMessage.isEmpty()) {
		%>
		<p class="error-message">
			<%=errorMessage%>
		</p>
		<%
		}
		%>
		<h2>Login</h2>
		<form action="LoginServlet" method="post">
			<div class="row">
				<div class="col">
					<label for="username">userName:</label> <input type="text"
						id="username" name="userName" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> <input type="password"
						id="password" name="password" required>
				</div>
			</div>
			<button type="submit" class="login-button">Login</button>
		</form>
		<p> dont have an account? : GO to register in page: <a href="Register.jsp"> Register Page </a> </p>
	</div>
</body>
</html>
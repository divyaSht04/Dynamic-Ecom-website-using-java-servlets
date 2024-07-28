<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Profile Card</title>
<%
String contextPath = request.getContextPath();
%>
<style>
.profile-card {
	width: 400px;
	border: 1px solid #ccc;
	border-radius: 10px;
	overflow: hidden;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	background-color: #fff;
	margin: 50px auto;
}

.profile-image {
	height: 40%;
	background-size: cover;
	background-position: center;
}

.profile-image img {
	object-fit: cover;
	height: 100%;
	width: 100%;
	border-radius: 50%;
}

.profile-info {
	padding: 20px;
}

.profile-info h2 {
	text-align: center;
	margin: 0 0 10px;
	font-size: 24px;
	font-weight: 800;
	color: #3200fb;
}

.profile-info table {
	width: 100%;
	font-size: 16px;
	font-weight: 600;
	color: #555;
}

.profile-info table td {
	padding: 5px 0;
}

.profile-info table td:first-child {
	width: 35%;
	color: #3200fb;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />

	<c:choose>
		<c:when test="${not empty userInfo}">
			<c:forEach var="user" items="${userInfo}">
				<div class="profile-card">
					<div class="profile-image">
						<img
							src="<%=contextPath %>/resources/images/user/${user.getImageUrlFromPart()}"
							alt="Profile Image">
					</div>
					<div class="profile-info">
						<h2>${user.getFirstName()}${user.getLastName()}</h2>
						<table>
							<tr>
								<td>Number:</td>
								<td>${user.getPhoneNumber()}</td>
							</tr>
							<tr>
								<td>Date of Birth:</td>
								<td>${user.getDateOfBirth()}</td>
							</tr>
							<tr>
								<td>Gender:</td>
								<td>${user.getGender()}</td>
							</tr>
							<tr>
								<td>Email:</td>
								<td>${user.getEmail()}</td>
							</tr>
							<tr>
								<td>Address:</td>
								<td>${user.getAddress()}</td>
							</tr>
						</table>
						<button>
							<a href='UserProfileUpdate.jsp'> Update Profile </a>
						</button>
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>No user information available.</p>
		</c:otherwise>
	</c:choose>

	<jsp:include page="footer.jsp" />
</body>
</html>

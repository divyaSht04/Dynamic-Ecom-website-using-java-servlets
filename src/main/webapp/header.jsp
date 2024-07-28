<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/header.css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<a href="#" class="logo"> <img
		src="<%=request.getContextPath()%>/resources/logo.png" alt="Logo" />
	</a>
	<nav>
		<ul class="nav-options">
			<c:choose>
				<c:when test="${empty isAdmin}">
				<li><a href="IndexServlet"> Home</a></li>
					<li><a href="UserProfileServlet"> User Profile</a></li>
					<li><a href="aboutUs.jsp"> About Us </a></li>
				</c:when>
			</c:choose>

			<c:if test="${not empty isAdmin}">
				<li><a href="AdminServlet"> Dashboard </a></li>
			</c:if>
		</ul>
	</nav>
	<div class="nav-icons">
		<c:choose>
			<c:when test="${empty isUser and empty isAdmin}">
				<a href="<%=request.getContextPath()%>/login.jsp">
					<button class="login-btn">Login</button>
				</a>
			</c:when>
			<c:otherwise>
				<a href="LogoutServlet">
					<button class="login-btn">Logout</button>
				</a>
			</c:otherwise>
		</c:choose>
		<c:if test="${not empty isUser and empty isAdmin}">
			<a href="CartPageServlet" class="cart-icon"> <img
				src="<%=request.getContextPath()%>/resources/cart.png" alt="Cart" />
			</a>
		</c:if>
	</div>
</header>

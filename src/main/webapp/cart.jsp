<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	scroll-behavior: smooth;
	font-family: "Jost", sans-serif;
	list-style: none;
	text-decoration: none;
}

section {
	padding: 5% 10%;
}

header.sticky {
	background: #fff;
	padding: 20px 10%;
	box-shadow: 0px 0px 10px rgb(0 0 0/ 10%);
}

.footer {
	background: #000;
	color: #ccc;
	height: 250px;
	text-align: center;
	padding: 50px 0;
}

.smallContainer {
	margin: 80px auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 40px;
}

th {
	text-align: left;
	padding: 10px;
	color: #fff;
	background: #0056b3;
	font-weight: normal;
}

td {
	padding: 15px;
	border-bottom: 1px solid #ddd;
}

td img {
	width: 80px;
	height: 80px;
	margin-right: 10px;
}

.cart-info {
	display: flex;
	align-items: center;
}

.cart-info div {
	margin-left: 10px;
}

.cart-info small {
	display: block;
	margin-top: 5px;
	color: #888;
}

.cart-info a {
	color: #ff0000;
	font-size: 14px;
	display: block;
	margin-top: 10px;
}

.quantity-buttons {
	display: flex;
	align-items: center;
}

.quantity-buttons input {
	width: 40px;
	text-align: center;
	border: 1px solid #ddd;
	height: 30px;
}

.quantity-buttons button {
	background-color: #0056b3;
	color: white;
	border: none;
	width: 30px;
	height: 30px;
	cursor: pointer;
}

.quantity-buttons button:hover {
	background-color: #004494;
}

.totalPrice {
	display: flex;
	justify-content: flex-end;
	margin-top: 30px;
}

.totalPrice table {
	border-top: 3px solid #0056b3;
	width: 100%;
	max-width: 350px;
}

.totalPrice td {
	padding: 10px;
	text-align: right;
}

th:last-child, td:last-child {
	text-align: right;
}
</style>
<%
String contextPath = (String) request.getContextPath();
%>
<body>
    <jsp:include page="header.jsp" />
    <c:choose>
        <c:when test="${not empty cartItems}">
            <section class="smallContainer">
                <table>
                    <tr>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Subtotal</th>
                    </tr>
                    <c:forEach items="${cartItems}" var="item">
                        <tr>
                            <td>
                                <div class="cart-info">
                                    <img
                                        src="<%=contextPath%>/resources/images/products/${item.getImageUrlFromPart()}"
                                        alt="${item.getName()}" />
                                    <div>
                                        <p>Name: ${item.getName()}</p>
                                        <small>Price: ${item.getPrice()} $ </small>
                                        <form action="RemoveItemServlet" method="post">
                                            <input type="hidden" name="id" value="${item.getId()}" />
                                            <button type="submit">Remove Item</button>
                                        </form>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="quantity-buttons">
                                    <form action="IncrementDecrementServlet" method="get">
                                        <input type="hidden" name="id" value="${item.getId()}" />
                                        <button name="action" value="dec">-</button>
                                        <input type="text" value="${item.getQuantity()}" readonly />
                                        <button name="action" value="inc">+</button>
                                    </form>
                                </div>
                            </td>
                            <td>${item.getTotalPrice()} $</td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="totalPrice">
                    <table>
                        <tr>
                            <td>Total</td>
                            <td>
                                <c:set var="total" value="0" />
                                <c:forEach items="${cartItems}" var="item">
                                    <c:set var="total" value="${total + item.getTotalPrice()}" />
                                </c:forEach>
                                ${total} $
                            </td>
                        </tr>
                    </table>
                </div>
                <form action="CheckOutServlet" method="post" style="text-align: center;">
                	<input type="hidden" name="total" value="${total}"/>
                    <button type="submit" style="width: 90px; height: 40px">Buy Now</button>
                </form>
            </section>
        </c:when>
        <c:otherwise>
            <p>Your cart is empty. Add some products to your cart!</p>
        </c:otherwise>
    </c:choose>

    <jsp:include page="footer.jsp" />
</body>
</html>
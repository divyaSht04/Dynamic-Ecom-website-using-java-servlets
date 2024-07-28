<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Laptop Galaxy</title>
<!-- CSS Link -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/style.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" />
<link href="https://fonts.googleapis.com/css2?family=Jost:wght@700&display=swap" rel="stylesheet" />
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
    integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    scroll-behavior: smooth;
    font-family: 'Jost', sans-serif;
    list-style: none;
    text-decoration: none;
}

.Acontainer {
    max-width: 1300px;
    margin: 50px auto;
    padding: 20px;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
    text-align: center;
    margin-bottom: 40px;
    color: #0056b3;
}

.small-container {
    margin: 0 auto;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

th, td {
    padding: 15px;
    border-bottom: 1px solid #ddd;
    text-align: left;
}

th {
    background-color: #0056b3;
    color: #fff;
}

th:nth-child(1), td:nth-child(1) {
    width: 50%;
}

th:nth-child(2), td:nth-child(2) {
    width: 10%;
    text-align: center;
}

th:nth-child(3), td:nth-child(3) {
    width: 20%;
    text-align: right;
}

th:nth-child(4), td:nth-child(4) {
    width: 20%;
    text-align: right;
}

.product-info {
    display: flex;
    align-items: center;
}

.product-info img {
    width: 80px;
    height: 80px;
    margin-right: 15px;
    object-fit: cover;
    border: 1px solid #ddd;
    border-radius: 4px;
}

.product-info p {
    margin: 0;
}

.product-info a {
    color: #ff0000;
    font-size: 12px;
    margin-top: 5px;
    display: inline-block;
}

.btn {
    padding: 10px 20px;
    margin-left: 10px;
    background-color: #0056b3;
    border: none;
    border-radius: 4px;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s;
}

.btn:hover {
    background-color: #004494;
}

.btn-container {
    display: flex;
    justify-content: flex-end;
    margin-top: 20px;
}
</style>
</head>
<body>
    <%
    String contextPath = request.getContextPath();
    %>
    <jsp:include page="header.jsp" />
    <div class="Acontainer">
        <h1>Admin Dashboard</h1>
        <c:choose>
            <c:when test="${not empty productList}">
                <div class="small-container">
                    <table>
                        <thead>
                            <tr>
                                <th>Product</th>
                                <th>Quantity</th>
                                <th>Unit Price</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${productList}" var="product">
                                <tr>
                                    <td>
                                        <div class="product-info">
                                            <img src="<%=contextPath %>/resources/images/products/${product.getImageUrlFromPart()}" alt="${product.getName()}">
                                            <div>
                                                <p>${product.getName()}</p>
                                                <a href="<%= contextPath %>/RemoveProductServlet?id=${product.getId()}">Remove Item</a>
                                            </div>
                                        </div>
                                    </td>
                                    <td>${product.getStock()}</td>
                                    <td>$ ${product.getPrice()}</td>
                                    <td>
                                        <form action="UpdateItemServlet" method="get">
                                            <input type="hidden" value="${product.getId()}" name="productID" />
                                             <button class="btn" type="submit">Update</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="btn-container">
                        <a href = "addProductPage.jsp"> <button class="btn">  Add</button> </a>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <h1>No products found</h1>
            </c:otherwise>
        </c:choose>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>

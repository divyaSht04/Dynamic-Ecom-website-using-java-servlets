<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

<%
	String contextPath = request.getContextPath();
%>
<jsp:include page="header.jsp" />

<section class="trending-products" id="trending">
    <div class="center-text">
        <h2>
            Our Trending <span>Products</span>
        </h2>
        <form action="IndexServlet" method="post">
            <select name="sortOption" id="sort-btn">
                <option value="price">Price</option>
                <option value="rating">Rating</option>
                <option value="stock">Stock</option>
            </select>
            <button type="submit">Sort</button>
        </form>
    </div>
    <c:choose>
        <c:when test="${empty productList}">
            <p>No Items Available</p>
        </c:when>
        <c:otherwise>
            <div class="product-grid">
                <c:forEach var="product" items="${productList}">
                    <div class="product-card">
                        <div class="img-box">
                            <img src="<%=contextPath %>/resources/images/products/${product.getImageUrlFromPart()}" alt="${product.getName()}" />
                        </div>
                        <div class="product-info">
                            <div style="display:flex; justify-content: space-around;">
                                <h4>Name: ${product.getName()}</h4>
                                <h4>Ratings: ${product.getRating()}</h4>
                            </div>
                            <p class="product-description">${product.getDescription()}</p>
                            <div style="display:flex; justify-content: space-around;">
                                <p class="product-price">Price: ${product.getPrice()} $</p>
                                <p class="product-price">Stock: ${product.getStock()}</p>
                            </div>
                            <form action="CartServlet" method="get">
                                <input type="hidden" name="id" value="${product.getId()}" />
                                <c:choose>
                                    <c:when test="${product.getStock() == 0}">
                                        <button class="cart-btn" disabled>Out of Stock</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="cart-btn">Add to Cart</button>
                                    </c:otherwise>
                                </c:choose>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</section>

<!-- Client Reviews Section -->
<section class="client-reviews">
    <div class="review">
        <h3>Client Reviews</h3>
        <img src="Images/timage5.jpg" alt="Client Image" />
        <p>It was easy to spot her. All you needed to do was look at her socks. They were never a matching pair. One would be green while the other would be blue. One would reach her knee while the other barely touched her ankle. Every other part of her was perfect, but never the socks. They were her micro act of rebellion.</p>
        <h4>Ram Bahadur</h4>
        <p>Regular Patron</p>
    </div>
</section>

<!-- Footer -->
<jsp:include page="footer.jsp" />

</body>
</html>

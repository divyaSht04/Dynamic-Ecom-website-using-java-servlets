<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add Product</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
	margin: 0;
	padding: 0;
}

.pcontainer {
	max-width: 600px;
	margin: 50px auto;
	padding: 20px;
	background-color: white;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
}

h1 {
	text-align: center;
	margin-bottom: 20px;
}

form {
	display: flex;
	flex-direction: column;
}

label {
	margin-top: 10px;
	margin-bottom: 5px;
	font-weight: bold;
}

input[type="text"], input[type="number"], input[type="file"], textarea {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

textarea {
	resize: vertical;
}

button {
	padding: 10px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
}

button:hover {
	background-color: #45a049;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="pcontainer">
		<h1>Add a New Product</h1>
		<form action="AddProduct" id="productForm" method="post" enctype="multipart/form-data" >
			<label>Product Name</label> 
			<input type="text" name="productName" required> 
			<label>Ratings</label> 
			<input type="number" name="productRatings" min="1" max="5" required>
			<label for="productStock">Stock</label> 
			<input type="number" name="productStock" required> 
			<label>Price</label> 
			<input type="number" name="productPrice" required>
			<label>Description</label>
			<textarea name="productDescription" required></textarea>
			<label> Image </label> 
			<input type="file" name="productImage" required>
			<button>Add Product</button>
		</form>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>

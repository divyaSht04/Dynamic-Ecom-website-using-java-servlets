<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
        }
        .pcontainer {
            max-width: 500px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-top: 10px;
            font-weight: bold;
            color: #555;
        }
        input {
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 3px;
        }
        button {
            margin-top: 20px;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="pcontainer">
        <h1>Update Item</h1>
        <form action="UpdateItemServlet" method="post">
        	<input type="hidden" name="productId" value="${product.getId()}" />
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${product.getName()}" required>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" step="0.01" min="0" required>

            <label for="stock">Stock:</label>
            <input type="number" id="stock" name="stock" min="0" required>

            <label for="ratings">Ratings:</label>
            <input type="number" id="ratings" name="ratings" step="0.1" min="0" max="5" required>

            <button type="submit">Update Item</button>
        </form>
    </div>
</body>
</html>
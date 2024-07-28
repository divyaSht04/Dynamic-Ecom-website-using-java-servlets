<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
style>body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}

.ucontainer {
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 2rem;
	width: 100%;
	max-width: 500px;
}

h1 {
	text-align: center;
	color: #333;
	margin-bottom: 1.5rem;
}

.form-section {
	margin-bottom: 1.5rem;
}

.form-section h2 {
	font-size: 1.2rem;
	color: #555;
	margin-bottom: 0.5rem;
}

.form-group {
	margin-bottom: 1rem;
}

label {
	display: block;
	margin-bottom: 0.3rem;
	color: #666;
}

input[type="text"], input[type="date"] {
	width: 100%;
	padding: 0.5rem;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 1rem;
}

.image-upload {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.image-preview {
	width: 150px;
	height: 150px;
	border-radius: 50%;
	object-fit: cover;
	margin-bottom: 1rem;
	border: 2px solid #ccc;
}

.file-input {
	display: none;
}

.file-label {
	background-color: #4CAF50;
	color: white;
	padding: 0.5rem 1rem;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.file-label:hover {
	background-color: #45a049;
}

button {
	display: block;
	width: 100%;
	padding: 0.75rem;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 4px;
	font-size: 1rem;
	cursor: pointer;
	transition: background-color 0.3s;
}

button:hover {
	background-color: #45a049;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="ucontainer">
		<h1>Update Your Profile</h1>
		<form id="profile-form" action="UserProfileUpdateServlet" method="post" enctype="multipart/form-data">
			<section class="form-section">
				<h2>Personal Information</h2>
				<div class="form-group">
					<label for="firstName">First Name</label> <input type="text"
						id="firstName" name="firstName" required>
				</div>
				<div class="form-group">
					<label for="lastName">Last Name</label> <input type="text"
						id="lastName" name="lastName" required>
				</div>
				<div class="form-group">
					<label for="dob">Date of Birth</label> <input type="date" id="dob"
						name="dob" required>
				</div>
			</section>

			<section class="form-section">
				<h2>Address</h2>
				<div class="form-group">
					<label for="address">Address</label> <input type="text"
						id="address" name="address" required>
				</div>
			</section>

			<section class="form-section">
				<h2>Profile Picture</h2>
				<div class="image-upload">
					<input type="file" name="image" required>
				</div>
			</section>

			<button type="submit">Update Profile</button>
		</form>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>About Us - TechLaptops</title>
<style>
body {
	font-family: Arial, sans-serif;
	line-height: 1.6;
	margin: 0;
	padding: 0;
	color: #333;
}

main {
	max-width: 1200px;
	margin: 0 auto;
	padding: 2rem;
}

h1, h2 {
	color: #2c3e50;
}

.store-image {
	max-width: 100%;
	height: auto;
	margin: 2rem 0;
	border-radius: 8px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.team-members {
	display: flex;
	justify-content: space-around;
	flex-wrap: wrap;
	margin-top: 2rem;
}

.member {
	text-align: center;
	margin-bottom: 2rem;
}

.member img {
	width: 200px;
	height: 200px;
	border-radius: 50%;
	object-fit: cover;
	margin-bottom: 1rem;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

@media ( max-width : 768px) {
	.team-members {
		flex-direction: column;
		align-items: center;
	}
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>
		<section id="about">
			<h1>About TechLaptops</h1>
			<p>TechLaptops is your one-stop shop for all your laptop needs.
				Founded in 2010, we've been providing top-quality laptops and
				exceptional customer service for over a decade. Our mission is to
				help you find the perfect laptop that meets your needs and budget.</p>
			<img src="store-front.jpg" alt="TechLaptops Store Front"
				class="store-image">
		</section>

		<section id="team">
			<h2>Our Team</h2>
			<div class="team-members">
				<div class="member">
					<img src="john-doe.jpg" alt="John Doe">
					<h3>John Doe</h3>
					<p>Founder & CEO</p>
				</div>
				<div class="member">
					<img src="jane-smith.jpg" alt="Jane Smith">
					<h3>Jane Smith</h3>
					<p>Head of Sales</p>
				</div>
				<div class="member">
					<img src="mike-johnson.jpg" alt="Mike Johnson">
					<h3>Mike Johnson</h3>
					<p>Technical Expert</p>
				</div>
			</div>
		</section>
	</main>

	<jsp:include page="footer.jsp" />
</body>
</html>

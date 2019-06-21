<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<html>
<head>

	<title>Welcome to Jay Company Home Page</title>
	
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</head>
<body>
	<h2>Jay Company Home Page</h2>
	<hr>
	
	<p>
	Welcome to Jay Company Systems Admin Meetings Page
	</p>
	
	<hr>
	
	<p>
		See ya in Portugal for our annual systems admin retreat!!!
		<br>
		Keep this a secret from regular and management employees
	
	</p>
	
	<hr>
		<a href="${pageContext.request.contextPath}/employees" >Back to Home Page</a>
	
	
</body>
</html>
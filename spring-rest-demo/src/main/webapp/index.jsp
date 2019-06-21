<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
	<title>luv2code Company Home Page</title>
	<!-- Reference Bootstrap files -->
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
		  
<!-- 	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
	
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
</head>

<body>
	
	<h2>RESTFUL Home Page</h2>
	<hr>
	
	<a href="${pageContext.request.contextPath}/api/students">Student List</a>
	
	<p>
	<a href="${pageContext.request.contextPath}/api/customers">All Customers</a>
	
	<hr>
	<p>
	
	
	
</body>

</html>
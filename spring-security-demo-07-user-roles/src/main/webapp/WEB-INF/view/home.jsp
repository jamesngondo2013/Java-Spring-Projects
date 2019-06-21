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
	Welcome to Jay Company Home Page
	</p>
	
	<hr>
	
	<!-- display user name and role logged in -->
	<p>
		User: <security:authentication property="principal.username"/>
		<br><br>
		Role(s): <security:authentication property="principal.authorities"/>	
	</p>
	
	
	<!-- Add a link to point to /leaders..this is for the Managers -->
	
	<security:authorize access="hasRole('MANAGER')" >
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Check Leadership Meeting</a>
			
		</p>
		
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')" >
		<p>
			
			<a href="${pageContext.request.contextPath}/systems">Check Sys Admins Meeting</a>
					
		</p>
	</security:authorize>
	
	<hr>
	
	<div>
		<form:form action="${pageContext.request.contextPath}/logout" method="POST" class="form-horizontal">
		
			<!-- Check for login error -->
	
			<c:if test="${param.logout != null}">

				<!-- <div class="alert alert-danger col-xs-offset-1 col-xs-10">
					You have been logged out.
				</div> -->
								
			</c:if>
		
			<!-- Logout Button -->
						<div style="margin-top: 10px" class="form-group">						
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-success">Logout</button>
							</div>
						</div> 
		</form:form>
	</div>
	
	
</body>
</html>
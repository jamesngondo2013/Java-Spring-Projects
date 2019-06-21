<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<head>
	<title>Save Customer</title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
	
	
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />
</head>

<body>
	<!-- start div wrapper -->
	<div id="wrapper">
	
		<!-- start div wrapper -->
		<div id="header">
		
			<h2>CRM - Customer Relationship Manager</h2>
			
		</div>
		
	</div> <!-- end div wrapper -->
	
	<!-- start div container -->
	<div id="container">
	
		<h3>Save Customer</h3>
		
			<form:form action="saveCustomer" modelAttribute="customer" method="POST">
			
				<!-- associate this data with a given customer id -->
				<!-- hidden form helps us to track the customer by id when doing updates  -->
				<!-- knowing who this particular data being updated belongs to in db and update only that cus -->
				<form:hidden path="id" />
				
				<table>
					<tbody>
						<tr>
							<td><label>First Name:</label></td>
							<td><form:input path="firstName"/></td>
						</tr>
						<tr>
							<td><label>Last Name:</label></td>
							<td><form:input path="lastName"/></td>
						</tr>
						<tr>
							<td><label>Email:</label></td>
							<td><form:input path="email"/></td>
						</tr>
						
						<tr>
							<td><label></label></td>
							<td><input type="submit" value="Save" class="save"/></td>
						</tr>
						
					</tbody>
				</table>
			</form:form>
		
		<!-- start div -->
		<div style="clear; both">
			
			<p>
				<a href="${pageContext.request.contextPath}/customer/list">Back to All Customers</a>
			</p>
			
		</div>  <!-- end div -->
	
	</div> <!-- end div container -->

</body>

</html>


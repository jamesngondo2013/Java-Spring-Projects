<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>

	<title>Student Registration Form</title>
	<style>
		.error {color:red}
	</style>
	
</head>

<body>
	<i>Fill out the form. Asterisk (*) is required.</i>
	<br><br>
	<form:form action="processForm" modelAttribute="student">
	
		First Name (*): 
		<form:input path="firstName" placeholder="Enter your first name"/>
		<form:errors path="firstName" cssClass="error"/>
		<br><br>
		
		Last Name (*): 
		<form:input path="lastName" placeholder="Enter your last name"/>
		<form:errors path="lastName" cssClass="error"/>
		<br><br>
		
		Free Passes(*): 
		<form:input path="freePasses" placeholder="Enter your freePasses"/>
		<form:errors path="freePasses" cssClass="error"/>
		<br><br>
		
		Postal Code(*): 
		<form:input path="postalCode" placeholder="Enter your postalCode"/>
		<form:errors path="postalCode" cssClass="error"/>
		<br><br>
		
		Country   (*):      
		<form:select path="country">
				
			<form:options items="${student.countryOptions}"/>
					
		</form:select>		  
		<br><br>
		
		Course Code (*): 
		<form:input path="courseCode" placeholder="Enter your course code"/>
		<form:errors path="courseCode" cssClass="error"/>
		<br><br>
		
		
		Favorite Language: 
		<form:radiobuttons path="favoriteLanguage" items="${student.favoriteLanguageOptions}"  />		  
		<br><br>
		
		Operating Systems: 
		Linux <form:checkbox path="operatingSystems" value="Linux"  />
		Win OS <form:checkbox path="operatingSystems" value="Win OS"  />
		Mac OS <form:checkbox path="operatingSystems" value="Mac OS"  />
				  
		<br><br>
		
		
		<input type="submit" value="Submit"/>
		
	</form:form>
		
</body>

</html>
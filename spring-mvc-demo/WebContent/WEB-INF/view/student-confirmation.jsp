<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

	<body>
	
		<h2>Student Details Confirmation</h2>
		
		<br><br>
		The student is confirmed:
		<br><br>
		First Name: ${student.firstName}
					${fname}  <!-- model name from controller -->
		<br><br>
		
		Last Name: ${student.lastName}
				${lname}
		<br><br>
		
		Free Passes: ${student.freePasses}  
		<br><br>
		
		Postal Code: ${student.postalCode}
						${postCode}
		<br><br>
		
		Country: ${student.country}
		<br><br>
		
		Course Code: ${student.courseCode}
		<br><br>
		
		Programming Language: ${student.favoriteLanguage}
		<br><br>
		
		Operating Systems:
		<ul>
			<c:forEach var="temp" items="${student.operatingSystems}">
			
				<li>${temp}</li>
			
			</c:forEach>
		</ul>
		
		<br><br>
		
		
	</body>

</html>
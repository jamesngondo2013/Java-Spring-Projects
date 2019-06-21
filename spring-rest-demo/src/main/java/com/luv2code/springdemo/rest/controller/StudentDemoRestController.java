package com.luv2code.springdemo.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.entity.Student;
import com.luv2code.springdemo.exceptionhandler.StudentErrorResponse;
import com.luv2code.springdemo.exceptionhandler.StudentNotFoundException;

@RestController
@RequestMapping("/api")
public class StudentDemoRestController {
	
	private List<Student> theStudents;
	
	//define @PostConstruct to load the student dada...only once!
	@PostConstruct
	public void loadData() {
		
		theStudents = new ArrayList<Student>();
		theStudents.add(new Student(1, "James", "Ngondo", true));
		theStudents.add(new Student(2, "Gilby", "Gombe", true));
		theStudents.add(new Student(3, "Baxter", "Saiti", false));
	}

	//add code for the "/students" end point
	@GetMapping("/students")
	public List<Student> getStudents() {
		
		return theStudents;
	}
	
	@GetMapping("/student/{studentId}")
	public Student getCustomer(@PathVariable int studentId) {
		
		//check the student against the list size
		if (studentId >= theStudents.size() || studentId < 0 ) {
			throw new StudentNotFoundException("Student ID "+ studentId +" not found");
		}
		
		// get customer from the service based on id
		Student theStudent = theStudents.get(studentId);
				
		return theStudent;
	}
	
	@GetMapping("/delete/{studentId}")
	public void deleteStudent(@PathVariable("studentId") int studentId) {
		
		// delete the customer
		theStudents.remove(studentId);
		
		//check if customer has been deleted
		
	}
}

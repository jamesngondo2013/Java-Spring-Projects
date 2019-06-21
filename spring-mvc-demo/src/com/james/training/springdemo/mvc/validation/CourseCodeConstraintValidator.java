package com.james.training.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String>{

	private String coursePrefix;
	
	@Override
	public void initialize(CourseCode theCourseCode) {
		coursePrefix = theCourseCode.value();
	}
	
	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {
		// user enters theCode from HTML form
		
		boolean result;
		//check if the string input data is not null
		if(theCode != null) {
			 result = theCode.startsWith(coursePrefix);
		}
		else {
			result = true;
		}
		//returns true if user entered data starts with 'LUV'
		
		return result;
	}

}

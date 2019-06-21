package com.james.training.springdemo.mvc;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	//add an initbinder to convert and trim input strings
	//remove leading and trailing whitespaces
	//resolve issues for our validation
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		
	}
	
	// need a controller method to show initial HTML form
	@RequestMapping("/showForm")
	public String showForm(Model theModel) {

		//create a student object
		Student theStudent = new Student();
		
		//add student object to the model
		theModel.addAttribute("student", theStudent);

		return "student-form";
	}

	// need a controller method to process the HTML form
	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("student") Student theStudent, BindingResult theBindingResult, Model model ) {
		// read the request parameter from the HTML form - bind variable using @ModelAttribute annotation
		//it is OPTIONAL to add Model model

		if (theBindingResult.hasErrors()) {
			return "student-form";
		}
		else {
			
			//convert the data to upperCase
			String fname = theStudent.getFirstName().toUpperCase();
			String lname = theStudent.getLastName().toUpperCase();
			String country = theStudent.getCountry().toUpperCase();
			String postCode = theStudent.getPostalCode().toUpperCase();
			String ProgLanguage = theStudent.getFavoriteLanguage().toUpperCase();
			
			model.addAttribute("fname",fname);
			model.addAttribute("lname",lname);
			model.addAttribute("country",country);
			model.addAttribute("postCode",postCode);
			model.addAttribute("ProgLanguage",ProgLanguage);
			
			
			// log input data
			System.out.println("theStudent FirstName: " + theStudent.getFirstName());
			System.out.println("theStudent LastName: " + theStudent.getLastName());
			System.out.println("theStudent Country: " + theStudent.getCountry());
			System.out.println("theStudent PostalCode: " + theStudent.getPostalCode());
			System.out.println("theStudent FreePasses: " + theStudent.getFreePasses());
			System.out.println("theStudent CourseCode: " + theStudent.getCourseCode());
			System.out.println("theStudent Prog Language: " + theStudent.getFavoriteLanguage());
			System.out.print("theStudent Operating Systems: ");
			String[] os = theStudent.getOperatingSystems();
			for(int i=0; i<os.length;i++) {
				System.out.print(os[i]+", ");
			}

			return "student-confirmation";
		}

	}

}

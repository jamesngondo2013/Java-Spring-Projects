package com.james.training.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String showPage() {
		//this will handle all types http request - POST/ GET for the slash
		//this returns the name of the page - /WEN-INF/view/main-menu.jsp
		return "main-menu";
	}

}

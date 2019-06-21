package com.luv2code.springdemo.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.entity.Student;
import com.luv2code.springdemo.exceptionhandler.CustomerErrorResponse;
import com.luv2code.springdemo.exceptionhandler.CustomerNotFoundException;
import com.luv2code.springdemo.exceptionhandler.StudentErrorResponse;
import com.luv2code.springdemo.exceptionhandler.StudentNotFoundException;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerDemoRestController {
	
	private List<Integer> theCustomerIds;

	// need to inject our customer service
	@Autowired
	private CustomerService customerService;
	
	@PostConstruct
	public void loadData() {
		
		theCustomerIds = new ArrayList<Integer>();
		
		List<Customer> theCustomers = customerService.getCustomers();
		
		for (Customer customer : theCustomers) {
			theCustomerIds.add(customer.getId());
		}
		
	}
	
	//add mapping for GET /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		
		// get all customers from the service
		List<Customer> theCustomers = customerService.getCustomers();
				
		return theCustomers;
	}
	
	//add mapping for GET /customer/{customerId}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		Customer theCustomer = customerService.getCustomer(customerId);

		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer ID " + customerId + " not found");
		}

		/*
		 * for (int i = 0; i < theCustomerIds.size(); i++) {
		 * 
		 * if (!(theCustomerIds.contains(customerId))) { throw new
		 * CustomerNotFoundException("Customer ID "+ customerId +" not found"); }
		 * 
		 * }
		 */
				
		return customerService.getCustomer(customerId);
	}
	
	//add mapping for POST /customer - add new customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
	
		//currentSession in customerDAO will perform an INSERT of the new customer when the id is null or set to zero - otherwise it will update the existing customer.
		theCustomer.setId(0);
		
		// save the customer using our service
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;	
			
	}
	
	//add mapping for PUT /customers - update existing customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		//perform an upadte based on hibernate coding
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
		
	}
	
	//add mapping for DELETE /customer - delete customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer theCustomer = customerService.getCustomer(customerId);
		//check if customer exists
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer ID " + customerId + " not found");
		}
		
		// delete the customer
		customerService.deleteCustomer(customerId);
		
		return "Deleted customer ID: " + customerId;
		
		
	}
}











package com.james.training.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.james.training.springdemo.dao.CustomerDAO;
import com.james.training.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	//need to inject customer dao - dependency injection
	@Autowired
	private CustomerDAO customerDAO;
	
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		return customerDAO.getCustomers();
	}


	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		
		customerDAO.saveCustomer(theCustomer);
	}


	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		return customerDAO.getCustomer(theId);
	}


	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		
		customerDAO.deleteCustomer(theId);
	}


	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) {
		
		return customerDAO.searchCustomers(theSearchName);
	}

}

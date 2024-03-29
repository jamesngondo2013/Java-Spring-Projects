package com.james.training.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.james.training.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	//@Repository applies to DAO implementations - does sql exception translations
	//need to inject the session factory so that the DAO can talk to the the BD
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query - sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", 
																				Customer.class); 
		
		//execute query and get result list
		List<Customer> customer = theQuery.getResultList();
		
		//return the results
		return customer;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save the customer - this will determine whether the transaction is UPDATE
		//or INSERT by figuring out if its new or existing customer
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// get customer from db based on id
		Customer theCustomer = currentSession.get(Customer.class, theId); 
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// get customer from db based on id
		//Customer theCustomer = currentSession.get(Customer.class, theId); 
		
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId"); 
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
				
	}

	  @Override
	    public List<Customer> searchCustomers(String theSearchName) {

	        // get the current hibernate session
	        Session currentSession = sessionFactory.getCurrentSession();
	        
	        Query theQuery = null;
	        
	        //
	        // only search by name if theSearchName is not empty
	        //
	        if (theSearchName != null && theSearchName.trim().length() > 0) {

	            // search for firstName or lastName ... case insensitive
	            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
	            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

	        }
	        else {
	            // theSearchName is empty ... so just get all customers
	            theQuery =currentSession.createQuery("from Customer", Customer.class);            
	        }
	        
	        // execute query and get result list
	        List<Customer> customers = theQuery.getResultList();
	                
	        // return the results        
	        return customers;
	        
	    }

}

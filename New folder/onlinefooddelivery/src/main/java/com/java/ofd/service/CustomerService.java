package com.java.ofd.service;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.java.ofd.entity.Customer;

import com.java.ofd.repo.CustomerRepository;
@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return  customerRepository.save(customer);
	}
	
	public String updateCustomer(int id) {
		// TODO Auto-generated method stub
		 Optional<Customer> custContainer =  customerRepository.findById(id);
		 if(custContainer.isPresent()) {

			 Customer oldObj = custContainer.get();

			 customerRepository.delete(oldObj);

			 return "Deleted Successfully!!!";

			 }else {

			 return "The specified id is not present in the DB :"+id;

			}

			
		
	}

		  
	

	public boolean deleteAllCustomer(Customer customer) {
		// TODO Auto-generated method stub
		try {

			customerRepository.deleteAll();

			 }catch(Exception e) {
		return false;
			 }
		return true;
	}

	public String deleteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer updateCustomer(int id, Customer obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Customer> getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	

}

package com.java.ofd.service;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ofd.entity.Orderdetails;

import com.java.ofd.repo.OrderdetailsRepository;
@Service
public class OrderdetailsService {
	@Autowired
	OrderdetailsRepository orderdetailsRepository;

	public Orderdetails createOrderDetails(Orderdetails details) {
		// TODO Auto-generated method stub
		return orderdetailsRepository.save( details);
	}

	public List<Orderdetails> getOrderDetails() {
		// TODO Auto-generated method stub
		return orderdetailsRepository.findAll();
	}

	public Orderdetails getOrderDetailsById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteAllOrderDetails() {
		// TODO Auto-generated method stub
		try {

			 orderdetailsRepository.deleteAll();

			 }catch(Exception e) {
		return false;
			 }
		return true;
	}

	public String deleteById(int id) {
		// TODO Auto-generated method stub
		 Optional< Orderdetails> orderContainer =   orderdetailsRepository.findById(id);
		 if(orderContainer.isPresent()) {

			 Orderdetails oldObj = orderContainer.get();

			 orderdetailsRepository.delete(oldObj);

			 return "Deleted Successfully!!!";

			 }else {

			 return "The specified id is not present in the DB :"+id;

			}
	}

	public Orderdetails updateOrderDetails(int id, Orderdetails obj) {
		// TODO Auto-generated method stub
		 Optional<Orderdetails > empContainer=orderdetailsRepository.findById(id);

		   if(empContainer.isPresent()) {

		   return empContainer.get();

		   }else {

		   return null; 

		  }

		 

	}
	
	

}

package com.java.ofd.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.java.ofd.entity.Bill;
import com.java.ofd.repo.BillRepository;
@Service 
public class BillService {
	 @Autowired
	 BillRepository  billRepository;

	public Bill createBill(Bill bill) {
		// TODO Auto-generated method stub
		return billRepository.save(bill);
	}

	public List<Bill> getBill() {
		// TODO Auto-generated method stub
		return billRepository.findAll();
	}

	public Bill getBillById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteAllBill() {
		// TODO Auto-generated method stub
		try {

			billRepository.deleteAll();

			 }catch(Exception e) {
		return false;
			 }
		return true;
	}
	

	public String deleteById(int id) {
		// TODO Auto-generated method stub
		Optional<Bill> billContainer =  billRepository.findById(id);
		 if(billContainer.isPresent()) {

			 Bill oldObj = billContainer.get();

			 billRepository.delete(oldObj);

			 return "Deleted Successfully!!!";

			 }else {

			 return "The specified id is not present in the DB :"+id;

			}

			
		
	}




	public Bill updateBill(int id, Bill obj) {
		// TODO Auto-generated method stub
		 Optional<Bill> billContainer=billRepository.findById(id);

		   if(billContainer.isPresent()) {

		   return billContainer.get();

		   }else {

		   return null; 

		  }
	}
	}
		 

	

	
	




	
package com.java.ofd.service;




import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.java.ofd.entity.Restaruant;
import com.java.ofd.repo.RestaruantRepository;
@Service
public class RestarurantService {
	@Autowired

	RestaruantRepository restarurantRepository;



	public Restaruant createRestaurant(Restaruant rest) {
		// TODO Auto-generated method stub
		return restarurantRepository.save(rest);

	}

	public List<Restaruant> getRestaurant() {
		// TODO Auto-generated method stub
		return  restarurantRepository.findAll();
	}

	public Restaruant getRestaurantById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteAllRestaurants() {
		// TODO Auto-generated method stub
		try {

			restarurantRepository.deleteAll();

			 }catch(Exception e) {
		return false;
			 }
		return true;
	}
	

	public String deleteById(int id) {
		// TODO Auto-generated method stub
		 Optional<Restaruant> resContainer = restarurantRepository.findById(id);
		 if(resContainer.isPresent()) {

			 Restaruant oldObj = resContainer.get();

			 restarurantRepository.delete(oldObj);

			 return "Deleted Successfully!!!";

			 }else {

			 return "The specified id is not present in the DB :"+id;

			}
	}

	public Restaruant updateRestaurant(int id, Restaruant obj) {
		// TODO Auto-generated method stub
		 Optional<Restaruant> empContainer=restarurantRepository.findById(id);

		   if(empContainer.isPresent()) {

		   return empContainer.get();

		   }else {

		   return null; 

		  }
	}


}

package com.java.ofd.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.java.ofd.entity.Foodcart;

import com.java.ofd.repo.FoodcartRepository;
@Service
public class FoodcartService {
	@Autowired
	FoodcartRepository foodcartRepository;

	public Foodcart createFoodcart(Foodcart food) {
		// TODO Auto-generated method stub
		return foodcartRepository.save(food);
	}

	public List<Foodcart> getFoodcart() {
		// TODO Auto-generated method stub
		return foodcartRepository.findAll();
	}

	public Foodcart getFoodcartById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteAllFoodcart() {
		// TODO Auto-generated method stub
		try {

			foodcartRepository.deleteAll();

			 }catch(Exception e) {
		return false;
			 }
		return true;
	}

	public String deleteById(int id) {
		// TODO Auto-generated method stub
		Optional<Foodcart> foodcartContainer =  foodcartRepository.findById(id);
		 if(foodcartContainer.isPresent()) {

			 Foodcart oldObj = foodcartContainer.get();

			 foodcartRepository.delete(oldObj);

			 return "Deleted Successfully!!!";

			 }else {

			 return "The specified id is not present in the DB :"+id;

			}
	}

	public Foodcart updateFoodcart(int id, Foodcart obj) {
		// TODO Auto-generated method stub
		Optional<Foodcart> empContainer=foodcartRepository.findById(id);

		   if(empContainer.isPresent()) {

		   return empContainer.get();

		   }else {

		   return null; 
	}
	

		


		 

		
		
	}



	


	}

	
	



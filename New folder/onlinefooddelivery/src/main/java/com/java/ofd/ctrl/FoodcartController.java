package com.java.ofd.ctrl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.ofd.entity.Foodcart;


import com.java.ofd.service.FoodcartService;

@RestController
public class FoodcartController {
	@Autowired
	FoodcartService foodcartservice;
	
	@PostMapping("/foodCart")
	public Foodcart createFoodCart(@RequestBody Foodcart food) {

		 return foodcartservice.createFoodcart(food);

		 

		}

	     @GetMapping("/foodCart")

		 public List<Foodcart> getFoodcart()
		 {

		 return foodcartservice.getFoodcart();
		 }
		 @GetMapping("/foodCart/{id}")

		 public Foodcart getFoodCartById(@PathVariable int id) {

		 return foodcartservice.getFoodcartById(id);

		}

		 @DeleteMapping("/foodCart")

		 public boolean deleteAllFoodCart() {

		 return foodcartservice.deleteAllFoodcart();

		}

		 @DeleteMapping("/foodCart/{id}")

		 public String deleteById(@PathVariable int id) {

		 return foodcartservice.deleteById(id);

		 

		}

		 //http://localhost:8090/employees/4

		 @PutMapping("/foodCart/{id}")

		 public Foodcart updateFoodCart(@PathVariable int id,@RequestBody Foodcart obj) {

		 return foodcartservice.updateFoodcart(id,obj);

		 }

		 

		 

		 



		

		 

		 



		}



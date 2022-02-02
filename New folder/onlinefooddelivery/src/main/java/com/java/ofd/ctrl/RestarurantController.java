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


import com.java.ofd.entity.Restaruant;

import com.java.ofd.service.RestarurantService;

@RestController
public class RestarurantController {
	 @Autowired

	 RestarurantService restarurantservice;

	 @PostMapping("/restaurant")
	 public Restaruant createRestaurant(@RequestBody Restaruant rest) {

		 return restarurantservice.createRestaurant(rest);

		 

		}

		 @GetMapping("/restaurant")

		 public List<Restaruant> getRestaurant()
		 {

		 return restarurantservice.getRestaurant();
		 }
		 @GetMapping("/restaurant/{id}")

		 public Restaruant getRestaurantById(@PathVariable int id) {

		 return restarurantservice.getRestaurantById(id);

		}

		 @DeleteMapping("/restaurant")

		 public boolean deleteAllRestaurant() {

		 return restarurantservice.deleteAllRestaurants();

		}

		 @DeleteMapping("/restaurant/{id}")

		 public String deleteById(@PathVariable int id) {

		 return restarurantservice.deleteById(id);

		 

		}

		 //http://localhost:8090/employees/4

		 @PutMapping("/restaurant/{id}")

		 public Restaruant updateRestaurant(@PathVariable int id,@RequestBody Restaruant obj) {

		 return restarurantservice.updateRestaurant(id,obj);

		 }

		 

		 

		 



		}
	
	 


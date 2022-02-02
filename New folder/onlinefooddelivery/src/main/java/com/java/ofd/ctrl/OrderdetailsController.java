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



import com.java.ofd.entity.Orderdetails;


import com.java.ofd.service.OrderdetailsService;

@RestController
public class OrderdetailsController {
	@Autowired

	OrderdetailsService orderdetailsService;

	@PostMapping("/order")
	 public Orderdetails createOrderDetails(@RequestBody Orderdetails details) {

		 return orderdetailsService.createOrderDetails(details);

		 

		}

		 @GetMapping("/order")

		 public List<Orderdetails> getOrderDetails()
		 {

		 return orderdetailsService.getOrderDetails();
		 }
		 @GetMapping("/order/{id}")

		 public Orderdetails getOrderDetailsById(@PathVariable int id) {

		 return orderdetailsService.getOrderDetailsById(id);

		}

		 @DeleteMapping("/order")

		 public boolean deleteAllOrderDetails() {

		 return orderdetailsService.deleteAllOrderDetails();

		}

		 @DeleteMapping("/order/{id}")

		 public String deleteById(@PathVariable int id) {

		 return orderdetailsService.deleteById(id);

		 

		}

		 //http://localhost:8090/employees/4

		 @PutMapping("/order{id}")

		 public Orderdetails updateOrderDetails(@PathVariable int id,@RequestBody Orderdetails obj) {

		 return orderdetailsService.updateOrderDetails(id,obj);

		 }

		 

		 

		 



		}


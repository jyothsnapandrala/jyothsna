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


import com.java.ofd.entity.Customer;


import com.java.ofd.service.CustomerService;
@RestController
public class CustomerController {
	@Autowired

	CustomerService customerService;

	 @PostMapping("/Customer")

	 public Customer addCustomer(@RequestBody Customer customer) {
	 return customerService.addCustomer(customer);

	 }
	 @GetMapping("/Customer")

	 public List<Customer> getCustomer()
	 {

	 return customerService.getCustomer();
	 }
	 @PutMapping("/Customer")

	 public String updateCustomer(@RequestBody int customer) {

	 return customerService.updateCustomer(customer);

	 }
	@DeleteMapping("/Customer")

	 public boolean deleteCustomer(@RequestBody Customer customer) {

	 return  customerService.deleteAllCustomer(customer);

	}
	
	

	 @DeleteMapping("/customer/{id}")

	 public String deleteById(@PathVariable int id) {

	 return customerService.deleteById(id);

	 

	}

	 //http://localhost:8090/employees/4

	 @PutMapping("/customer/{id}")

	 public Customer updateCustomer(@PathVariable int id,@RequestBody Customer obj) {

	 return customerService.updateCustomer(id,obj);

	 }

	 

	 

	 



	}
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

import com.java.ofd.entity.Item;
import com.java.ofd.service.ItemService;

@RestController
public class ItemController {
	

	 @Autowired
	 ItemService  itemService;
	@PostMapping("/item")
	 public Item createItem(@RequestBody Item item) {

		 return itemService.createItem(item);

		 

		}

		 @GetMapping("/item")

		 public List<Item> getItem()
		 {

		 return itemService.getItems();
		 }
		 @GetMapping("/item/{id}")

		 public Item getItemById(@PathVariable int id) {

		 return itemService.getItemById(id);

		}

		 @DeleteMapping("/item")

		 public boolean deleteAllItem() {

		 return itemService.deleteAllItems();

		}

		 @DeleteMapping("/item/{id}")

		 public String deleteById(@PathVariable int id) {

		 return itemService.deleteById(id);

		 

		}

		 //http://localhost:8090/employees/4

		 @PutMapping("/item/{id}")

		 public Item updateItem(@PathVariable int id,@RequestBody Item obj) {

		 return itemService.updateItem(id,obj);

		 }

		 

		 

		 



		}



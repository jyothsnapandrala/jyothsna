package com.java.ofd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.java.ofd.entity.Item;
import com.java.ofd.repo.ItemRepository;
@Service
public class ItemService {
	@Autowired
	ItemRepository itemRepository ;


	public Item createItem(Item item) {
		// TODO Auto-generated method stub
		return itemRepository.save(item);
	}

	public List<Item> getItems() {
		// TODO Auto-generated method stub
		return itemRepository.findAll();
	}

	public Item getItemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteAllItems() {
		// TODO Auto-generated method stub
		try {

			 itemRepository.deleteAll();

			 }catch(Exception e) {
		return false;
			 }
		return true;
	}

	public String deleteById(int id) {
		// TODO Auto-generated method stub
		 Optional<Item> itemContainer =  itemRepository.findById(id);
		 if(itemContainer.isPresent()) {

			 Item oldObj = itemContainer.get();

			 itemRepository.delete(oldObj);

			 return "Deleted Successfully!!!";

			 }else {

			 return "The specified id is not present in the DB :"+id;

			}

	}

	public Item updateItem(int id, Item obj) {
		// TODO Auto-generated method stub
		Optional<Item> itemContainer=itemRepository.findById(id);

		   if(itemContainer.isPresent()) {

		   return itemContainer.get();

		   }else {

		   return null; 

		  }

	}

}

package com.java.ofd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.java.ofd.entity.Category;
import com.java.ofd.repo.CategoryRepository;
@Service
public class CategoryService {
	 @Autowired
	 CategoryRepository  categoryRepository ;
	public Category createCategory(Category cate) {
		// TODO Auto-generated method stub
		return  categoryRepository .save(cate);
	}

	public List<Category> getCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	public Category getCategoryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteAllCategory() {
		// TODO Auto-generated method stub
		try {

			categoryRepository.deleteAll();

			 }catch(Exception e) {
		return false;
			 }
		return true;
	}

	public String deleteById(int id) {
		// TODO Auto-generated method stub
		Optional<Category> categoryContainer =  categoryRepository.findById(id);
		 if(categoryContainer.isPresent()) {

			 Category oldObj = categoryContainer.get();

			 categoryRepository.delete(oldObj);

			 return "Deleted Successfully!!!";

			 }else {

			 return "The specified id is not present in the DB :"+id;

			}
	}

	public Category updateCategory(int id, Category obj) {
		// TODO Auto-generated method stub
		 Optional<Category> categoryContainer=categoryRepository.findById(id);

		   if(categoryContainer.isPresent()) {

		   return categoryContainer.get();

		   }else {

		   return null; 

		  }
	}

	
	

}

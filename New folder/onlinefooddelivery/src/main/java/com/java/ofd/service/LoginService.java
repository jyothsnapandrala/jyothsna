package com.java.ofd.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.java.ofd.entity.Login;
import com.java.ofd.repo.LoginRepository;
@Service
public class LoginService {
	@Autowired
	LoginRepository loginrepository;

	public Login createLogin(Login log) {
		// TODO Auto-generated method stub
		return loginrepository.save(log);
	}

	public List<Login> getLogin() {
		// TODO Auto-generated method stub
		return loginrepository.findAll();
	}

	public Login getLoginById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteAllLogin() {
		// TODO Auto-generated method stub
		try {

			 loginrepository.deleteAll();

			 }catch(Exception e) {
		return false;
			 }
		return true;
	}
	

	public String deleteById(int id) {
		// TODO Auto-generated method stub
		 Optional<Login> loginContainer =  loginrepository.findById(id);
		 if(loginContainer.isPresent()) {

			 Login oldObj = loginContainer.get();

			 loginrepository.delete(oldObj);

			 return "Deleted Successfully!!!";

			 }else {

			 return "The specified id is not present in the DB :"+id;

			}
	}

	public Login updateLogin(int id, Login obj) {
		// TODO Auto-generated method stub
		Optional<Login> loginContainer=loginrepository.findById(id);

		   if(loginContainer.isPresent()) {

		   return loginContainer.get();

		   }else {

		   return null; 

		  }

		 

		

	}

	
}

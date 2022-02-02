package com.java.ofd.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.java.ofd.entity.Bill;

public interface BillRepository  extends JpaRepository<Bill,Integer>{

	
	

}

package com.java.ofd.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.ofd.entity.Customer;



public interface CustomerRepository extends JpaRepository <Customer,Integer> {

}

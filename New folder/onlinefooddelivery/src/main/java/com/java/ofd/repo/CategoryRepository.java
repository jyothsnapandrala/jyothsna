package com.java.ofd.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.ofd.entity.Category;



public interface CategoryRepository  extends JpaRepository <Category,Integer> {

}

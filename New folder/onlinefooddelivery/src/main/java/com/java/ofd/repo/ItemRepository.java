package com.java.ofd.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.java.ofd.entity.Item;

public interface ItemRepository extends JpaRepository <Item,Integer>{

}
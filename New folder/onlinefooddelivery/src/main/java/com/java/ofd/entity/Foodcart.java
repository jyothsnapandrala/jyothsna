




package com.java.ofd.entity;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="Foodcart_Tb")
public class Foodcart {
	@Id
	@Column(name="Cartid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cartid;
	@OneToOne

	@JoinColumn(name="Cust_Id")
	private Customer customer;
	@Column(name="FoodCart_ItemList")
	@OneToMany
	private List<Item> itemList;
	

	public Foodcart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Foodcart(int cartid, Customer customer, List<Item> itemList) {
		super();
		this.cartid = cartid;
		this.customer = customer;
		this.itemList = itemList;
	}
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	@Override
	public String toString() {
		return "FoodCart [cartid=" + cartid + ", customer=" + customer + ", itemList=" + itemList + "]";
	}
	
	}
	


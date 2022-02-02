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
@Table(name="Restaruant_Tb")
public class Restaruant {
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	private String restaurantid;
	@Column(name="Restaurant_Name")
	private String restaurantname;
	@OneToOne

	@JoinColumn(name="Adress_Id")
	private Address address;
	 @OneToMany
	private List<Item> itemList;
	@Column(name=" Manager_Name")
	private String managername;
	@Column(name="contactnumber")
	 
	private String contactnumber;
	public Restaruant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Restaruant(String restaurantid, String restaurantname, Address address, List<Item> itemList,
			String managername, String contactnumber) {
		super();
		this.restaurantid = restaurantid;
		this.restaurantname = restaurantname;
		this.address = address;
		this.itemList = itemList;
		this.managername = managername;
		this.contactnumber = contactnumber;
	}
	public String getRestaurantid() {
		return restaurantid;
	}
	public void setRestaurantid(String restaurantid) {
		this.restaurantid = restaurantid;
	}
	public String getRestaurantname() {
		return restaurantname;
	}
	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	@Override
	public String toString() {
		return "Restaruant [restaurantid=" + restaurantid + ", restaurantname=" + restaurantname + ", address="
				+ address + ", itemList=" + itemList + ", managername=" + managername + ", contactnumber="
				+ contactnumber + "]";
	}
	
}

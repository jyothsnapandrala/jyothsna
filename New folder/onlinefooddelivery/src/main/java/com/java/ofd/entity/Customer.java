package com.java.ofd.entity;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Customer_Tb")
public class Customer {
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	private int customerid;
	 @Column(name="First_Name")
	private String firstname;
	 @Column(name="Last_Name")
	private String lastname;
	private int age;
	 @Column(name="Mobilenumber")
	private String mobilenumber;
	 @OneToOne

		@JoinColumn(name="address_Id")
	private Address address;
	 @Column(name="Email")
	private String email;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int customerid, String firstname, String lastname, int age, String mobilenumber, Address address,
			String email) {
		super();
		this.customerid = customerid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.mobilenumber = mobilenumber;
		this.address = address;
		this.email = email;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", firstname=" + firstname + ", lastname=" + lastname + ", age="
				+ age + ", mobilenumber=" + mobilenumber + ", address=" + address + ", email=" + email + "]";
	}
	
	
	
}

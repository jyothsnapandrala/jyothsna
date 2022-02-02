package com.java.ofd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int addressid;
	 @Column(name="Build_Name")
	private String buildname;
	 @Column(name="StreetNo")
	private String streetNo;
	 @Column(name="State")
	private String state;
	 @Column(name="Country")
	private String country;
	 @Column(name="Pincode")
	private String pincode;
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(int addressid, String buildname, String streetNo, String state, String country, String pincode) {
		super();
		this.addressid = addressid;
		this.buildname = buildname;
		this.streetNo = streetNo;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}
	public int getAddressid() {
		return addressid;
	}
	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}
	public String getBuildname() {
		return buildname;
	}
	public void setBuildname(String buildname) {
		this.buildname = buildname;
	}
	public String getStreetNo() {
		return streetNo;
	}
	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "Address [addressid=" + addressid + ", buildname=" + buildname + ", streetNo=" + streetNo + ", state="
				+ state + ", country=" + country + ", pincode=" + pincode + "]";
	}
	

}

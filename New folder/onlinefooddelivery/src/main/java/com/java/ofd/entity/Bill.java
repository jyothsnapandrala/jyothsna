package com.java.ofd.entity;

import javax.persistence.Column;


import javax.persistence.Entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="Bill_Tb")
public class Bill {
	
	@Id
	@Column(name=" bill_Id")

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  int billId;
	@Temporal(value=TemporalType.DATE)
	private  Date purchaseDate;
	@OneToOne

	@JoinColumn(name="order_Id")
	private  Orderdetails Order;
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bill(int billId,  Date purchaseDate, Orderdetails Order) {
		super();
		this.billId = billId;
		this.purchaseDate  = purchaseDate ;
		this.Order = Order;
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public Date getPurchaseDat() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		  this.purchaseDate = purchaseDate;
	}
	public Orderdetails getOrder() {
		return Order;
	}
	public void setOrder(Orderdetails Order) {
		this.Order = Order;
	}
	@Override
	public String toString() {
		return "Bill [billId=" + billId + ",purchaseDate=" + purchaseDate + ", Order=" + Order + "]";
	}
	
}
	
	

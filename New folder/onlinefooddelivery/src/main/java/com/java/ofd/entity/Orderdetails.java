package com.java.ofd.entity;

import java.util.Date;

import javax.persistence.Column;



import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

@Table(name="orderdetails_Tb")
public class Orderdetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderId;
	@Temporal(value=TemporalType.DATE)
	private Date orderDate;
	@OneToOne

	@JoinColumn(name="cart_Id")
	private Foodcart cart ;
	 @Column(name="OrderStatus")
	private String orderStatus;
	public Orderdetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Orderdetails(int orderId, Date orderDate, Foodcart cart, String orderStatus) {
		super();
		this.orderId = orderId;
		this.orderDate =  orderDate;
		this.cart = cart;
		this.orderStatus = orderStatus;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getorderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate= orderDate;
	}
	public Foodcart getCart() {
		return cart;
	}
	public void setCart(Foodcart cart) {
		this.cart = cart;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "Orderdetails [orderId=" + orderId + ",  orderDate=" +  orderDate + ", cart=" + cart
				+ ", orderStatus=" + orderStatus + "]";
	}
	

}

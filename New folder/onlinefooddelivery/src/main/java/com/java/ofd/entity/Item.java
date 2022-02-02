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
@Table(name="Item_Tb")
public class Item {
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int itemid;
	  @Column(name="Item_Name")
	  
	  
	private String itemname;
	  @OneToOne

		@JoinColumn(name="order_Id")
	private Category catogory;
	  @Column(name="Quantity")
	private int quantity;
	  @Column(name="Cost")
	private double cost;
	  @OneToMany
	private List<Restaruant> restaruant ;
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(int itemid, String itemname, Category catogory, int quantity, double cost,
			List<Restaruant> restaruant) {
		super();
		this.itemid = itemid;
		this.itemname = itemname;
		this.catogory = catogory;
		this.quantity = quantity;
		this.cost = cost;
		this.restaruant = restaruant;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public Category getCatogory() {
		return catogory;
	}
	public void setCatogory(Category catogory) {
		this.catogory = catogory;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public List<Restaruant> getRestaruant() {
		return restaruant;
	}
	public void setRestaruant(List<Restaruant> restaruant) {
		this.restaruant = restaruant;
	}
	@Override
	public String toString() {
		return "Item [itemid=" + itemid + ", itemname=" + itemname + ", catogory=" + catogory + ", quantity=" + quantity
				+ ", cost=" + cost + ", restaruant=" + restaruant + "]";
	}
	
	

}

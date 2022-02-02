package com.java.ofd.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Category_Tb")
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cateid;
	 @Column(name="Catogory_Name")
	private String catogoryName;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category( int cateid, String catogoryName) {
		super();
		this.cateid = cateid;
		this.catogoryName = catogoryName;
	}
	public int getCateid() {
		return cateid;
	}
	public void setCatlid(int cateid) {
		this.cateid = cateid;
	}
	public String getCatogoryName() {
		return catogoryName;
	}
	public void setCatogoryName(String catogoryName) {
		this.catogoryName = catogoryName;
	}
	@Override
	public String toString() {
		return "Category [catlid=" + cateid + ", catogoryName=" + catogoryName + "]";
	}
	
	

}

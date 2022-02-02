package com.java.ofd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Login {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int userid;
	@Column(name="Username")
private String username;
	@Column(name="Password")
private String password;
public Login() {
	super();
	// TODO Auto-generated constructor stub
}
public Login(int userid, String username, String password) {
	super();
	this.userid = userid;
	this.username = username;
	this.password = password;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "LoginModule [userid=" + userid + ", username=" + username + ", password=" + password + "]";
}


}

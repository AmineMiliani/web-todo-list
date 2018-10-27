package com.nathan_amine.web.jdbc;

public class Account {
	public Account(String user, String password, String role) {
		super();
		this.user = user;
		this.password = password;
		this.role = role;
	}
	private String user;
	private String password;
	private String role;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Account [user=" + user + ", password=" + password + ", role=" + role + "]";
	}

}

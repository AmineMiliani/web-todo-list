package com.nathan_amine.web.jdbc;

public class Todo {


	public Todo(String description) {
		super();
		this.description = description;
	}

	public Todo(int id2, String description) {
		this.id =id2;
		this.description =description;
		// TODO Auto-generated constructor stub
	}

	private int id;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String descritption) {
		this.description = descritption;
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + "]";
	}
}

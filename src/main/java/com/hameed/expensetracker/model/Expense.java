package com.hameed.expensetracker.model;

public class Expense {
	private int id;
	private String title;
	private int amount;
//	private String currency;
	private Category category;
	private SubCategory subCategory;
	private User ownerUser;

	public Expense(String title, int amount, Category category, SubCategory subCategory, User ownerUser) {
		super();
		this.title = title;
		this.amount = amount;
		this.category = category;
		this.subCategory = subCategory;
		this.ownerUser = ownerUser;
	}
	public Expense(int id, String title, int amount, Category category, SubCategory subCategory, User ownerUser) {
		super();
		this.id = id;
		this.title = title;
		this.amount = amount;
		this.category = category;
		this.subCategory = subCategory;
		this.ownerUser = ownerUser;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	public User getOwnerUser() {
		return ownerUser;
	}
	public void setOwnerUser(User ownerUser) {
		this.ownerUser = ownerUser;
	}
	
	
}

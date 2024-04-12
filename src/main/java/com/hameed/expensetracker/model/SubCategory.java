package com.hameed.expensetracker.model;

public class SubCategory {
	private int id;
	private String subCategoryName;
	private Category category;
	public SubCategory(int id, String subCategoryName, Category category) {
		super();
		this.id = id;
		this.subCategoryName = subCategoryName;
		this.category = category;
	}
	public SubCategory(String subCategoryName, Category category) {
		super();
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}

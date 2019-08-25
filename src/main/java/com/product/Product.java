package com.product;

import java.util.Base64;

import com.category.Category;

public class Product extends Category{
	private String productName;
	private String productDescription;
	private String productColor;
	private byte[] productView;
	private String img;
	
	//first constructor
	public Product(String categoryName, String categoryDescription, String productName, String productDescription,
			String productColor) {
		super(categoryName, categoryDescription);
		this.productName = productName;
		this.productDescription = productDescription;
		this.productColor = productColor;
		this.productView = null;
	}

	//constructor with images inclusive
	public Product(String categoryName, String categoryDescription, String productName, String productDescription,
			String productColor, byte[] productVew) {
		super(categoryName, categoryDescription);
		this.productName = productName;
		this.productDescription = productDescription;
		this.productColor = productColor;
		this.productView = productVew;
		this.img = Base64.getEncoder().encodeToString(productVew);
	}

	//getters
	public String getProductName() {
		return productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getProductColor() {
		return productColor;
	}

	//setters
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public byte[] getProductView() {
		return productView;
	}
	public String getImg() {
		return img;
	}

	public void setProductView(byte[] productView) {
		this.productView = productView;
	}
	
	
}

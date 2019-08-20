package product;

import category.Category;

public class Product extends Category{
	private String productName;
	private String productDescription;
	private String productColor;
	
	//constructor
	public Product(String categoryName, String categoryDescription, String productName, String productDescription,
			String productColor) {
		super(categoryName, categoryDescription);
		this.productName = productName;
		this.productDescription = productDescription;
		this.productColor = productColor;
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
	
	
}
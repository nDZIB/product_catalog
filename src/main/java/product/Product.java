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
}

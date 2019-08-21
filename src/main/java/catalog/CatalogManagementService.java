package catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import product.Product;

public class CatalogManagementService {
	List<Product> products = new ArrayList<Product>();// list to hold all available products

	// get all the products
	public List<Product> getProducts(Connection databaseConnection) {

		try {
			PreparedStatement dbManipulate = databaseConnection
					.prepareStatement("SELECT " + "productName, productDescription, productColor, categoryName, "
							+ "categoryDescription FROM product INNER JOIN category WHERE "
							+ "product.categoryID = category.categoryID");

			// after getting the list of products, put them in request scope and forward to
			// view

			ResultSet setOfProducts = dbManipulate.executeQuery();

			// obtain list of products and add to a list
			while (setOfProducts.next()) {
				String productName = setOfProducts.getString(1);
				String productDescription = setOfProducts.getString(2);
				String productColor = setOfProducts.getString(3);
				String categoryName = setOfProducts.getString(4);
				String categoryDescription = setOfProducts.getString(5);
				// instantiate a product object
				Product product = new Product(categoryName, categoryDescription, productName, productDescription,
						productColor);

				// add to list
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();// if getting the products fails print error to console
		}
		return products;
	}
}

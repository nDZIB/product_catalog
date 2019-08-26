package com.product;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.category.Category;
import com.category.CategoryManagementService;

@WebServlet(urlPatterns = "/modify-product.pcat")
@MultipartConfig(maxFileSize = 16177215)
public class ModifyProduct extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {

		String productName = requestVariable.getParameter("productName");
		String productDescription = requestVariable.getParameter("productDescription");
		String productColor = requestVariable.getParameter("productColor");
		String categoryName = requestVariable.getParameter("categoryName");
		String categoryDescription = requestVariable.getParameter("categoryDescription");
		// String pict = requestVariable.getParameter("productView");

		Product product = new Product(categoryName, categoryDescription, productName, productDescription, productColor);

		// set the current product as session variable, this is to enable it to be
		// accessible even after
		// this request

		requestVariable.getSession().setAttribute("product", product);
		// redirect to the page to modify products
		requestVariable.getRequestDispatcher("/WEB-INF/views/modify-product.jsp").forward(requestVariable,
				responseVariable);
	}

	@Override
	public void doPost(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {
		ProductManagementService productMService = new ProductManagementService();
		CategoryManagementService categoryMService = new CategoryManagementService();

		Connection dbconnection = (Connection) requestVariable.getSession().getAttribute("dbconnection");
		// redirect to the page to modify products
		if (requestVariable.getParameter("editProduct") != null) {// code to execute if user wishes to edit product
			this.editProduct(requestVariable, dbconnection, categoryMService, productMService);

		} else if (requestVariable.getParameter("deleteProduct") != null) {// product deletion code
			Product product = (Product) requestVariable.getSession().getAttribute("product");

			productMService.removeProduct(dbconnection, product);

		} else if (requestVariable.getParameter("addNewProduct") != null) {// product addition code
			addNewProduct(requestVariable, dbconnection, categoryMService, productMService);
		}
		requestVariable.getSession().removeAttribute("product");
		responseVariable.sendRedirect("/view-exp-catalog.pcat");// after performing relevant logic, return to
																// view-exp-catalog
	}

	// method to add a product as a new one
	public void addNewProduct(HttpServletRequest requestVariable, Connection dbconnection,
			CategoryManagementService categoryMService, ProductManagementService productMService)
			throws IOException, ServletException {// product addition code

		int foundID;
		String newProductName = requestVariable.getParameter("newProductName");
		String newProductDescription = requestVariable.getParameter("newProductDescription");
		String newProductColor = requestVariable.getParameter("newProductColor");
		String categoryName = requestVariable.getParameter("newCategoryName");
		String categoryDescription = requestVariable.getParameter("newCategoryDescription");

		// get the image if exists
		Part part = requestVariable.getPart("productView");
		InputStream productView = part.getInputStream();

		Category category = new Category(categoryName, categoryDescription);
		Product product = new Product(categoryName, categoryDescription, newProductName, newProductDescription,
				newProductColor);
		// System.out.println(product.getProductDescription());

		foundID = categoryMService.getCategoryID(dbconnection, category);

		if (foundID != 0) {// if the requested product category exists
			if (part != null) {
				productView = part.getInputStream();
				if (productView != null) // if image exists, add the new product
					productMService.addProduct(dbconnection, product, foundID, productView);
				else // add new product without image
					productMService.addProduct(dbconnection, product, foundID);
			} // assumption here then is that all valid files are images
//			else {// if the no image was provided, add the new product without an image
//				productMService.addProduct(dbconnection, product, foundID);
//			}

		} else {// otherwise if the category does not exist, add it and proceed
			if (part != null)
				if ((productView = part.getInputStream()) != null)// if there is a picture supplied
					this.addProductAndCategory(categoryMService, product, productMService, category, dbconnection,
							productView);
				else
					this.addProductAndCategory(categoryMService, product, productMService, category, dbconnection);
		}
	}

	// method to edit an existing product

	public void editProduct(HttpServletRequest requestVariable, Connection dbconnection,
			CategoryManagementService categoryMService, ProductManagementService productMService)
			throws IOException, ServletException {

		// inorder to edit product,
		// 1 get a corresponding category id
		// make modifications
		// get the parameters from the page
		int productID = 0;// this will hold the id of the old project
		int categoryID = 0;// will hold the categoryid
		int newcategoryID = 0;// this will hold the id of the new project
		String newProductName = requestVariable.getParameter("newProductName");
		String newProductDescription = requestVariable.getParameter("newProductDescription");
		String newProductColor = requestVariable.getParameter("newProductColor");
		String categoryName = requestVariable.getParameter("newCategoryName");
		String categoryDescription = requestVariable.getParameter("newCategoryDescription");

		;

		Product product = new Product(categoryName, categoryDescription, newProductName, newProductDescription,
				newProductColor);

		// get relevent categoryID
		Product oldProduct = (Product) requestVariable.getSession().getAttribute("product");

		Part part = requestVariable.getPart("productView");
		InputStream productView = null;
		categoryID = productMService.getCategoryID(dbconnection, oldProduct);// get categoryID of old product
		productID = productMService.getProductID(dbconnection, oldProduct);// get productID of old product

		if (productID != 0) {// the provided old product exists
			if (categoryID != 0) {// and the provided category exists
				Category category = new Category(product.getCategoryName(), product.getCategoryDescription());

				newcategoryID = categoryMService.getCategoryID(dbconnection, category);// get the categoryid of the
																						// new product
				if (newcategoryID != 0) {// if the new product has a category
					if (part.getSize() == 0) {// if the user selects no file
						productMService.editProduct(dbconnection, product, newcategoryID, productID);
						System.out.println("No image inserted");
					} else {
						productView = part.getInputStream();
						if (productView != null) {// if there is an image, edit the product, considering the image
							productMService.editProduct(dbconnection, product, newcategoryID, productID, productView);
						} else {
							productMService.editProduct(dbconnection, product, newcategoryID, productID);
							System.out.println("No image inserted");
						}
					}

				} else {// otherwise if the category does not exist, add it and proceed
					if (part != null) {// if the user selects a file
						productView = part.getInputStream();
						if (productView != null) {// and the file is a valid image file, add the new product with its
													// image
							this.addProductAndCategory(categoryMService, oldProduct, productMService, category,
									dbconnection, productView);
						}
					} else
						this.addProductAndCategory(categoryMService, oldProduct, productMService, category,
								dbconnection);
				}
			} else {// if the provided old product's category does not exist/ this most always exist
					// though
				System.out.println("Your category does not exist");
				// redirect the user to modify-product.jsp
			}
		} else {// if the provided old product does not exist/ it must always exist, because the
				// user does not pass it
			System.out.println("Your product does not exist(can't update a non-existent product");
			// redirect the user to modify-product.jsp
		}
	}

	// method to add a category
	public void addProductAndCategory(CategoryManagementService categoryMService, Product product,
			ProductManagementService productMService, Category category, Connection dbconnection) {
		int newCategoryID = categoryMService.addCategory(dbconnection, category);
		if (newCategoryID != 0) {// if the category was successfully added
			productMService.addProduct(dbconnection, product, newCategoryID);// add the product
		} else {// if the new product was not added
			System.out.println("Could not add the product");
			// redirect to modify-product.jsp
		}
	}

	// method to add a category with an image provided
	public void addProductAndCategory(CategoryManagementService categoryMService, Product product,
			ProductManagementService productMService, Category category, Connection dbconnection,
			InputStream productView) {
		int newCategoryID = categoryMService.addCategory(dbconnection, category);
		if (newCategoryID != 0) {// if the category was successfully added
			productMService.addProduct(dbconnection, product, newCategoryID, productView);// add the product
		} else {// if the new product was not added
			System.out.println("Could not add the product");
			// redirect to modify-product.jsp
		}
	}
}

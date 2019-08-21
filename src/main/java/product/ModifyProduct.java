package product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.Category;
import category.CategoryManagementService;

@WebServlet(urlPatterns = "/modify-product.pcat")
public class ModifyProduct  extends HttpServlet{
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
		
		Product product = new Product(categoryName, categoryDescription, productName, productDescription, productColor);
		
		//set the current product as session variable, this is to enable it to be accessible even after
				//this request
		
		
		
		requestVariable.getSession().setAttribute("product", product);
		//redirect to the page to  modify products
		requestVariable.getRequestDispatcher("/WEB-INF/views/modify-product.jsp").forward(requestVariable,responseVariable);
	}
	@Override
	public void doPost(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
		throws ServletException, IOException {
		ProductManagementService productMService = new ProductManagementService();
		CategoryManagementService categoryMService = new CategoryManagementService();
		
		Connection dbconnection = (Connection)requestVariable.getSession().getAttribute("dbconnection");
		int id;//will hold the categoryID of any newly created category
		//redirect to the page to  modify products
		if(requestVariable.getParameter("editProduct")!=null) {//code to execute if user wishes to edit product
			//inorder to edit product, 
				//1 get a corresponding category id
				//make modifications
			//get the parameters from the page
			int productID = 0;// this will hold the id of the old project
			int categoryID = 0;//will hold the categoryid
			int newcategoryID = 0;// this will hold the id of the new project
			String newProductName = requestVariable.getParameter("newProductName");
			String newProductDescription = requestVariable.getParameter("newProductDescription");
			String newProductColor = requestVariable.getParameter("newProductColor");
			String categoryName = requestVariable.getParameter("newCategoryName");
			String categoryDescription = requestVariable.getParameter("newCategoryDescription");
			
			Product product = new Product(categoryName, categoryDescription, newProductName, newProductDescription, newProductColor);
			
			//get relevent categoryID
			Product oldProduct = (Product)requestVariable.getSession().getAttribute("product");
			
			
			categoryID = productMService.getCategoryID(dbconnection, oldProduct);//get categoryID of old product
			productID = productMService.getProductID(dbconnection, oldProduct);//get productID of old product
			
			if(productID != 0) {// the provided old product exists
				if(categoryID != 0) {//and the provided category exists
					Category category = new Category(product.getCategoryName(), product.getCategoryDescription());
					
					newcategoryID = categoryMService.getCategoryID(dbconnection, category);//get the categoryid of the new product
					if(newcategoryID != 0) {//if the new product has a category
						productMService.editProduct(dbconnection, product, newcategoryID, productID);
					} else {//if no new category exists, then add it
						System.out.println("No new CategoryExists");
						//redirect user to modify-product
					}
				} else {// if the provided old product's category does not exist
					System.out.println("Your category does not exist");
					//redirect the user to modify-product.jsp
				}
			} else {//if the provided old product does not exist
				System.out.println("Your product does not exist(can't update a non-existent product");
				//redirect the user to modify-product.jsp
			}
		} else if(requestVariable.getParameter("deleteProduct")!= null) {//product deletion code
			Product product = (Product)requestVariable.getSession().getAttribute("product");
			
			productMService.removeProduct(dbconnection, product);
			
		} else if (requestVariable.getParameter("addNewProduct")!=null) {//product addition code
			
			int foundID;
			String newProductName = requestVariable.getParameter("newProductName");
			String newProductDescription = requestVariable.getParameter("newProductDescription");
			String newProductColor = requestVariable.getParameter("newProductColor");
			String categoryName = requestVariable.getParameter("newCategoryName");
			String categoryDescription = requestVariable.getParameter("newCategoryDescription");
			
			Category category = new Category(categoryName, categoryDescription);
			Product product = new Product(categoryName, categoryDescription, newProductName, newProductDescription, newProductColor);
			System.out.println(product.getProductDescription());
			
			
			foundID = categoryMService.getCategoryID(dbconnection, category);
			
			if(foundID != 0) {//if the requested product category exists
				productMService.addProduct(dbconnection, product, foundID);
			} else {//otherwise if the category does not exist, add it and proceed
				int newCategoryID = categoryMService.addCategory(dbconnection, category);
				if(newCategoryID != 0) {//if the category was successfully added
					productMService.addProduct(dbconnection, product, newCategoryID);//add the product
				} else {//if the new product was not added
					System.out.println("Could not add the product");
					//redirect to modify-product.jsp
				}
			} 
	}
		requestVariable.getSession().removeAttribute("product");
		responseVariable.sendRedirect("/view-exp-catalog.pcat");//after performing relevant logic, return to view-exp-catalog
	}
}

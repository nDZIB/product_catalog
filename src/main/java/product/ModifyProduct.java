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
			
			
			try {
				PreparedStatement pst = dbconnection.prepareStatement("SELECT productID,categoryID FROM product WHERE productName = ? AND "
						+ "productDescription = ? AND productColor = ?");//you might want to edit this query to include other fields
				pst.setString(1, oldProduct.getProductName());
				pst.setString(2, oldProduct.getProductDescription());
				pst.setString(3, oldProduct.getProductColor());
				
				
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					productID = rs.getInt(1);
					categoryID = rs.getInt(2);
				}
				
				//now get the category id of the new product, just to be sure you have the right
				//thing supposing the user modified but the category
				try {
					PreparedStatement pst3 = dbconnection.prepareStatement("SELECT categoryID FROM category WHERE categoryName = ? AND "
							+ "categoryDescription = ?");//you might want to edit this query to include other fields
					pst3.setString(1, product.getCategoryName());
					pst3.setString(2, product.getCategoryDescription());;
					
					ResultSet rs3 = pst3.executeQuery();
					while(rs3.next()) {
						newcategoryID = rs3.getInt(1);
					}
				} catch(SQLException ex3) {
					ex3.printStackTrace();
				}
				//now you you can update the product
				try {
					PreparedStatement pst2 = dbconnection.prepareStatement("UPDATE product SET categoryID = ?, productName = ?, productDescription = ?,"
							+ " productColor = ? WHERE productID = ? AND categoryID = ? ");
					pst2.setInt(1, newcategoryID);
					pst2.setString(2, product.getProductName());
					pst2.setString(3, product.getProductDescription());
					pst2.setString(4, product.getProductColor());
					pst2.setInt(5, productID);
					pst2.setInt(6, categoryID);
					
					
					//execute the query
					pst2.executeUpdate();
				} catch(SQLException ex2) {
					ex2.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			
		} else if(requestVariable.getParameter("deleteProduct")!= null) {//product deletion code
			Product product = (Product)requestVariable.getSession().getAttribute("product");
			//prepare a statement
			try {//for better code, you might want to first get the relevant category id before proceeding
				PreparedStatement pst = dbconnection.prepareStatement("DELETE FROM product WHERE productName=? AND productDescription=?");
				pst.setString(1, product.getProductName());
				pst.setString(2, product.getProductDescription());
				pst.executeUpdate();
				 
				System.out.println("Okay, product deleted");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Nothing deleted");
			}
			
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
			//first search the database and retrieve the relevant product id
			try {
				PreparedStatement pst = dbconnection.prepareStatement("SELECT categoryID FROM category WHERE categoryName = ? "
						+ "AND categoryDescription = ? ");
				
				pst.setString(1, category.getCategoryName());
				pst.setString(2, category.getCategoryDescription());
				
				System.out.println(category.getCategoryName());
				System.out.println(category.getCategoryDescription());
				ResultSet rs = pst.executeQuery();//rs has the category id
				if(rs.next()) {
					foundID =rs.getInt(1);
					pst = dbconnection.prepareStatement("INSERT INTO product(categoryID, productName, productDescription, productColor) VALUES(?,?,?,?)");
					
					pst.setInt(1, foundID);
					pst.setString(2, product.getProductName());
					pst.setString(3, product.getProductDescription());
					pst.setString(4, product.getProductColor());
					
					//execute the query
					pst.executeUpdate();
				}
				else {//if there is no such category, add the category
					try {
					PreparedStatement pst2 = dbconnection.prepareStatement("INSERT IGNORE INTO category(categoryName, categoryDescription) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
					pst2.setString(1, category.getCategoryName());
					pst2.setString(2, category.getCategoryDescription());
					
					 
					pst2.executeUpdate();//i now have the id of the new category
					id = 0;
					ResultSet rs2 = pst2.getGeneratedKeys();
					if(rs2.next())
						id = rs2.getInt(1);
					
					//NOW INSERT THE PRODUCT
					pst2 = dbconnection.prepareStatement("INSERT INTO product(categoryID, productName, productDescription, productColor) VALUES(?,?,?,?)");
					
					
					pst2.setInt(1, id);
					pst2.setString(2, product.getProductName());
					pst2.setString(3, product.getProductDescription());
					pst2.setString(4, product.getProductColor());
					
					//execute the query
					pst2.executeUpdate();
					}catch (SQLException td)  {
						td.printStackTrace();
					}
				} 
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		requestVariable.getSession().removeAttribute("product");
		responseVariable.sendRedirect("/view-exp-catalog.pcat");//after performing relevant logic, return to view-exp-catalog
	}
}

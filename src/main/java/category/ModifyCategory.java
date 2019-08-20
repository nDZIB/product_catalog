package category;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/modify-category.pcat")
public class ModifyCategory  extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
		throws ServletException, IOException {
		//first, get the parameters
		String categoryName = requestVariable.getParameter("categoryName");
		String categoryDescription = requestVariable.getParameter("categoryDescription");
		Category category = new Category(categoryName, categoryDescription);
		requestVariable.getSession().setAttribute("category", category);
		requestVariable.getRequestDispatcher("/WEB-INF/views/modify-category.jsp").forward(requestVariable, responseVariable);
	}
	@Override
	public void doPost(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {
		/*Do post handles deletion and modification of a category*/
		Connection dbconnection = (Connection)requestVariable.getSession().getAttribute("dbconnection");
			if(requestVariable.getParameter("deleteCategory")!=null) {//if user wishes to delete category
				Category category = (Category)requestVariable.getSession().getAttribute("category");
				//prepare a statement
				try {
					PreparedStatement pst = dbconnection.prepareStatement("DELETE FROM category WHERE categoryName=? AND categoryDescription=?");
					pst.setString(1, category.getCategoryName());
					pst.setString(2, category.getCategoryDescription());
					pst.execute();
					
					System.out.println("Okay");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Nothing deleted");
				}
			} else if(requestVariable.getParameter("editCategory")!=null) {//if user wishes to edit category
				String newCategoryDescription = requestVariable.getParameter("newCategoryDescription").toString();
				String newCategoryName = requestVariable.getParameter("newCategoryName").toString();
				
				Category newCategory = new Category(newCategoryName, newCategoryDescription);
				Category category = (Category)requestVariable.getSession().getAttribute("category");
				try {
					PreparedStatement pst = dbconnection.prepareStatement("UPDATE category SET categoryName= ?, "
							+ "categoryDescription = ? WHERE categoryName=? AND categoryDescription= ?");
					
					pst.setString(1, newCategory.getCategoryName());
					pst.setString(2, newCategory.getCategoryDescription());
					pst.setString(3, category.getCategoryName());
					pst.setString(4, category.getCategoryDescription());
					
					
					pst.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if(requestVariable.getParameter("addNewCategory")!=null) {
				//execute query to add a category, then return to the view-exp-catalog jsp
				//first get category, then add it
				String newCategoryDescription = requestVariable.getParameter("newCategoryDescription").toString();
				String newCategoryName = requestVariable.getParameter("newCategoryName").toString();
				
				Category newCategory = new Category(newCategoryName, newCategoryDescription);
				
				try {
					PreparedStatement pst = dbconnection.prepareStatement("INSERT INTO category(categoryName, "
							+ "categoryDescription) VALUES(?,?)");
					
					//set the parameters
					pst.setString(1, newCategory.getCategoryName());
					pst.setString(2, newCategory.getCategoryDescription());
					pst.execute();
					
					
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			requestVariable.getSession().removeAttribute("category");
			responseVariable.sendRedirect("/view-exp-catalog.pcat");
		}
}

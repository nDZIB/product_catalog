package category;

import java.io.IOException;
import java.sql.Connection;

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
		
		
		CategoryManagementService manageCategory = new CategoryManagementService();
		
		Connection dbconnection = (Connection)requestVariable.getSession().getAttribute("dbconnection");
			if(requestVariable.getParameter("deleteCategory")!=null) {//if user wishes to delete category
				Category category = (Category)requestVariable.getSession().getAttribute("category");
				//prepare a statement
				
				manageCategory.removeCategory(dbconnection, category);
			} else if(requestVariable.getParameter("editCategory")!=null) {//if user wishes to edit category
				String newCategoryDescription = requestVariable.getParameter("newCategoryDescription").toString();
				String newCategoryName = requestVariable.getParameter("newCategoryName").toString();
				
				Category newCategory = new Category(newCategoryName, newCategoryDescription);
				Category category = (Category)requestVariable.getSession().getAttribute("category");
				
				manageCategory.editCategory(dbconnection, category, newCategory);
//				
			} else if(requestVariable.getParameter("addNewCategory")!=null) {
				//execute query to add a category, then return to the view-exp-catalog jsp
				//first get category, then add it
				String newCategoryDescription = requestVariable.getParameter("newCategoryDescription").toString();
				String newCategoryName = requestVariable.getParameter("newCategoryName").toString();
				
				Category newCategory = new Category(newCategoryName, newCategoryDescription);
				
				manageCategory.addNewCategory(dbconnection, newCategory);
			}
			requestVariable.getSession().removeAttribute("category");
			responseVariable.sendRedirect("/view-exp-catalog.pcat");
		}
}
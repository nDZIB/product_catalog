package filter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import connection.ConnectionManager;

@WebServlet(urlPatterns = "/error-redirect.pcat")
@MultipartConfig(maxFileSize = 16177215) 
public class ErrorRedirect extends HttpServlet {
	private Connection databaseConnection;
	private ConnectionManager dataSource = new ConnectionManager();
    // content=blob, name=varchar(255) UNIQUE.
    private static final String SQL_FIND = "SELECT img_data FROM trn_imgs WHERE img_title = ?";

   public Connection getc() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		//setting the location and access privileges for the database handling storage
		//for the user shopVisitor, password is hopVisito
		String url = "jdbc:mysql://localhost:3306/mypic";
	    String user = "root";
	    String password = "";
	    
		try {
			databaseConnection =(Connection) DriverManager.getConnection(url, user, password);
			System.out.println("Connected to database");
			return databaseConnection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return databaseConnection;
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageName = "design.jpg"; // Returns "foo.png".
        System.out.println(imageName);
        try (Connection connection = this.getc();//dataSource.createConnection();
        		PreparedStatement statement = connection.prepareStatement(SQL_FIND)) {
            statement.setString(1, imageName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    byte[] content = resultSet.getBytes(1);
                    String img = Base64.getEncoder().encodeToString(content);
         
                    request.setAttribute("img", img);
                    
                    //response.setContentType(getServletContext().getMimeType(imageName));
                    //response.setContentLength(content.length);
                    //response.getOutputStream().write(content);
                } else {
                    //response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Something failed at SQL/DB level.", e);
        }
        System.out.println("I am here");
        request.getRequestDispatcher("/WEB-INF/views/im.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException {
    	Connection db = new ConnectionManager().createConnection();
    	
    	try {
			PreparedStatement pst = db.prepareStatement("insert into product(categoryId, productName, productView) values "
					+ "(?, ?, ?)");
			Part part = request.getPart("pic");
			InputStream is = part.getInputStream();
			if(part!= null) {
				pst.setInt(1, 16);
				pst.setString(2, "Shoe");
				pst.setBlob(3, is);
				
				pst.executeUpdate();
			}
			else
				System.out.println("no file selected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

}

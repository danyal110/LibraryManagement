//insert book details
package pro;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insertbookdetails
 */
@WebServlet("/insertbookdetails")
public class insertbookdetails extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		System.out.println("sdf");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","library","library");
			String b=request.getParameter("isbn");
			String subject=request.getParameter("subject");
			String name=request.getParameter("name");
			String author=request.getParameter("author");
			String publisher=request.getParameter("publisher");
			String c=request.getParameter("cost");
			int cost=Integer.parseInt(c);
			int copies=0;
			System.out.println("ddda");
			PreparedStatement ps=con.prepareStatement("insert into book values (?,?,?,?,?,?,?)");
			ps.setString(1, b);
			ps.setString(2, subject);
			ps.setString(3, name);
			ps.setString(4, author);
			ps.setString(5, publisher);
			ps.setInt(6, copies);
			ps.setFloat(7, cost);
			System.out.println("prepared");
			ps.executeUpdate();
			System.out.println("executed");
			/*ps=con.prepareStatement("insert into book2 values (?,?,'No')");
			ps.setInt(1, copies);
			ps.setString(2, b);
			ps.executeUpdate();*/
			System.out.println("Database Updated(Book Inserted)");
			copies=Integer.parseInt(request.getParameter("quantity"));
			response.sendRedirect("/suryapro1/InsertBook?isbn="+b+"&copies="+copies);
		}
		catch(Exception e)
		{
			
		}
	}


}

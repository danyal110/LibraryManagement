//insert student into table student
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
 * Servlet implementation class InsertStudent
 */
@WebServlet("/InsertStudent")
public class InsertStudent extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","library","library");
			PreparedStatement ps=con.prepareStatement("insert into student values (?,?,?,?)");
			String id=request.getParameter("id");
			String s=request.getParameter("name");
			String br=request.getParameter("branch");
			String y=request.getParameter("year");
			int year=Integer.parseInt(y);
			ps.setString(1, id);
			ps.setString(2, s);
			ps.setString(3, br);
			ps.setInt(4, year);
			ps.executeUpdate();
			pw.print("<h1>Updated</h1>");
			System.out.println("Üpdated");
		}
		catch(Exception e){
			System.out.println("Server time out");
			pw.print("<h1>Server time out</h1>");
		}
	}

}

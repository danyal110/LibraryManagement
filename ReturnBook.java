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
 * Servlet implementation class ReturnBook
 */
@WebServlet("/ReturnBook")
public class ReturnBook extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		String id=request.getParameter("studentid");
		String isbn=request.getParameter("isbn");
		int bno=Integer.parseInt(request.getParameter("B_no"));
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","library","library");
			PreparedStatement ps=con.prepareStatement("delete from issue where id='"+id+"' and isbn='"+isbn+"' and B_no="+bno);
			ps.executeUpdate();
			ps=con.prepareStatement("update book2 set issued='YES' where B_no="+bno+" and isbn='"+isbn+"'");
			ps.executeUpdate();
			pw.print("<h1>Book has been returned<h1>");
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}

	
	

}

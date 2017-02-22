package pro;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class booksearch2
 */
@WebServlet("/booksearch2")
public class booksearch2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method 
		PrintWriter pw=response.getWriter();
		String bookname=request.getParameter("bookname");
		String studentid=request.getParameter("studentid");
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","library","library");
			PreparedStatement ps=con.prepareStatement("select isbn,name from book where name like '%"+bookname+"%' ");
			ResultSet rs=ps.executeQuery();
			pw.print("<form action='issue'>"
					+"<input name='studentid' value='"+studentid+"' visibility:hidden >");
			while(rs.next()){
				pw.print("<input type='radio' name='isbn' value='"+rs.getString("isbn")+"'>"+rs.getString("name")+"<br>");
			}
			pw.print("<input type='submit' value='Submit' /></form>");
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
}

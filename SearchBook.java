//Search a book in book table
package pro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class lib
 */
@WebServlet("/SearchBook")
public class SearchBook extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		System.out.println("sdis");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","library","library");
			String subject=request.getParameter("subject");
			System.out.println("-"+subject+"-");
			String isbn=request.getParameter("isbn");
			System.out.println(isbn);
			String bookname=request.getParameter("bookname");
			System.out.println(bookname);
			String author=request.getParameter("author");
			System.out.println(author);
			String publisher=request.getParameter("publisher");
			System.out.println(publisher);
			PreparedStatement ps;
			System.out.println("sdis");
			ps=con.prepareStatement("select * from book where subject like '%"+subject+"%' and author like '%"+author+"%' and publisher like '%"+publisher+"%' and name like '%"+bookname+"%' and isbn like '%"+isbn+"%' ");
			System.out.println("Ps prepared");
			/*ps.setString(1, subject);
			System.out.println("subject completed");
			ps.setString(2, author);
			System.out.println("author completed");
			ps.setString(3, publisher);
			System.out.println("publisher completed");
			ps.setString(4, bookname);
			System.out.println("bookname completed");
			ps.setString(5, isbn);
			System.out.println("isbn completed");*/
			ResultSet rs=ps.executeQuery();
			System.out.println("ps executed");
			pw.print("<table border='1px'> <tr>"
					+ "<th>ISBN No.</th>"
					+ "<th>Name</th>"
					+ "submit"
					+ "</tr>"
					+ "<form action=students>");
			while(rs.next()){
				pw.print("<tr>"
						+ "<th>"+rs.getString("isbn")+"</th>"
						+ "<th>"+rs.getString("name")+"</th>"
						+ "<th><input type=submit value=check /></th>"
						+ "</tr>"); 
			}
			pw.print("</table>");
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
	}

	
}

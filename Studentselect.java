//select a student who have issued a book
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
 * Servlet implementation class Studentselect
 */
@WebServlet("/Studentselect")
public class Studentselect extends HttpServlet {
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","library","library");
			String studentid=request.getParameter("studentid");
			pw.print("<h1>"+studentid+"</h1>");
			PreparedStatement ps=con.prepareStatement("select * from (student natural join issue) where id=?");
			ps.setString(1, studentid);
			ResultSet rs=ps.executeQuery();
			//System.out.println(rs.next());
			pw.print("<table border='1px'>"
					+ "<tr>"
					+ "<th>Book No.</th>"
					+ "<th>ISBN No.</th>"
					//+ "<th>Name</th>"
					//+ "<th>Subject</th>"
					//+ "<th>Author</th>"
					//+ "<th>Publisher</th>"
					+ "<th>Issue Date</th>"
					+ "<th>Return Date</th>"
					+ "<th>Return book</th>"
					//+ "<th>Price</th>"
					+ "</tr>");
			int count=0;
			//pw.print("<h1>"+studentid+"</h1>");
			while(rs.next()){
				//System.out.println("sxs");
				//pw.print(rs.getString("id"));
				String isbn=rs.getString("isbn");
				int bno=rs.getInt("b_no");
				pw.print("<tr>"
						+ "<th>"+bno+"</th>"
						+ "<th>"+isbn+"</th>"
						//+ "<th>"+rs.getString("book.name")+"</th>"
						//+ "<th>"+rs.getString("subject")+"</th>"
						//+ "<th>"+rs.getString("author")+"</th>"
						//+ "<th>"+rs.getString("publisher")+"</th>"
						+ "<th>"+rs.getString("issue_date")+"</th>"
						+ "<th>"+rs.getString("return_date")+"</th>"
						//+ "<th>"+rs.getFloat("cost")+"</th>"
						+ "<th><button onclick=location.href='/suryapro1/ReturnBook?studentid="+studentid+"&isbn="+isbn+"&B_no="+bno+"' >Return</button></th>"
						+"</tr>");
						
				count++;
			}
			pw.print("</table>");
			if(count>=2){
				pw.print("<p>Warning! Issue limit reached <br></p>");
			}
			pw.print("<button onclick=location.href='http://localhost:8888/suryapro1/inputbookname?studentid="+studentid+"'>Issue</button>");
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}

}

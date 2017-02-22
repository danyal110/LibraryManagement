//insert or update books table
package pro;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insertbook
 */
@WebServlet("/InsertBook")
public class InsertBook extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		System.out.println("running");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","library","library");
			String b=request.getParameter("isbn");
			int copies=Integer.parseInt(request.getParameter("copies"));
			PreparedStatement ps=con.prepareStatement("select isbn,copies from book where isbn='"+b+"'");
			ResultSet rs=ps.executeQuery();
			if(!rs.next())
			{
				rs.close();
				//pw.print("You are a fuck");
				pw.print("<form action='insertbookdetails'>"
						+ "isbn: <input type=text name=isbn value="+b+" />" 
						+ "name: <input type=text name='name' />"
						+ "subject: <input type=text name='subject' />"
						+ "author: <input type=text name='author' />"
						+ "publisher: <input type=text name='publisher' />"
						+ "cost: <input type=number name='cost' />"
						+ "copies:<input type=numeric name='quantity' value="+copies+" />"
						+ "<input type=submit value=Submit />"
						+ "</form>");
			}
			/*else
			{
				int a=rs.getInt("copies")+1;
				ps=con.prepareStatement("update book set copies =? where isbn= ?");
				ps.setInt(1, a);
				ps.setString(2, b);
				ps.executeUpdate();
				ps=con.prepareStatement("insert into book2 values ( "+a+" , "+b+" , 'No' )");
				ps.executeUpdate();
				pw.print("<h1>Succesfull</h1>");
				
			}*/
			rs=ps.executeQuery();
			rs.next();
			int a=rs.getInt("copies")+1;
			rs.close();
			//int x=1;
			while((copies--)!=0){
				System.out.println(a);
				ps=con.prepareStatement("update book set copies =? where isbn= ?");
				ps.setInt(1, a);
				ps.setString(2, b);
				ps.executeUpdate();
				ps=con.prepareStatement("insert into book2 values ( "+a+" , "+b+" , 'NO' )");
				ps.executeUpdate();
				//pw.print("<h1>Succesfull</h1>");
				a++;
			}
			pw.print("<h1>Succesfull</h1>");
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}

}

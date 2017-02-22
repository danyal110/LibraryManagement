//issue a book and update issue table
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
import java.util.Date;
/**
 * Servlet implementation class issue
 */
@WebServlet("/issue")
public class issue extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		Date date=new Date();
		ResultSet rs;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","library","library");
			String studentid=request.getParameter("studentid");
			String isb=request.getParameter("isbn");
			PreparedStatement ps=con.prepareStatement("select b_no from book2 where isbn='"+isb+"'and issued='NO'");
			System.out.println("prepared ok");
			rs=ps.executeQuery();
			System.out.println("execute ok");
			if(!rs.next())
			{
				
				System.out.println("no avalable books");
			}
			else
			{
				String bookn=rs.getString(1);
				//System.out.println("hsvh");
				ps=con.prepareStatement("update book2 set issued='YES' where isbn='"+isb+"'and b_no="+bookn);
				ps.executeUpdate();
				//System.out.println("ddd");
				ps=con.prepareStatement("insert into issue(ID,B_no,ISBN,issue_date) values('"+studentid+"','"+bookn+"','"+isb+"','"+date.toString()+"')");
				ps.executeUpdate();
				//System.out.println("vchd");
				pw.print("<h1>Book has been Issued</h1>");
			}
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
}

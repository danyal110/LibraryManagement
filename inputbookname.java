package pro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class inputbookname
 */
@WebServlet("/inputbookname")
public class inputbookname extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentid=request.getParameter("studentid");
		PrintWriter pw=response.getWriter();
		pw.print("<form action='/suryapro1/booksearch2'>"
				+ "BookName<input type='text' name=bookname />"
				+ "<input value='"+studentid+"' name='studentid' />"
				+ "<input type='submit' value='Submit'>"
				+ "</form>");
	}

}

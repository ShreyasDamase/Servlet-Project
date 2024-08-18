package com.app.servlet;

import static com.app.servlet.DBConnection.getConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/editBook")
public class EditBookServler extends HttpServlet {
 private static String query=" update book set BookName=?, BookEdition=?,BookPrice=? where BookId=?";
 @Override
 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  //get print writer
 	PrintWriter pw=resp.getWriter();
 //set content type
 	resp.setContentType("text/html");
 	String bookid=req.getParameter("bookId");
 	 
 	pw.println("<form method='post' action'/Book_Registration/editBook'>");
 	pw.println("<input type='hidden' name='bookId' value='"+bookid+"'>");

 	pw.println("<table>");
 	pw.println("<tr>");
 	pw.println("<td> Book Name</td>");
 	pw.println("<td><input type='text' name='bookName'></td>");
 	
 	pw.println("</tr>");
 	pw.println("<tr>");
 	pw.println("<td>Book Edition</td>");
 	pw.println("<td><input type='text' name='bookEdition'></td>");
 	
  	pw.println("</tr>");
 	pw.println("<tr>");
 	pw.println("<td>Book Price</td>");
 	pw.println("<td><input type='text' name='bookPrice'></td>");
 	 	pw.println("</tr>");
 	
 	pw.println("<tr>");
 	pw.println("<td><input type='submit' value='update'></td>");
 	pw.println("</tr>");
 	
 	
 	pw.println("</table>");
 	pw.println("</form>");
 	
 //getting connection 
 	 
 	
 }

 @Override
 	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	 PrintWriter pw=resp.getWriter();
	 //set content type
	 	resp.setContentType("text/html");
	 int bookId=Integer.parseInt(req.getParameter("bookId")); 		 
String bookName=req.getParameter("bookName"); 		 
String bookEdition=req.getParameter("bookEdition"); 		 
String bookPrice=req.getParameter("bookPrice"); 	

try(Connection cn=getConnection();
		PreparedStatement ps= cn.prepareStatement(query))
{
	ps.setString(1,bookName);
	ps.setString(2,bookEdition);
	ps.setFloat(3,(Float.parseFloat(bookPrice)));
	ps.setInt(4,bookId);
	
    int rowsAffected = ps.executeUpdate();
    if (rowsAffected > 0) {
        pw.println("<h3>Book updated successfully!</h3>");
    } else {
        pw.println("<h3>Book update failed!</h3>");
    }
	pw.println("<a href='booklist'>Book List</a>");

    pw.println("</body></html>");


    
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

 	}


}

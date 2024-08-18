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
@WebServlet("/deleteBook")
public class deleteBook extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	// TODO Auto-generated method stub
	
	String query="delete from book where Bookid=?";
	int Bookid=Integer.parseInt(req.getParameter("bookId"));
	resp.setContentType("text/html");
	PrintWriter pw=resp.getWriter();
	
	try(Connection cn=getConnection();
			PreparedStatement ps=cn.prepareStatement(query)){ 
		ps.setInt(1, Bookid);	
			int count= ps.executeUpdate();
			if(count ==1) {
				
				pw.println("<h2>Record is delete Successfully!!</h2>");
			}else 	 pw.println("<h2>Record delete Failed!!</h2>");
			
			pw.println("<a href='booklist'>Book List</a>");

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }


 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	 doGet(req, resp);
}
}

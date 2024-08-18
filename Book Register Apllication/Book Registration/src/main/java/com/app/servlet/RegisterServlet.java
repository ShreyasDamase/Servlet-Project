package com.app.servlet;

import  static com.app.servlet.DBConnection.getConnection;

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
 
public class RegisterServlet extends HttpServlet {
	
	private static final String query="INSERT INTO book (BookName, BookEdition, BookPrice) VALUES (?,?,?)"; 
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 //get print writer
	PrintWriter pw=resp.getWriter();
//set content type
	resp.setContentType("text/html");
	String BookName=req.getParameter("bookName");
	String BookEdition=req.getParameter("bookEdition");
	float BookPrice=Float.parseFloat(req.getParameter("bookPrice"));
	
//getting connection 
	Connection cn=getConnection();
	try {
		PreparedStatement ps= cn.prepareStatement(query);
		ps.setString(1, BookName);
		ps.setString(2, BookEdition);
		ps.setFloat(3, BookPrice);
		
		int count= ps.executeUpdate();
		if(count ==1) {
			
			pw.println("<h2>Record is Resgisted Successfully!!</h2>");
		}else 	 pw.println("<h2>Record Resgistion Failed!!</h2>");

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	pw.println("<a href='booklist'>Book List</a>");
	
	
}

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}

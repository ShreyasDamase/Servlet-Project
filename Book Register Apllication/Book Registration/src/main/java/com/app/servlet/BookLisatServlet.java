package com.app.servlet;

import static com.app.servlet.DBConnection.getConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/booklist")
public class BookLisatServlet extends HttpServlet {
	public static final String query = "select  BookId ,BookName, BookEdition, BookPrice from book";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		Connection cn = getConnection();
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ResultSet rs = ps.executeQuery();

			pw.println("<!DOCTYPE html>");
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<title>Book List</title>");
			pw.println(
					"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
			
			pw.println("<script>");
			pw.println("function redirectTo(url){");
			pw.println(" window.location.href=url;");
			pw.println("}");
			pw.println("</script>");

			pw.println("</head>");
			pw.println("<body>");
			pw.println("<div class='container'>");
			pw.println("<h2 class='my-4'>Book List</h2>");
			pw.println("<table class='table table-striped table-bordered'>");
			pw.println("<thead class='thead-dark'>");

			pw.println("<th>Book Id</th>");
			pw.println("<th>Book Name</th>");
			pw.println("<th>Book Edition</th>");
			pw.println("<th>Book Price</th>");
			pw.println("<th>Edit Book</th>");

			pw.println("<th>Delete Book</th>");

			pw.println("</tr>");
			pw.println("</thead>");
			pw.println("<tbody>");
			while (rs.next()) {

				int bookId = rs.getInt("BookId");
				pw.println("<tr>");
				pw.println("<td>" + bookId + "</td>");
				pw.println("<td>" + rs.getString("BookName") + "</td>");
				pw.println("<td>" + rs.getString("BookEdition") + "</td>");
				pw.println("<td>" + rs.getFloat("BookPrice") + "</td>");
				pw.println(
						"<td><input type='button' value='Edit' class='btn btn-primary' onclick='redirectTo(\"editBook?bookId="
								+ bookId + "\")'></td>");
				pw.println(
						"<td><input type='button' value='Delete' class='btn btn-danger' onclick='redirectTo(\"deleteBook?bookId="
								+ bookId + "\")'></td>");

				pw.println("</tr>");

			}
			pw.println("</tbody>");

			pw.println("</table>");
			pw.println("<a href='index.html'>Home</a >");
			pw.println("</div>");
			pw.println("</body>");
			pw.println("</html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}

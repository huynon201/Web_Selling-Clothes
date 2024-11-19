package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ConnectionPool;

@WebServlet("/database")
public class DatabaseServlet extends HttpServlet {

	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html");
	        try (PrintWriter out = response.getWriter();
	             Connection conn = ConnectionPool.getConnection()) {
	            String sql = "SELECT * FROM your_table";
	            PreparedStatement statement = conn.prepareStatement(sql);
	            ResultSet rs = statement.executeQuery();

	            while (rs.next()) {
	                out.println("<p>" + rs.getString("column_name") + "</p>");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
}

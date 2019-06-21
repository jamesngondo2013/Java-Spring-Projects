package com.james.training.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class TestDBServlet
 */
@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//setup connection variables
		String user = "root";
		String pass = "xxxx9070";
		
		String jdcbcURL = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		
		//get connection to database
		try {
			PrintWriter out = response.getWriter();
			
			 out.println("Connecting to database..." + jdcbcURL);
			
			Class.forName(driver);
			
			conn = (Connection)DriverManager.getConnection(jdcbcURL, user, pass);
	            
	            if (conn!=null) {
	                out.println("\nsuccessfully connected to database..." + jdcbcURL);
	            }
	            conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

}

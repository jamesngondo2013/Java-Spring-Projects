package com.training.hibernate.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {
	private static Connection conn;

	public static void main(String[] args) {
		 try {
	            conn = (Connection)DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/spring_security_demo_plaintext", "root", "chingotah9070");
	            
	            if (conn!=null) {
	                System.out.println("successfully connected to database...");
	            }
	         
	        }catch (SQLException e) {
	           
	            e.printStackTrace();
	            System.out.println("cannot connect to database...");
	        }

	}

}

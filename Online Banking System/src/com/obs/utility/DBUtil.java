package com.obs.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	public static Connection provideConnection() {
		
		Connection conn=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url="jdbc:mysql://localhost:3306/onlinebankingsystem";
		
		try {
			conn= DriverManager.getConnection(url,"root","abhi1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
}

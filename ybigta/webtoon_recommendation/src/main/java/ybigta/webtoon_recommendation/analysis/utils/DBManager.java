package ybigta.webtoon_recommendation.analysis.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	
	private static Connection connection;
	
	private static final String CONNECTION_URL = "";
	private static final String ID = "";
	private static final String PASSWORD = "";
	
	public static Connection connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(CONNECTION_URL, ID, PASSWORD);
			
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean disconnectDB() {
		try {
			connection.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}

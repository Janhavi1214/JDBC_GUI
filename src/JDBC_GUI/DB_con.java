package JDBC_GUI;

//step 1
import java.sql.*;
public class DB_con {

	public static void main(String[] args) {
		
		String URL = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "password";
		
		String dbName = "my_new_db";
		
		try {
			//step 2
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//step 3
			Connection con = DriverManager.getConnection(URL, user, password);
			
			System.out.println("Database connected successfully");
			
			//step 4
			Statement stmt = con.createStatement();
			String query = "CREATE DATABASE " + dbName; // give space after DATABASE
			
			//step 5
			stmt.executeUpdate(query);
			
			//step 6
			System.out.println("Database created successfully: " + dbName);
			
			//step 7
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}

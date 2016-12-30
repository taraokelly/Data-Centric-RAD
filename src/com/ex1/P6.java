package com.ex1;

import java.sql.*;
import java.util.Scanner;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class P6 {

	public static void main(String[] args) throws SQLException {

		Scanner sc = new Scanner(System.in);
		MysqlDataSource mysqlDS = new MysqlDataSource();
		String name = "Joker";
		String fName = "Jack";
		String sName = "Nicholson";
		String dob = "1949-03-21";
		double powers = 89.4;
		
		mysqlDS.setURL("jdbc:mysql://localhost:3306/superheroes");	
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");
		
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		System.out.println("Press Enter on the Console to Continue");
		sc.nextLine();	
		sc.close();
	
		String query = "insert into superhero_table values(" +
				"'" + name + "', " +
				"'" + fName + "', " +
				"'" + sName + "', " +
				"'" + dob + "', " +
				"'" + powers + "')";
		
		try{
			myStmt.executeUpdate(query);
			conn.close();
			myStmt.close();
			
			System.out.println(name+" sucessfully inserted into database.");
			
		}catch(MySQLIntegrityConstraintViolationException e){
			
			System.out.println("Error: Superhero "+name+" already exists in database.");
		}
		
		catch(SQLException e){
			
			System.out.println(e.getMessage());
		}
		
	}

}

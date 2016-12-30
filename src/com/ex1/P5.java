package com.ex1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class P5 {
	public static void main(String[] args) throws SQLException{
		
		Scanner sc = new Scanner(System.in);
		MysqlDataSource mysqlDS = new MysqlDataSource();
		
		mysqlDS.setURL("jdbc:mysql://localhost:3306/superheroes");	
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");
		
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		System.out.println("Press Enter on the Console to Continue");
		sc.nextLine();	
		sc.close();

		String query = "select * from superhero_table";
			
		ResultSet rs = myStmt.executeQuery(query);
		
		while( rs.next() ) {
			
			String name = rs.getString("name");	
			String realFirstName = rs.getString("real_first_name");		
			String realSurname = rs.getString("real_surname");		
			String DOB = rs.getString("dob");	
			Double powers = rs.getDouble("powers");
			
			System.out.println(name + ", " + realFirstName + ", " + realSurname + ", " + DOB + ", " + powers);
			
			}
		
		conn.close();
		myStmt.close();
		rs.close();

		} 
}

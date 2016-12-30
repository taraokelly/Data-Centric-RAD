package com.ex2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class P1 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		String superhero;
		
		MysqlDataSource mysqlDS = new MysqlDataSource();
		
		mysqlDS.setURL("jdbc:mysql://localhost:3306/superheroes");	
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * from superhero_table where name = ?");
		
		System.out.println("Enter a Superhero to search:");
		superhero = sc.nextLine();
		myStmt.setString(1, superhero);
		
		ResultSet rs = myStmt.executeQuery();
		if(!rs.next()){
			System.out.println("No Such Superhero: "+superhero);
		}
		rs.previous();
		while( rs.next() ) {
			
			String name = rs.getString("name");	
			String realFirstName = rs.getString("real_first_name");		
			String realSurname = rs.getString("real_surname");		
			String DOB = rs.getString("dob");	
			Double powers = rs.getDouble("powers");
			
			System.out.println("Superhero:\t"+ name + "\nReal Name:\t" + realSurname + ", " +realFirstName + "\nDOB:\t\t"  + DOB + "\nPowers:\t\t" + powers);
			}
		conn.close();
		myStmt.close();
		sc.close();
	}

}

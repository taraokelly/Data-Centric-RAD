package com.ex2;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class P4 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		String superhero = null, firstName, surname;
		double powers;
		String DOB;
		
			MysqlDataSource mysqlDS = new MysqlDataSource();
			mysqlDS.setURL("jdbc:mysql://localhost:3306/superheroes");	
			mysqlDS.setUser("root");
			mysqlDS.setPassword("");
	
			Connection conn = mysqlDS.getConnection();
			PreparedStatement myStmt = conn.prepareStatement("insert into superhero_table values( ?, ?, ?,?, ?)");
			
			System.out.println("========================================\nInsert Superhero\n========================================");		
			
			try{
				System.out.println("Enter Superhero:");
				superhero= sc.nextLine();
				System.out.println("Enter Real First Name:");
				firstName= sc.nextLine();
				System.out.println("Enter Real Surname:");
				surname= sc.nextLine();
				System.out.println("Enter DOB (YYYY-MM-DD):");
				DOB= sc.nextLine();
				System.out.println("Enter Powers:");
				powers= sc.nextDouble();
				myStmt.setString(1, superhero);
				myStmt.setString(2, firstName);
				myStmt.setString(3, surname);
				myStmt.setString(4, DOB);
				myStmt.setDouble(5, powers);
				int r = myStmt.executeUpdate();
				
			if (r > 0)
					System.out.println(superhero+" successfully inserted.");
			}catch(InputMismatchException e){
				System.out.println("Invalid Powers Entered.");
			}catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Error: Superhero "+superhero+" already exists in database.");
			}
			conn.close();
			myStmt.close();
			sc.close();
	}

}

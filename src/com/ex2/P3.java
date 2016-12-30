package com.ex2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class P3 {

	public static void main(String[] args) throws SQLException {
		
		Scanner sc = new Scanner(System.in);
		String superhero;
		double pNo;

		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL("jdbc:mysql://localhost:3306/superheroes");	
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("update superhero_table set powers = powers + ? where name like ?");
		
		System.out.println("========================================\nIncrease Superhero Powers\n========================================");		
		try{
			System.out.println("Enter Superhero:");
			superhero= sc.nextLine();
			System.out.println("Increase the Powers of "+superhero+" by:");
			pNo= sc.nextDouble();
			myStmt.setDouble(1, pNo);
			myStmt.setString(2, superhero);
			int r = myStmt.executeUpdate();
			
			if (r > 0)
				System.out.println(superhero+"'s powers successfully updated.");
			else
				System.out.println(superhero+ " does not exist in database.");
		}catch(InputMismatchException e){
			System.out.println("Invalid Powers Entered.");
		}
		conn.close();
		myStmt.close();
		sc.close();

	}

}

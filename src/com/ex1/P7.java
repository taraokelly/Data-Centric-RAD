package com.ex1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class P7 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		String superhero;

		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL("jdbc:mysql://localhost:3306/superheroes");	
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");
		
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		System.out.println("Enter a Superhero to delete:");
		superhero = sc.nextLine();
		
		String query = "delete from superhero_table where name = " +
				"'" + superhero+"';";
	
		try{
			int rs = myStmt.executeUpdate(query);
			if (rs > 0)
				System.out.println(superhero+" successfully deleted from database.");
			else
				System.out.println(superhero+ " does not exist in database.");
		}catch(MySQLIntegrityConstraintViolationException e){
			System.out.println("Error: Superhero "+superhero+" must be first deleted from any dependant tables.");
		}
		catch(SQLException e){	
			System.out.println(e.getMessage());
		
		conn.close();
		myStmt.close();
		sc.close();
		}
	}//main

}//P7

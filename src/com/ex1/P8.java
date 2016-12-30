package com.ex1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class P8 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		String superhero;

		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL("jdbc:mysql://localhost:3306/superheroes");	
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");
		
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		System.out.println("Enter a Superhero to increase powers by 1:");
		superhero = sc.nextLine();
		
		String query = "Update superhero_table " +
				 "set powers = powers + 1 " +
				 "where name like '" + superhero + "'";

			int rs = myStmt.executeUpdate(query);
			
			if (rs > 0)
				System.out.println(superhero+"'s powers successfully updated.");
			else
				System.out.println(superhero+ " does not exist in database.");
			
			query="select powers from superhero_table where name = '" + superhero + "'";
			ResultSet r = myStmt.executeQuery(query);
			while( r.next() ) {

				Double powers = r.getDouble("powers");
				System.out.println(superhero +" now has the powers of: " + powers);
			}
			conn.close();
			myStmt.close();
			sc.close();
	}//main

}//P8

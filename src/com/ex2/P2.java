package com.ex2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class P2 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		final String all = "ALL", allOption = "%";
		String city;
		ResultSet rs;
		boolean printBanner;
		
		MysqlDataSource mysqlDS = new MysqlDataSource();
		printBanner = true;
		mysqlDS.setURL("jdbc:mysql://localhost:3306/superheroes");	
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select c.city_name, c.country, s.name from city_table c INNER JOIN superhero_city_table s on c.id = s.id where c.city_name like ?");
		System.out.println("========================================\nSuperheroes in Cities \n========================================");		
		System.out.println("Enter City Name or \"All\" For All Cities:");
		city= sc.nextLine();
		if(city.equalsIgnoreCase(all)){
			myStmt.setString(1, allOption);
			rs = myStmt.executeQuery();	
		}
		else{
			myStmt.setString(1, city);
			rs = myStmt.executeQuery();
			if(!rs.next()){
				System.out.println("No City Named: "+city);
				printBanner = false;
			}
			rs.previous();
		}
		if(printBanner==true)
			System.out.println("City\tCountry\t\tSuperhero\n========================================");
		while( rs.next() ) {
			String cName = rs.getString("city_name");
			String country = rs.getString("country");
			String sName = rs.getString("name");
			System.out.println(cName +"\t" + country+"\t" + sName);
			}
		conn.close();
		myStmt.close();
		sc.close();
	}

}

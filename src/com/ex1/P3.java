package com.ex1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class P3 {

	public static void main(String[] args) throws SQLException {
		
		MysqlDataSource mysqlDS = new MysqlDataSource();
		
		mysqlDS.setURL("jdbc:mysql://localhost:3306/employee_kin");	
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");
		
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		
		String query = "select e.ename, k.NOK_name, s.salary from employee_table e Left Join next_of_kin_table k on e.NextOfKin = k.NOK_ID Inner Join salary s on e.eid = s.eid;";
			
		ResultSet rs = myStmt.executeQuery(query);
		
		while( rs.next() ) {
			
			String eName = rs.getString("ename");	
			String kName = rs.getString("NOK_name");
			Double salary = rs.getDouble("salary");
			System.out.println(eName + ", "+ kName + ", " + salary);
			}
		
		conn.close();
		myStmt.close();
		rs.close();

	}

}

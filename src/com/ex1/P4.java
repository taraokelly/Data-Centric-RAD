package com.ex1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class P4 {

	public static void main(String[] args) throws SQLException {
		
		MysqlDataSource mysqlDS = new MysqlDataSource();
		
		mysqlDS.setURL("jdbc:mysql://localhost:3306/studentDB3");	
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");
		
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		
		String query = "select s.student_name 'Student', c.course_name 'Course', col.college_name 'College', col.county 'County',t.main_town 'Main Town' from student_table s Inner Join course_table c on s.course_id = c.course_id Inner Join college_table col on c.college_id = col.college_id Inner Join county_table t on col.county = t.county_name;";
			
		ResultSet rs = myStmt.executeQuery(query);
		
		while( rs.next() ) {
			
			String student = rs.getString("Student");
			String course= rs.getString("Course");
			String college = rs.getString("College");
			String county = rs.getString("County");
			String mainTown = rs.getString("Main Town");
			System.out.println(student +", "+course+", "+college+", "+county+", "+mainTown);
			}
		
		conn.close();
		myStmt.close();
		rs.close();

	}

}

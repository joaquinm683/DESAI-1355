package com.joaquinmoreno.ep1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class Ep1Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Ep1Application.class, args);
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ep01_joaquinmoreno", "root", "rootpass");


		//1. Statement
		Statement st = connection.createStatement();
		String SQLQuery = "Select * from books";

		ResultSet resultSet = st.executeQuery(SQLQuery);

		System.out.println("========================STATEMENT - SELECT ALL========================");

		while(resultSet.next()){
			System.out.println("Title: " + resultSet.getString("book_title") +
					" | Description: " + resultSet.getString("description") +
					" | Category: " + resultSet.getString("category") +
					" | Price: " + resultSet.getString("price"));
		}

		//2. PreparedStatement
		PreparedStatement pst = connection.prepareStatement("update books set price=? where category=?");
		pst.setDouble(1,6.99);
		pst.setString(2,"business");

		pst.executeUpdate();

		System.out.println("========================PREPARED STATEMENT - UPDATE========================");

		pst = connection.prepareStatement("Select * from books where category = ?");
		pst.setString(1,"business");

		ResultSet pstResultSet = pst.executeQuery();

		while(pstResultSet.next()){
			System.out.println("Title: " + pstResultSet.getString("book_title") +
					" | Description: " + pstResultSet.getString("description") +
					" | Category: " + pstResultSet.getString("category") +
					" | Price: " + pstResultSet.getString("price"));
		}


		System.out.println("========================CALLABLE STATEMENT - CREAR  REGISTRO========================");
		//3. CallableStatement

		CallableStatement callableStatement = connection.prepareCall("{call usp_addBook(?,?,?,?)}");
		callableStatement.setString(1,"But Is It User Friendly?");
		callableStatement.setString(2,"popular_comp");
		callableStatement.setString(3,"A survey of software for the naive user, focusing on the ''friendliness'' of each.");
		callableStatement.setDouble(4,25.00);

		ResultSet Call1_resultSet = callableStatement.executeQuery();

		CallableStatement callableStatementListar = connection.prepareCall("{call usp_listBook(?)}");
		callableStatementListar.setString(1,"But Is It User Friendly?");

		ResultSet Call2_resultSet = callableStatementListar.executeQuery();

		while (Call2_resultSet.next()){

		System.out.println("Title: " + Call2_resultSet.getString("book_title") +
				" | Description: " + Call2_resultSet.getString("description") +
				" | Category: " + Call2_resultSet.getString("category") +
				" | Price: " + Call2_resultSet.getString("price"));
		}











	}

}

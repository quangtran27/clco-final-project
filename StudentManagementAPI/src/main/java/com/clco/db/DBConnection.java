package com.clco.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");

    // Assign database parameters
    String url = "jdbc:mysql://ec2-52-87-236-86.compute-1.amazonaws.com:3306/db?useUnicode=true&characterEncoding=UTF-8";
    String user = "root";
    String password = "300102";

    // Create a connection to the database
    return DriverManager.getConnection(url, user, password);
  }
}
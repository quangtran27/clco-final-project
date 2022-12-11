package com.clco.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.clco.model.Student;

public class DBConnection {
  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");

    // Assign database parameters
    String url = "jdbc:mysql://34.201.150.254:3306/db?useUnicode=true&characterEncoding=UTF-8";
    String user = "root";
    String password = "300102";

    // Create a connection to the database
    return DriverManager.getConnection(url, user, password);
  }
}
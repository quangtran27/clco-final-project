package com.clco.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnection {
  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");

    // Assign database parameters
    String url = "jdbc:mysql://3.82.117.24:3306/db?useUnicode=true&characterEncoding=UTF-8";
    String user = "root";
    String password = "300102";

    // Create a connection to the database
    return DriverManager.getConnection(url, user, password);
  }

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    Connection connection = getConnection();
    String sql = "SELECT * FROM Student;";
    PreparedStatement ps = connection.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      String id = rs.getString("id");
      String fullname = rs.getString("fullname");
      String gender = rs.getString("gender");
      String major = rs.getString("major");
      Date birthday = rs.getDate("birthday");
      Double gpa = rs.getDouble("gpa");
      System.out.println(id + " " + fullname + " " + gender + " " + major + " " + birthday.toString() + " " + gpa.toString());
    }
  }
}
package com.clco.db;

import com.clco.model.Student;
import com.clco.util.Convert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDB {
  private Connection connection;
  public StudentDB() {
  }

  private void getConnection() throws SQLException, ClassNotFoundException {
    this.connection = connection == null ? DBConnection.getConnection() : connection;
  }

  public boolean insert(Student student) throws SQLException, ClassNotFoundException {
    getConnection();
    String sql = "INSERT INTO Student (id, fullname, gender, major, birthday, gpa) VALUES (?, ?, ?, ?, ?, ?)";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1, student.getId());
    ps.setString(2, student.getFullName());
    ps.setString(3, student.getGender());
    ps.setString(4, student.getMajor());
    ps.setString(5, Convert.toInsertFormat(student.getBirthday()));
    ps.setString(6, student.getGpa().toString());
    return ps.executeUpdate() > 0;
  }

  public boolean update(Student student) throws SQLException, ClassNotFoundException {
    getConnection();
    String sql = "UPDATE Student SET fullName = ?, gender = ?, major = ?, birthday = ?, gpa = ? WHERE id = ?;";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1, student.getFullName());
    ps.setString(2, student.getGender());
    ps.setString(3, student.getMajor());
    ps.setString(4, Convert.toInsertFormat(student.getBirthday()));
    ps.setString(5, student.getGpa().toString());
    ps.setString(6, student.getId());

    return ps.executeUpdate() > 0;
  }

  public boolean delete(String id) throws SQLException, ClassNotFoundException {
    getConnection();

    String sql = "DELETE FROM Student WHERE id = ?";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1, id);

    return ps.executeUpdate() > 0;
  }

  public Student get(String id) throws SQLException, ClassNotFoundException {
    getConnection();
    Student student = null;
    String sql = "SELECT * FROM Student WHERE id = ?";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1, id);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      String fullname = rs.getString("fullname");
      String gender = rs.getString("gender");
      String major = rs.getString("major");
      Date birthday = rs.getDate("birthday");
      Double gpa = rs.getDouble("gpa");
      student = new Student(id, fullname, gender, major, birthday, gpa);
    }
    return student;
  }
  public List<Student> getAll() throws SQLException, ClassNotFoundException {
    getConnection();
    List<Student> students = new ArrayList<>();

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
      students.add(new Student(id, fullname, gender, major, birthday, gpa));
    }
    return students;
  }

}

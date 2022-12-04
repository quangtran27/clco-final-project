package com.clco;

import com.clco.db.StudentDB;
import com.clco.model.Student;

import java.sql.SQLException;
import java.util.List;

public class Main {
  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    List<Student> students = new StudentDB().getAll();
    for (Student student: students) {
      System.out.println(student.getFullName());
    }
  }
}

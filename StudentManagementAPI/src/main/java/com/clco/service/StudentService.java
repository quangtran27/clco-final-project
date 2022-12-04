package com.clco.service;

import com.clco.db.StudentDB;
import com.clco.model.Student;
import com.clco.util.Convert;
import com.clco.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class StudentService {
  private HttpServletRequest req;
  private HttpServletResponse resp;
  private final StudentDB studentDB;

  public HttpServletRequest getReq() {
    return req;
  }
  public void setReq(HttpServletRequest req) {
    this.req = req;
  }
  public HttpServletResponse getResp() {
    return resp;
  }
  public void setResp(HttpServletResponse resp) {
    this.resp = resp;
  }

  public StudentService(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
    this.req = req;
    this.resp = resp;
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    this.studentDB = new StudentDB();
  }

  public void listAllStudent() {
    try {
      List<Student> students = studentDB.getAll();
      PrintWriter out = resp.getWriter();
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");

      out.println(Convert.toJson(students));
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getStudent() {
    String id = req.getParameter("id");
    try {
      Student student = studentDB.get(id);
      PrintWriter out = resp.getWriter();
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      out.println(Convert.toJson(student));
      out.flush();
    } catch (SQLException | ClassNotFoundException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void addStudent() throws IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    try {
      String id = req.getParameter("id");
      String fullname = req.getParameter("fullName");
      String gender = req.getParameter("gender");
      String major = req.getParameter("major");
      String birthdayString = req.getParameter("birthday");
      Date birthday = Convert.stringToDate(birthdayString);
      double gpa = Double.parseDouble(req.getParameter("gpa"));

      if (id != null && fullname != null && gender != null && major != null && birthday != null && gpa <= 4 && gpa >= 0) {
        Student student = new Student(id, fullname, gender, major, birthday, gpa);

        boolean res = studentDB.insert(student);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.println(Convert.toJson(new Result(res)));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateStudent() throws UnsupportedEncodingException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    try {
      String id = req.getParameter("id");
      String fullname = req.getParameter("fullName");
      String gender = req.getParameter("gender");
      String major = req.getParameter("major");
      String birthdayString = req.getParameter("birthday");
      Date birthday = Convert.stringToDate(birthdayString);
      double gpa = Double.parseDouble(req.getParameter("gpa"));

      if (id != null && fullname != null && gender != null && major != null && birthday != null && gpa <= 4 && gpa >= 0) {
        Student student = new Student(id, fullname, gender, major, birthday, gpa);
        boolean res = studentDB.update(student);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.println(Convert.toJson(new Result(res)));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteStudent() throws SQLException, ClassNotFoundException, IOException {
    String id = req.getParameter("id");
    if (id != null) {
      boolean res = studentDB.delete(id);
      PrintWriter out = resp.getWriter();
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      out.println(Convert.toJson(new Result(res)));
    }
  }
}

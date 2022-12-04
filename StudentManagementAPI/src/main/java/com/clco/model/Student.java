package com.clco.model;

import java.util.Date;

public class Student {
  private String id;
  private String fullName;
  private String gender;
  private String major;
  private Date birthday;
  private Double gpa;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getFullName() {
    return fullName;
  }
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  public String getGender() {
    return gender;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }
  public String getMajor() {
    return major;
  }
  public void setMajor(String major) {
    this.major = major;
  }
  public Date getBirthday() {
    return birthday;
  }
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
  public Double getGpa() {
    return gpa;
  }
  public void setGpa(Double gpa) {
    this.gpa = gpa;
  }

  public Student() {

  }

  public Student(String id, String fullName, String gender, String major, Date date, Double gpa) {
    super();
    this.id = id;
    this.fullName = fullName;
    this.gender = gender;
    this.major = major;
    this.birthday = date;
    this.gpa = gpa;
  }
}
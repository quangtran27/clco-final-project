package com.clco.util;

public class Result {
  private boolean isSuccess;
  public boolean isSuccess() {
    return isSuccess;
  }
  public void setSuccess(boolean success) {
    isSuccess = success;
  }
  public Result(boolean isSuccess) {
    this.isSuccess = isSuccess;
  }
}

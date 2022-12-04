package com.clco.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convert {
  public static String toInsertFormat(Date date) {
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    return df.format(date);
  }

  public static Date stringToDate(String dateString) throws ParseException {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    return df.parse(dateString);
  }

  public static String toJson(Object o) {
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    return gson.toJson(o);
  }
}

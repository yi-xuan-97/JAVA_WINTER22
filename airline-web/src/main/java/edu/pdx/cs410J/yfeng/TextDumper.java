package edu.pdx.cs410J.yfeng;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

public class TextDumper {
  private final Writer writer;

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  public void dump(Airline airline) {
    try (
      PrintWriter pw = new PrintWriter(this.writer)
    ){
//      for (Map.Entry<String, String> entry : dictionary.entrySet()) {
//        pw.println(entry.getKey() + " : " + entry.getValue());
//      }
      pw.println(airline.getName());

      airline.getFlights().forEach(flight -> {
                pw.println(flight.getNumber());

              }
      );

      pw.flush();
    }
  }
}

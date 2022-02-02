package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.AirlineDumper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * TextDumper class to dump the airline information into the text file
 */
public class TextDumper implements AirlineDumper<Airline> {
  private final Writer writer;

  /**
   * cLass constructor
   * @param writer Bufferwriter to write information into the file
   */
  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  /**
   * function that dump the airline information into the text file
   * @param airline airline object which contains airline name and flight information
   */
  @Override
  public void dump(Airline airline) {
    try (
            PrintWriter pw = new PrintWriter(this.writer)
    ) {

      String check = airline.getName();
      ArrayList<Flight> temp = (ArrayList<Flight>) airline.getFlights();
      for(Flight t: temp){
        String result;
        if(t.getDepartureString()!="" && t.getArrivalString()!=""){

          if(check.contains(" ")){
            result = "\"" +  airline.getName() + "\" " + String.valueOf(t.getNumber())
                    + " " + t.getSource() + " "
                    + t.getDepartureString().replace("$", "").replace(",", "") + " "
                    + t.getDestination() + " "
                    + t.getArrivalString().replace("$", "").replace(",", "");
          }
          else{
            result = airline.getName() + " " + String.valueOf(t.getNumber())
                    + " " + t.getSource() + " "
                    + t.getDepartureString().replace("$", "").replace(",", "") + " "
                    + t.getDestination() + " "
                    + t.getArrivalString().replace("$", "").replace(",", "");
          }
        }
        else{
          if(check.contains(" ")){
            result = "\"" +  airline.getName() + "\" ";
          }
          else{
            result = airline.getName();
          }
        }
        pw.println(result);
      }

//      pw.println(airline.getName());

      pw.flush();
    }
  }
}

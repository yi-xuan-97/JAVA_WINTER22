package edu.pdx.cs410J.yfeng;

import com.google.common.annotations.VisibleForTesting;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PrettyPrinter {
  private final Writer writer;

  @VisibleForTesting
  static String formatWordCount(int count )
  {
    return String.format( "Dictionary on server contains %d words", count );
  }

  @VisibleForTesting
  static String formatDictionaryEntry(String word, String definition )
  {
    return String.format("  %s : %s", word, definition);
  }


  public PrettyPrinter(Writer writer) {
    this.writer = writer;
  }

  public void dump(Airline airline) throws IOException {
    try (
            PrintWriter pw = new PrintWriter(this.writer)
    ) {

      String check = airline.getName();
      pw.println("The name of airline is: " + check + "\n");

      ArrayList<Flight> temp = (ArrayList<Flight>) airline.getFlights();
      for(Flight t: temp){
        String result;

        Date endDate = t.getArrival();
        Date startDate = t.getDeparture();
        long duration  = endDate.getTime() - startDate.getTime();
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);

        result = "Flight number: " + String.valueOf(t.getNumber()) + "\n"
                + "Departure airport: " + t.getSource() + "\n"
                + "Departure date and time: " + t.getDeparture() + "\n"
                + "Destination airport: " + t.getDestination() + "\n"
                + "Arrival date and time: " + t.getArrival() + "\n"
                + "Total duration of this flight: " + diffInMinutes + " min" + "\n";

        pw.println(result);
      }
      pw.flush();

    }

  }
}

package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.AirlineDumper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * This is a class for print airline information prettyly to file or standard out
 */
public class PrettyPrinter implements AirlineDumper<Airline> {
    private final Writer writer;

    /**
     * Class constructor
     * @param writer Bufferwriter to write into the text file
     */
    public PrettyPrinter(Writer writer) {
        this.writer=writer;
    }

    /**
     * Dump function to dump the information of an airline into text file prettily
     * @param airline airline object
     * @throws IOException may throw IO expection from dump function
     */
    @Override
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

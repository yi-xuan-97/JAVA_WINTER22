package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.AirlineDumper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

public class PrettyPrinter implements AirlineDumper<Airline> {
    private final Writer writer;

    public PrettyPrinter(Writer writer) {
        this.writer=writer;
    }

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

                result = "Flight number: " + String.valueOf(t.getNumber()) + "\n"
                        + "Departure airport: " + t.getSource() + "\n"
                        + "Departure date and time: " + t.getDeparture() + "\n"
                        + "Destination airport: " + t.getDestination() + "\n"
                        + "Arrival date and time: " + t.getArrival() + "\n";

                pw.println(result);
            }
            pw.flush();

        }

    }
}

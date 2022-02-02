package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.AirlineDumper;

import java.io.IOException;
import java.io.Writer;

public class PrettyPrinter implements AirlineDumper<Airline> {
    private final Writer writer;

    public PrettyPrinter() {
        writer = null;
    }

    @Override
    public void dump(Airline airline) throws IOException {

    }
}

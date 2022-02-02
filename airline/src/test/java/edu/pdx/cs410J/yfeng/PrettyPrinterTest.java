package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class PrettyPrinterTest {

    @Test
    void airlineNameIsDumpedInTextFormat() throws IOException {
        String airlineName = "Test Airline";
        Airline airline = new Airline(airlineName);
        Flight flight = new Flight();
        airline.addFlight(flight);

        StringWriter sw = new StringWriter();
        PrettyPrinter pretty = new PrettyPrinter(sw);
        pretty.dump(airline);

        String text = sw.toString();
        assertThat(text, containsString(airlineName));
    }


}

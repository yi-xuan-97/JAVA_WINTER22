package edu.pdx.cs410J.yfeng;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Class to test PrettyPrint class
 */
public class PrettyPrinterTest {

    /**
     * Test if an airline contains certain information can be dumped
     * @throws IOException may throw IO expcetion from dump function
     */
    @Test
    void airlineNameIsDumpedInTextFormat() throws IOException {
        String airlineName = "Test Airline";
        Airline airline = new Airline(airlineName);

        Flight flight = new Flight();
        Flight flight1 = new Flight();

        flight.setFlightNumber("123");
        flight.setSrc("yum");
        flight.setDepart("1/1/2022 01:00");
        flight.setDest("pdx");
        flight.setArrive("1/1/2022 02:00");

        flight1.setFlightNumber("123");
        flight1.setSrc("yum");
        flight1.setDepart("1/1/2022 02:00");
        flight1.setDest("pdx");
        flight1.setArrive("1/1/2022 03:00");

        airline.addFlight(flight);
        airline.addFlight(flight1);

        StringWriter sw = new StringWriter();
        PrettyPrinter pretty = new PrettyPrinter(sw);
        pretty.dump(airline);

        String text = sw.toString();
        assertThat(text, containsString(airlineName));
    }


}

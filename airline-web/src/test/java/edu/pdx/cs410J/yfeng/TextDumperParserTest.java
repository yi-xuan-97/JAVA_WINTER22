package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class TextDumperParserTest {

//  @Test
//  void emptyMapCanBeDumpedAndParsed() throws ParserException {
//    Map<String, String> map = Collections.emptyMap();
//    Map<String, String> read = dumpAndParse(map);
//    assertThat(read, equalTo(map));
//  }

    /**
     * dump airline and parse airline
     * @param airline airline obect
     * @return airline name
     * @throws ParserException may throw parser exception
     */
    private Airline dumpAndParse(Airline airline) throws ParserException {
        StringWriter sw = new StringWriter();
        TextDumper dumper = new TextDumper(sw);
        dumper.dump(airline);

        String text = sw.toString();

        TextParser parser = new TextParser(new StringReader(text));
        return parser.parse();
    }

    /**
     * dump can be parse
     * @throws ParserException may throw parser exception
     */
    @Test
    void dumpedTextCanBeParsed() throws ParserException {
        String airlineName = "Airline Name";
        int flightNumber = 456;

        Airline airline = new Airline(airlineName);
        airline.addFlight(new Flight(flightNumber));

        Airline airline2 = dumpAndParse(airline);
        assertThat(airline2.getName(), equalTo(airlineName));
        Collection<Flight> flights = airline2.getFlights();
        assertThat(flights, hasSize(1));
        Flight flight = flights.iterator().next();
        assertThat(flight.getNumber(), equalTo(flightNumber));
    }
}

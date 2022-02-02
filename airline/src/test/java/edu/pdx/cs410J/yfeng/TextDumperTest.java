package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class TextDumperTest {

  /**
   * test function to test if airline name can be write into the file
   */
  @Test
  void airlineNameIsDumpedInTextFormat() {
    String airlineName = "Test Airline";
    Airline airline = new Airline(airlineName);
    Flight flight = new Flight();
    airline.addFlight(flight);

    StringWriter sw = new StringWriter();
    TextDumper dumper = new TextDumper(sw);
    dumper.dump(airline);

    String text = sw.toString();
    assertThat(text, containsString(airlineName));
  }

  /**
   * Test function to test if program can write into file and then read it
   * @param tempDir dirctory
   * @throws IOException may throw IO exectation
   * @throws ParserException may throw parser expectation
   */
  @Test
  void canParseTextWrittenByTextDumper(@TempDir File tempDir) throws IOException, ParserException {
    String airlineName = "Test Airline";
    Airline airline = new Airline(airlineName);
    Flight flight = new Flight();
    airline.addFlight(flight);

    File textFile = new File(tempDir, "airline.txt");
    TextDumper dumper = new TextDumper(new FileWriter(textFile));
    dumper.dump(airline);

    TextParser parser = new TextParser(new FileReader(textFile));
    Airline read = parser.parse();
    assertThat(read.getName(), equalTo(airlineName));
  }

  /**
   * Test if dump function can dump airline with certain information
   */
  @Test
  void candumpwithflights(){
    String airlineName = "Test";
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
    TextDumper dumper = new TextDumper(sw);
    dumper.dump(airline);

    String text = sw.toString();
    assertThat(text, containsString(airlineName));
    assertThat(text, containsString("yum"));
  }

  @Test
  void candumpwithflightsnamewithquote(){
    String airlineName = "Test airline";
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
    TextDumper dumper = new TextDumper(sw);
    dumper.dump(airline);

    String text = sw.toString();
    assertThat(text, containsString(airlineName));
    assertThat(text, containsString("yum"));
  }
}

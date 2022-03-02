package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * XmlDuperTest class that test XmlDumper class
 */
public class XmlDumperTest {

    /**
     * Test if xmlparser can parse xml file after xmldumper dump to the xml file
     * @throws IOException May throw IOException
     * @throws ParserException May throw ParserException
     */
    @Test
    void canParseTextWrittenByTextDumper(@TempDir File tempDir) throws IOException, ParserException {

        String airlineName = "Valid Airlines";
        Airline airline = new Airline(airlineName);
        Flight flight = new Flight();
        Flight flight1 = new Flight();

        flight.setFlightNumber("123");
        flight.setSrc("PDX");
        flight.setDepart("1/1/2022 01:00");
        flight.setDest("ABQ");
        flight.setArrive("1/1/2022 02:00");

        flight1.setFlightNumber("123");
        flight1.setSrc("ABQ");
        flight1.setDepart("1/1/2022 02:00");
        flight1.setDest("PDX");
        flight1.setArrive("1/1/2022 03:00");

        airline.addFlight(flight);
        airline.addFlight(flight1);

//        XmlDumper dumper = new XmlDumper(Objects.requireNonNull(getClass().getResource("valid-airline.xml")).getPath());
        File textFile = new File(tempDir, "airlinetest.xml");
        TextDumper dumper = new TextDumper(new FileWriter(textFile));
        dumper.dump(airline);

//        XmlParser xmlparser = new XmlParser(Objects.requireNonNull(getClass().getResource("valid-airline.xml")).getPath());
//        Airline read = xmlparser.parse();
//        XmlParser parser = new XmlParser(new FileReader(textFile));
//        Airline read = parser.parse();
//        assertThat(read.getName(), equalTo(airlineName));

    }
}

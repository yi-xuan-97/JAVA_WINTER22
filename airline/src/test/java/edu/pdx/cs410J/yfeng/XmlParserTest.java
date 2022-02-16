package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * XmlParserTest class to test XmlParser class
 */
public class XmlParserTest {

    /**
     * Test that test if xmlparse can parse
     * @throws ParserException May throw parser exception
     */
    @Test
    void validxmlFileCanBeParsed() throws ParserException {

        XmlParser xmlparser = new XmlParser(Objects.requireNonNull(getClass().getResource("valid-airline.xml")).getPath());
        Airline airline = xmlparser.parse();

        assertThat(airline.getName(), equalTo("Valid Airlines"));
    }

}

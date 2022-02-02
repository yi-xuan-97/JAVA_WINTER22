package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class to test TextParser class
 */
public class TextParserTest {

  /**
   * Test if a text file can be parsed
   * @throws ParserException may throw an parse expectation from parse() function
   */
  @Test
  void validTextFileCanBeParsed() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("valid-airline.txt");
    assertThat(resource, notNullValue());

    TextParser parser = new TextParser(new InputStreamReader(resource));
    Airline airline = parser.parse();
    assertThat(airline.getName(), equalTo("Test"));
  }

  /**
   * Check if an invalid file can be process
   */
  @Test
  void invalidTextFileThrowsParserException() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("empty-airline.txt");
    assertThat(resource, notNullValue());

    TextParser parser = new TextParser(new InputStreamReader(resource));
    Airline airline = parser.parse();
    assertThat(airline, is(nullValue()));
  }
}

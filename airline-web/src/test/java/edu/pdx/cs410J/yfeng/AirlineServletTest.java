package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link AirlineServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
class AirlineServletTest {

  @Test
  void missingAirlineNameReturnpRreconditionFailedSatus() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    // Nothing is written to the response's PrintWriter
//    verify(pw).println(Messages.missingRequiredParameter(AirlineServlet.AIRLINENAME_PARAMETER));
//    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.missingRequiredParameter(AirlineServlet.AIRLINENAME_PARAMETER));
  }

  @Test
  void addOneWordToDictionary() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    String airlineName = "TEST AIRLINE";
    String flightnumber = "123";

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AirlineServlet.AIRLINENAME_PARAMETER)).thenReturn(airlineName);
    when(request.getParameter(AirlineServlet.FLIGHTNUMBER_PARAMETER)).thenReturn(flightnumber);

    HttpServletResponse response = mock(HttpServletResponse.class);

    // Use a StringWriter to gather the text from multiple calls to println()
    StringWriter stringWriter = new StringWriter();
    PrintWriter pw = new PrintWriter(stringWriter, true);

    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);

    assertThat(stringWriter.toString(), containsString(Messages.definedWordAs(airlineName, flightnumber)));

    // Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
//    ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
    verify(response).setStatus(eq(HttpServletResponse.SC_OK));

//    assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));

    Airline airline = servlet.getOrCreateAirline(airlineName);
    assertThat(airline, notNullValue());
    Collection<Flight> flights = airline.getFlights();
    assertThat(flights, hasSize(1));
    Flight flight = flights.iterator().next();
    assertThat(String.valueOf(flight.getNumber()), equalTo(flightnumber));
  }

  @Test
  void returnTextRepresentationOfAirline() throws IOException, ParserException {
    String airlineName = "Test Airlines";
    int flightNumber = 123;

    AirlineServlet servlet = new AirlineServlet();
    Airline airline = servlet.getOrCreateAirline(airlineName);
    airline.addFlight(new Flight(flightNumber));

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AirlineServlet.AIRLINENAME_PARAMETER)).thenReturn(airlineName);

    HttpServletResponse response = mock(HttpServletResponse.class);
    StringWriter sw = new StringWriter();
    when(response.getWriter()).thenReturn(new PrintWriter(sw, true));

    servlet.doGet(request, response);

    verify(response).setStatus(eq(HttpServletResponse.SC_OK));

    String text = sw.toString();
    Airline airline2 = new TextParser(new StringReader(text)).parse();

    assertThat(airline2.getName(), equalTo(airlineName));
    Collection<Flight> flights = airline2.getFlights();
    assertThat(flights, hasSize(1));
    Flight flight = flights.iterator().next();
    assertThat(flight.getNumber(), equalTo(flightNumber));
  }

}

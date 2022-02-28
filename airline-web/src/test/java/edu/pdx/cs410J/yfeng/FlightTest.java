package edu.pdx.cs410J.yfeng;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Flight} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class FlightTest {

  /**
   * This unit test will need to be modified (likely deleted) as you implement
   * your project.
   */
  @Test
  void getArrivalStringNeedsToBeImplemented() {
    Flight flight = new Flight();
    assertThat(flight.getDeparture(), is(nullValue()));
  }

  /**
   * Test to set flight information into a flight object
   */
  @Test
  void setFlightinfo() {
    Flight flight = new Flight();
    flight.setFlightNumber("4");
    flight.setSrc("yum");
    flight.setDepart("01/11/2022 11:11");
    flight.setDest("pdx");
    flight.setArrive("01/11/2022 13:44");
    assertThat(flight.getNumber(),equalTo(4));
    assertThat(flight.getSource(), equalTo("yum"));
    assertThat(flight.getDepartureString(), equalTo("1/11/22, 11:11 AM"));
    assertThat(flight.getDestination(), equalTo("pdx"));
    assertThat(flight.getArrivalString(), equalTo("1/11/22, 1:44 PM"));
  }

  /**
   * This unit test will need to be modified (likely deleted) as you implement
   * your project.
   */
  @Test
  void initiallyAllFlightsHaveTheSamevalue () {
    Flight flight = new Flight();
    assertThat(flight.getNumber(), equalTo(0));
    assertThat(flight.getSource(), equalTo(""));
    assertThat(flight.getDepartureString(), equalTo(""));
    assertThat(flight.getDestination(),equalTo(""));
    assertThat(flight.getArrivalString(), equalTo(""));
  }

  @Test
  void forProject1ItIsOkayIfGetDepartureTimeReturnsNull() {
    Flight flight = new Flight();
    assertThat(flight.getDeparture(), is(nullValue()));
  }
  
}

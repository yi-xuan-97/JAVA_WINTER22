package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.AbstractAirline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Airline extends AbstractAirline<Flight> {
  private final String name;
  private List<Flight> list = new ArrayList<Flight>();

  public Airline(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void addFlight(Flight flight) {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    list.add(flight);
  }

  @Override
  public Collection<Flight> getFlights() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    return list;
  }
}

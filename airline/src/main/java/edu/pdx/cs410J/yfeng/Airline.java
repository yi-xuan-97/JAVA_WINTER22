package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.AbstractAirline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * <p>Airline class inhertant from AbstractAirline
 * </p>
 * @author yixuanfeng
 */
public class Airline extends AbstractAirline<Flight> {
  private final String name;
  private List<Flight> list = new ArrayList<Flight>();

  public Airline(String name) {
    this.name = name;
  }

  /**
   * <p>This is a function to get the name of the airline
   * </p>
   * @return the name of airline
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * <p>This is a function to add a flight to the airline
   * </p>
   * @param flight an object of Flight class
   */
  @Override
  public void addFlight(Flight flight) {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    list.add(flight);
  }

  /**
   * <p>This is a function to return all flight within an airline
   * </p>
   * @return a list of Flight object
   */
  @Override
  public Collection<Flight> getFlights() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    return list;
  }
}

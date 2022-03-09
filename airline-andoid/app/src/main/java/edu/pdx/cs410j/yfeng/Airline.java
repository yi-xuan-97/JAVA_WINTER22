package edu.pdx.cs410j.yfeng;

import edu.pdx.cs410J.AbstractAirline;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    boolean flag = false;
    for(Flight f:list){
      if((f.getSource().equalsIgnoreCase(flight.getSource()))
              && (f.getDepartureString().equalsIgnoreCase(flight.getDepartureString()))){
        flag = true;
      }
    }
    if(!flag){
      list.add(flight);
    }

    Comparator<Flight> dateComparator = Comparator.comparing(Flight::getSource)
            .thenComparing(Flight::getDepartureString);
    list.sort(dateComparator);

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

  public String print(){
    String temp = "";
    temp = getName() + "\n\n";

    ArrayList<Flight> flights = (ArrayList<Flight>) getFlights();
    for(Flight flight: flights){
      String tempo1 = String.valueOf(flight.getNumber());
      String tempo2 = flight.getSource();
      String tempo3 = flight.getDepartureString();
      String tempo4 = flight.getDestination();
      String tempo5 = flight.getArrivalString();
      temp += "The flight number is: "+ tempo1 + "\n" +
              "Three letter code of departure airport: "+ tempo2 + "\n" +
              "Departure date and time: "+ tempo3 + "\n" +
              "Three letter code of arrival airport: "+ tempo4 + "\n" +
              "Departure date and time: "+ tempo5 + "\n\n";
    }
    return temp;
  }

  public String prettyprint(){
    String check = getName();
    String result = "The name of airline is: " + check + "\n\n";

    ArrayList<Flight> temp = (ArrayList<Flight>) getFlights();
    for(Flight t: temp){

      Date endDate = t.getArrival();
      Date startDate = t.getDeparture();
      long duration  = endDate.getTime() - startDate.getTime();
      long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);

      result += "Flight number: " + t.getNumber() + "\n"
              + "Departure airport: " + t.getSource() + "\n"
              + "Departure date and time: " + t.getDeparture() + "\n"
              + "Destination airport: " + t.getDestination() + "\n"
              + "Arrival date and time: " + t.getArrival() + "\n"
              + "Total duration of this flight: " + diffInMinutes + " min" + "\n\n";

    }
    return result
    + "--------------------------------------------------------------------" + "\n";
  }
}

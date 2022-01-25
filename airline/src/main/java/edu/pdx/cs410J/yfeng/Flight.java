package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.AbstractFlight;


public class Flight extends AbstractFlight {

  private int flightNumber;
  private String src;
  private String depart;
  private String dest;
  private String arrive;

  public Flight(){
    this.flightNumber = 0;
    this.src = "";
    this.depart = "";
    this.dest = "";
    this.arrive = "";
  }

  /**
   * <p>This is a function to set the arrive with a string
   * </p>
   * @param arrive an string that contains the arrive information
   */
  public void setArrive(String arrive) {
    this.arrive = arrive;
  }

  /**
   * <p>This is a function to set the department with a string
   * </p>
   * @param depart an string that contains the department information
   */
  public void setDepart(String depart) {
    this.depart = depart;
  }

  /**
   * <p>This is a function to set the destination with a string
   * </p>
   * @param dest an string that contains the destination information
   */
  public void setDest(String dest) {
    this.dest = dest;
  }

  /**
   * <p>This is a function to set the flight number with a string
   * </p>
   * @param flightNumber an string that contains the flightnumber information
   */
  public void setFlightNumber(String flightNumber) {
    //Check if flight number is numeric
    try{
      this.flightNumber = Integer.parseInt(flightNumber);
    }
    catch (NumberFormatException ex){
      System.err.println("Please make sure your flight number is numeric");
      System.exit(1);
    }
  }

  /**
   * <p>This is a function to set the src with a string
   * </p>
   * @param src an string that contains the src information
   */
  public void setSrc(String src) {
    this.src = src;
  }

  /**
   * <p>This is a function to get flight number
   * </p>
   * @return the number of current flight
   */
  @Override
  public int getNumber() {
    System.out.println("The flight number is: "+flightNumber);
    return Integer.parseInt(String.valueOf(flightNumber));
  }

  /**
   * <p>This is a function to get three letter code of departure airport
   * </p>
   * @return string of three letter code of departure airport
   */
  @Override
  public String getSource() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    System.out.println("Three letter code of departure airport: "+this.src);
    return this.src;
  }

  /**
   * <p>This is a function to get departure date and time
   * </p>
   * @return departure date and time
   */
  @Override
  public String getDepartureString() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    System.out.println("Departure date and time: "+this.depart);
    return this.depart;
  }

  /**
   * <p>This is a function to get three letter code of arrival airport
   * </p>
   * @return three departure code of arrival airport
   */
  @Override
  public String getDestination() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    System.out.println("Three letter code of arrival airport: "+this.dest);
    return this.dest;
  }

  /**
   * <p>This is a function to get arrival date and time
   * </p>
   * @return the arrival date and time
   */
  @Override
  public String getArrivalString() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    System.out.println("Arrival date and time: "+this.arrive);
    return this.arrive;
  }
}

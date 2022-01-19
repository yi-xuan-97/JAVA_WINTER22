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

  public void setArrive(String arrive) {
    this.arrive = arrive;
  }

  public void setDepart(String depart) {
    this.depart = depart;
  }

  public void setDest(String dest) {
    this.dest = dest;
  }

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

  public void setSrc(String src) {
    this.src = src;
  }

  @Override
  public int getNumber() {
    System.out.println("The flight number is: "+flightNumber);
    return flightNumber;
  }

  @Override
  public String getSource() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    System.out.println("Three letter code of departure airport: "+this.src);
    return this.src;
  }

  @Override
  public String getDepartureString() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    System.out.println("Departure date and time: "+this.depart);
    return this.depart;
  }

  @Override
  public String getDestination() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    System.out.println("Three letter code of arrival airport: "+this.dest);
    return this.dest;
  }

  @Override
  public String getArrivalString() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    System.out.println("Arrival date and time: "+this.arrive);
    return this.arrive;
  }
}

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
    try{
      int flightNum = Integer.parseInt(flightNumber);
      this.flightNumber = flightNum;
    }
    catch (NumberFormatException ex){
      ex.printStackTrace();
    }
  }

  public void setSrc(String src) {
    this.src = src;
  }

  @Override
  public int getNumber() {
    System.out.println(flightNumber);
    return flightNumber;
  }

  @Override
  public String getSource() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    System.out.println(this.src);
    return this.src;
  }

  @Override
  public String getDepartureString() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    System.out.println(this.depart);
    return this.depart;
  }

  @Override
  public String getDestination() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    System.out.println(this.dest);
    return this.dest;
  }

  @Override
  public String getArrivalString() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
    System.out.println(this.arrive);
    return this.arrive;
  }
}

package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.AbstractFlight;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Flight class that contains information of a flight
 */
public class Flight extends AbstractFlight {

  private int flightNumber;
  private String src;
  private String depart;
  private String dest;
  private String arrive;

  /**
   * flight class constructor
   */
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
    this.flightNumber= Integer.parseInt(flightNumber);
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
    if(this.depart == "")
      return "";
    Date date1= new Date(this.depart);
    DateFormat dateformat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    return dateformat.format(date1);
  }

  /**
   * <p>This is a function to get three letter code of arrival airport
   * </p>
   * @return three departure code of arrival airport
   */
  @Override
  public String getDestination() {
//    throw new UnsupportedOperationException("This method is not implemented yet");
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
    if(this.arrive == "")
      return "";
    Date date2= new Date(this.arrive);
    DateFormat dateformat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    return dateformat.format(date2);
  }

  /**
   * Override getDeparture from abstractflight
   * @return date of departure date and time
   */
  @Override
  public Date getDeparture() {
    if(this.depart == "")
      return null;
    Date date1= new Date(this.depart);
    return date1;
  }

  /**
   * Override getArrival from abstractflight
   * @return date of arrival date and time
   */
  @Override
  public java.util.Date getArrival() {
    if(this.arrive == "")
      return null;
    Date date2= new Date(this.arrive);
    return date2;
  }

}

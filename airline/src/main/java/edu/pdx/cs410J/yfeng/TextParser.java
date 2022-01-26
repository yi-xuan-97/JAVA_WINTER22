package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

public class TextParser implements AirlineParser<Airline> {
  private final Reader reader;

  public TextParser(Reader reader) {
    this.reader = reader;
  }

  @Override
  public Airline parse() throws ParserException {
    try (
            BufferedReader br = new BufferedReader(this.reader)
    ) {

      String airlineName = br.readLine();
      String name;
      Flight flight = new Flight();
      String flightinfo[];

      if (airlineName == null) {
        throw new ParserException("Missing airline name");
      }

      if(airlineName.startsWith("\"")){
        String[] airlineNames = airlineName.split("\"");
        name = airlineNames[1];
        String finfo = airlineNames[2];
        flightinfo = finfo.split("\\s+");
      }
      else{
        String[] airlineNames = airlineName.split("\\s+");
        name = airlineNames[0];
        flightinfo = Arrays.copyOfRange(airlineNames, 1, airlineNames.length);
        for(int i=0;i<airlineNames.length;++i){
          System.out.println(airlineNames[i]);
        }
      }
      //Set number to flight
      flight.setFlightNumber(flightinfo[1]);


      //Check if it is three letter code, if so set it to flight
      char[] chars = flightinfo[2].toCharArray();
      for(char code : chars){
        if(!Character.isLetter(code)){
//          System.err.println("Code of departure airport should be three letter");
//          System.exit(1);
        }
      }
      if(flightinfo[2].length()!=3){
//        System.err.println("Code of departure airport should be three letter");
//        System.exit(1);
      }
      else
        flight.setSrc(flightinfo[2]);


      //Check if it is valid date and time, if so set it to flight
      String[] src_date = flightinfo[3].split("/");
      char[] temp1 = flightinfo[3].toCharArray();
      for(char t : temp1){
        if(Character.isLetter(t)){
//          System.err.println("Date and time should all be numeric");
//          System.exit(1);
        }
      }
      if(Integer.parseInt(src_date[0])<0 || Integer.parseInt(src_date[0])>12 ||
              Integer.parseInt(src_date[1])<0 || Integer.parseInt(src_date[1])>31 ||
              src_date[0].length()>2 || src_date[1].length()>2 || src_date[2].length()>4
              || src_date.length>3){
//        System.err.println("Please enter your date using format mm/dd/yyyy,and check if it exist");
//        System.exit(1);
      }


      String[] src_time = flightinfo[4].split(":");
      char[] temp2 = flightinfo[4].toCharArray();
      for(char t : temp2){
        if(Character.isLetter(t)){
//          System.err.println("Date and time should all be numeric");
//          System.exit(1);
        }
      }
      if(Integer.parseInt(src_time[0])<0 || Integer.parseInt(src_time[0])>24||
              Integer.parseInt(src_time[1])<0 || Integer.parseInt(src_time[1])>59 ||
              src_time[0].length()>2 || src_time[1].length()>2
              || src_time.length>2){
//        System.err.println("Please enter your time using format hh/mm,and check if it exist");
//        System.exit(1);
      }
      flight.setDepart(flightinfo[3] +" "+ flightinfo[4]);


      //Check if it's three letter code, if so set it to Dest
      char[] chars1 = flightinfo[5].toCharArray();
      for(char code : chars1){
        if(!Character.isLetter(code)){
//          System.err.println("Code of departure airport should be three letter");
//          System.exit(1);
        }
      }
      if(flightinfo[5].length()!=3){
//        System.err.println("Code of departure airport should be three letter");
//        System.exit(1);
      }
      else
        flight.setDest(flightinfo[5]);


      //Check if date and time is valid, if so then set it to flight
      String[] src_date1 = flightinfo[6].split("/");
      char[] temp3 = flightinfo[6].toCharArray();
      for(char t : temp3){
        if(Character.isLetter(t)){
//          System.err.println("Date and time should all be numeric");
//          System.exit(1);
        }
      }
      if(Integer.parseInt(src_date1[0])<0 || Integer.parseInt(src_date1[0])>12 ||
              Integer.parseInt(src_date1[1])<0 || Integer.parseInt(src_date1[1])>31 ||
              src_date1[0].length()>2 || src_date1[1].length()>2 || src_date1[2].length()>4
              ||src_date1.length>3){
//        System.err.println("Please enter your date using format mm/dd/yyyy,and check if it exist");
//        System.exit(1);
      }

      String[] src_time1 = flightinfo[7].split(":");
      char[] temp4 = flightinfo[7].toCharArray();
      for(char t : temp4){
        if(Character.isLetter(t)){
//          System.err.println("Date and time should all be numeric");
//          System.exit(1);
        }
      }
      if(Integer.parseInt(src_time1[0])<0 || Integer.parseInt(src_time1[0])>24||
              Integer.parseInt(src_time1[1])<0 || Integer.parseInt(src_time1[1])>59 ||
              src_time1[0].length()>2 || src_time1[1].length()>2
              || src_time1.length>2){
//        System.err.println("Please enter your time using format hh/mm,and check if it exist");
//        System.exit(1);
      }
      flight.setArrive(flightinfo[6] +" "+ flightinfo[7]);

      Airline airline = new Airline(name);
      airline.addFlight(flight);

      return airline;

    } catch (IOException e) {
      throw new ParserException("While parsing airline text", e);
    }
  }
}

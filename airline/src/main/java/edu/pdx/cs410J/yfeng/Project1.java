package edu.pdx.cs410J.yfeng;

import com.sun.jdi.connect.Connector;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.IOException;
/**
 * The main class for the CS410J airline Project
 */
public class Project1 {

  public static void main(String[] args) throws IOException {

    //Create new flight
    Flight flight = new Flight();  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    //Check if it's tempty arguement
    if(args.length == 0){
      System.err.println("Missing command line arguments. Please input [options] <args> in command line. [option] could" +
              "be -print and -README. <args> should be the following in the same order airline flightNumber srcletter" +
              "departtime destletter arrivetime");
      System.exit(1);
    }


    //Check first two arguement to determine the start point and check the number of arguements
    int i=0;
    if(args[0].equals("-print") || args[0].equals("-README") && args[1].equals("-print") || args[1].equals("-README")){
      i=2;
      if(args.length < 10){
        System.err.println("Missing command line arguments. Please input [options] <args> in command line. [option] could" +
                "be -print and -README. <args> should be the following in the same order airline flightNumber srcletter" +
                "departtime destletter arrivetime");
        System.exit(1);
      }
      else if(args.length > 10){
        System.err.println("Too much command line arguments. Please input [options] <args> in command line. [option] could" +
                "be -print and -README. <args> should be the following in the same order airline flightNumber srcletter" +
                "departtime destletter arrivetime");
        System.exit(1);
      }
    }
    else if(args[0].equals("-print") || args[0].equals("-README") && (!args[1].equals("-print") && !args[1].equals("-README"))) {
      i = 1;
      if (args.length < 9) {
        System.err.println("Missing command line arguments. Please input [options] <args> in command line. [option] could" +
                "be -print and -README. <args> should be the following in the same order airline flightNumber srcletter" +
                "departtime destletter arrivetime");
        System.exit(1);
      } else if (args.length > 9) {
        System.err.println("Too much command line arguments. Please input [options] <args> in command line. [option] could" +
                "be -print and -README. <args> should be the following in the same order airline flightNumber srcletter" +
                "departtime destletter arrivetime");
        System.exit(1);

      }
    }

    //Create new airline with airline name
    Airline airline = new Airline(args[i]);


    //Set number to flight
    flight.setFlightNumber(args[i+1]);


    //Check if it is three letter code, if so set it to flight
    if(args[i+2].length()!=3){
      System.err.println("Code of departure airport should be three letter");
      System.exit(1);
    }
    else
      flight.setSrc(args[i+2]);




    //Check if it is valid date and time, if so set it to flight
    String[] src_date = args[i+3].split("/");
    if(Integer.parseInt(src_date[0])<0 || Integer.parseInt(src_date[0])>12 ||
            Integer.parseInt(src_date[1])<0 || Integer.parseInt(src_date[1])>31){
      System.err.println("Please enter your date using format mm/dd/yyyy,and check if it exist");
      System.exit(1);
    }


    String[] src_time = args[i+4].split(":");
    if(Integer.parseInt(src_time[0])<0 || Integer.parseInt(src_time[0])>12||
            Integer.parseInt(src_time[1])<0 || Integer.parseInt(src_time[1])>59){
      System.err.println("Please enter your time using format hh/mm,and check if it exist");
      System.exit(1);
    }
    flight.setDepart(args[i+3]+" "+args[i+4]);


    //Check if it's three letter code, if so set it to Dest
    if(args[i+5].length()!=3){
      System.err.println("Code of departure airport should be three letter");
      System.exit(1);
    }
    else
      flight.setDest(args[i+5]);


    //Check if date and time is valid, if so then set it to flight
    String[] src_date1 = args[i+6].split("/");
    if(Integer.parseInt(src_date1[0])<0 || Integer.parseInt(src_date1[0])>12 ||
            Integer.parseInt(src_date1[1])<0 || Integer.parseInt(src_date1[1])>31){
      System.err.println("Please enter your date using format mm/dd/yyyy,and check if it exist");
      System.exit(1);
    }

    String[] src_time1 = args[i+7].split(":");
    if(Integer.parseInt(src_time1[0])<0 || Integer.parseInt(src_time1[0])>12||
            Integer.parseInt(src_time1[1])<0 || Integer.parseInt(src_time1[1])>59){
      System.err.println("Please enter your time using format hh/mm,and check if it exist");
      System.exit(1);
    }
    flight.setArrive(args[i+6]+" "+args[i+7]);



    //Check if the first two is [option], if so, output the information that user want
    for(int j=0; j<2; ++j){
      if(args[j].equals("-print")){
        flight.getNumber();
        flight.getSource();
        flight.getDepartureString();
        flight.getDestination();
        flight.getArrivalString();
      }
      if(args[j].equals("-README")){

        InputStream readme = Project1.class.getResourceAsStream("README.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
        String line = reader.readLine();
        System.out.println(line);
      }
    }

    //Add flight to the airline
    airline.addFlight(flight);
    System.exit(1);



  }
}
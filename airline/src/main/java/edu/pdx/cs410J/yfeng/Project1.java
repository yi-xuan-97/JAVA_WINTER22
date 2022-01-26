package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {

  public static void main(String[] args) throws IOException, ParserException {

    //Create new flight
    Flight flight = new Flight();  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    //Check if it's tempty arguement
    if(args.length == 0){
      System.err.println("Missing command line arguments. Please input [options] <args> in command line. [option] could" +
              "be -print and -README. <args> should be the following in the same order airline flightNumber srcletter" +
              "departtime destletter arrivetime");
      System.exit(1);
    }
    else if(args.length == 1 && args[0].equals("-README")){
      InputStream readme = Project1.class.getResourceAsStream("README.txt");
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String line = reader.readLine();
      System.out.println(line);
      System.exit(0);
    }


    //Check first two arguement to determine the start point and check the number of arguements
    int i=0;

    char element1 = args[0].charAt(0);
    char element2 = args[1].charAt(0);
    char element3 = args[2].charAt(0);
    char element4;

    int index=0;
    boolean checktext = false;
    for(int curr=0;curr<3;++curr){
      if (args[curr].equals("-textFile")){
        index = curr;
        checktext = true;
        i += 2;
      }
      else if(args[curr].charAt(0) == '-'){
        ++i;
      }
    }

    if(args.length < (i+8)){
      System.err.println("Missing command line arguments. Please input [options] <args> in command line. [option] could" +
              "be -print and -README. <args> should be the following in the same order airline flightNumber srcletter" +
              "departtime destletter arrivetime");
      System.exit(1);
    }
    else if(args.length > (i+8)){
      System.err.println("Too much command line arguments. Please input [options] <args> in command line. [option] could " +
              "be -print and -README. <args> should be the following in the same order airline flightNumber srcletter" +
              "departtime destletter arrivetime");
      System.exit(1);
    }



    //Set number to flight
    flight.setFlightNumber(args[i+1]);


    //Check if it is three letter code, if so set it to flight
    char[] chars = args[i+2].toCharArray();
    for(char code : chars){
      if(!Character.isLetter(code)){
        System.err.println("Code of departure airport should be three letter");
        System.exit(1);
      }
    }
    if(args[i+2].length()!=3){
      System.err.println("Code of departure airport should be three letter");
      System.exit(1);
    }
    else
      flight.setSrc(args[i+2]);


    //Check if it is valid date and time, if so set it to flight
    String[] src_date = args[i+3].split("/");
    char[] temp1 = args[i+3].toCharArray();
    for(char t : temp1){
      if(Character.isLetter(t)){
        System.err.println("Date and time should all be numeric");
        System.exit(1);
      }
    }
    if(Integer.parseInt(src_date[0])<0 || Integer.parseInt(src_date[0])>12 ||
            Integer.parseInt(src_date[1])<0 || Integer.parseInt(src_date[1])>31 ||
            src_date[0].length()>2 || src_date[1].length()>2 || src_date[2].length()>4
            || src_date.length>3){
      System.err.println("Please enter your date using format mm/dd/yyyy,and check if it exist");
      System.exit(1);
    }


    String[] src_time = args[i+4].split(":");
    char[] temp2 = args[i+4].toCharArray();
    for(char t : temp2){
      if(Character.isLetter(t)){
        System.err.println("Date and time should all be numeric");
        System.exit(1);
      }
    }
    if(Integer.parseInt(src_time[0])<0 || Integer.parseInt(src_time[0])>24||
            Integer.parseInt(src_time[1])<0 || Integer.parseInt(src_time[1])>59 ||
            src_time[0].length()>2 || src_time[1].length()>2
            || src_time.length>2){
      System.err.println("Please enter your time using format hh/mm,and check if it exist");
      System.exit(1);
    }
    flight.setDepart(args[i+3]+" "+args[i+4]);


    //Check if it's three letter code, if so set it to Dest
    char[] chars1 = args[i+5].toCharArray();
    for(char code : chars1){
      if(!Character.isLetter(code)){
        System.err.println("Code of departure airport should be three letter");
        System.exit(1);
      }
    }
    if(args[i+5].length()!=3){
      System.err.println("Code of departure airport should be three letter");
      System.exit(1);
    }
    else
      flight.setDest(args[i+5]);


    //Check if date and time is valid, if so then set it to flight
    String[] src_date1 = args[i+6].split("/");
    char[] temp3 = args[i+6].toCharArray();
    for(char t : temp3){
      if(Character.isLetter(t)){
        System.err.println("Date and time should all be numeric");
        System.exit(1);
      }
    }
    if(Integer.parseInt(src_date1[0])<0 || Integer.parseInt(src_date1[0])>12 ||
            Integer.parseInt(src_date1[1])<0 || Integer.parseInt(src_date1[1])>31 ||
            src_date1[0].length()>2 || src_date1[1].length()>2 || src_date1[2].length()>4
            ||src_date1.length>3){
      System.err.println("Please enter your date using format mm/dd/yyyy,and check if it exist");
      System.exit(1);
    }

    String[] src_time1 = args[i+7].split(":");
    char[] temp4 = args[i+7].toCharArray();
    for(char t : temp4){
      if(Character.isLetter(t)){
        System.err.println("Date and time should all be numeric");
        System.exit(1);
      }
    }
    if(Integer.parseInt(src_time1[0])<0 || Integer.parseInt(src_time1[0])>24||
            Integer.parseInt(src_time1[1])<0 || Integer.parseInt(src_time1[1])>59 ||
            src_time1[0].length()>2 || src_time1[1].length()>2
            || src_time1.length>2){
      System.err.println("Please enter your time using format hh/mm,and check if it exist");
      System.exit(1);
    }
    flight.setArrive(args[i+6]+" "+args[i+7]);



    //Check if the first two is [option], if so, output the information that user want
    for(int j=0; j<3; ++j){
      if(args[j].equals("-print")){
        flight.getNumber();
        flight.getSource();
        flight.getDepartureString();
        flight.getDestination();
        flight.getArrivalString();
      }
      else if(args[j].equals("-README")){

        InputStream readme = Project1.class.getResourceAsStream("README.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
        String line = reader.readLine();
        System.out.println(line);
      }
      else if(args[j].equals("-textFile")){
        String location = args[j+1].substring(1);
        String currentPath = new java.io.File(".").getCanonicalPath();
        String final_loc = currentPath + "/" + location;
        System.out.println("Current dir:" + final_loc);
        File file = new File(final_loc);
        FileReader filereader = new FileReader(file);
        TextParser textparser = new TextParser(filereader);
        Airline airline = textparser.parse();
        if(!airline.getName().equals(args[i])){
          System.err.println("Please check your airline name to match it with the name with text file");
          System.exit(1);
        }
        //Add flight to the airline
        airline.addFlight(flight);
        ArrayList<Flight> temp = (ArrayList<Flight>) airline.getFlights();
        for(int p=1;p<temp.size();++p){
          temp.get(p).getNumber();
        }

        FileWriter filewriter = new FileWriter(file);
        TextDumper textdumper = new TextDumper(filewriter);
        textdumper.dump(airline);


        System.exit(0);
      }
      else if(args[j].charAt(0)=='-' && !args[j].equals("-print")
              && !args[j].equals("-README") && !args[j].equals("-textFile")){
        System.err.println("Please enter a valid option");
        System.exit(1);
      }
    }

    Airline airline = new Airline(args[i]);
    airline.addFlight(flight);


    System.exit(0);



  }
}
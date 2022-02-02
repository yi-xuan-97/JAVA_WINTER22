package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.AirportNames;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * The main class for the CS410J airline Project
 */
public class Project3 {

  /**
   * The main function
   * @param args Takes argument from command line
   * @throws IOException Have IO expectation during parising or dumping
   * @throws ParserException  Have a parse expectation during parsing
   */
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
      InputStream readme = Project3.class.getResourceAsStream("README.txt");
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String line = reader.readLine();
      System.out.println(line);
      System.exit(0);
    }


    //Check first two arguement to determine the start point and check the number of arguements
    int i=0;

    for(int curr=0;curr<6;++curr){
      if (args[curr].equals("-textFile")){
        i += 2;
      }
      else if(args[curr].equals("-pretty")){
        curr += 1;
        i += 2;
      }
      else if(args[curr].charAt(0) == '-'){
        ++i;
      }
    }

    if(args.length < (i+10)){
      System.err.println("Missing command line arguments. Please input [options] <args> in command line. [option] could" +
              "be -print and -README. <args> should be the following in the same order airline flightNumber srcletter" +
              "departtime destletter arrivetime");
      System.exit(1);
    }
    else if(args.length > (i+10)){
      System.err.println("Too much command line arguments. Please input [options] <args> in command line. [option] could " +
              "be -print and -README. <args> should be the following in the same order airline flightNumber srcletter" +
              "departtime destletter arrivetime");
      System.exit(1);
    }



    //Set number to flight
    char[] ch = args[i+1].toCharArray();
    for(char code : ch){
      if(Character.isLetter(code) ){
        System.err.println("Flight number should be numeric");
        System.exit(1);
      }
    }
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
    else if(AirportNames.getName(args[i+2].toUpperCase())==null){
      System.err.println("Please double check if this airport code exist (src)");
      System.exit(1);
    }
    else
      flight.setSrc(args[i+2].toUpperCase());


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
    if(Integer.parseInt(src_time[0])<0 || Integer.parseInt(src_time[0])>12||
            Integer.parseInt(src_time[1])<0 || Integer.parseInt(src_time[1])>59 ||
            src_time[0].length()>2 || src_time[1].length()>2
            || src_time.length>2){
      System.err.println("Please enter your time using format hh/mm,and check if it exist");
      System.exit(1);
    }

    if(args[i + 5].equalsIgnoreCase("am") || args[i + 5].equalsIgnoreCase("pm")){
      flight.setDepart(args[i+3]+" "+args[i+4] + " " +args[i+5]);
    }
    else{
      System.err.println("Please make sure you enter time as hh/mm am/pm");
      System.exit(0);
    }


    //Check if it's three letter code, if so set it to Dest
    char[] chars1 = args[i+6].toCharArray();
    for(char code : chars1){
      if(!Character.isLetter(code)){
        System.err.println("Code of arrival airport should be three letter");
        System.exit(1);
      }
    }
    if(args[i+6].length()!=3){
      System.err.println("Code of arrival airport should be three letter");
      System.exit(1);
    }
    else if(AirportNames.getName(args[i+6].toUpperCase())==null){
      System.err.println("Please double check if this airport code exist (dest)");
      System.exit(1);
    }
    else
      flight.setDest(args[i+6].toUpperCase());


    //Check if date and time is valid, if so then set it to flight
    String[] src_date1 = args[i+7].split("/");
    char[] temp3 = args[i+7].toCharArray();
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

    String[] src_time1 = args[i+8].split(":");
    char[] temp4 = args[i+8].toCharArray();
    for(char t : temp4){
      if(Character.isLetter(t)){
        System.err.println("Date and time should all be numeric");
        System.exit(1);
      }
    }
    if(Integer.parseInt(src_time1[0])<0 || Integer.parseInt(src_time1[0])>12||
            Integer.parseInt(src_time1[1])<0 || Integer.parseInt(src_time1[1])>59 ||
            src_time1[0].length()>2 || src_time1[1].length()>2
            || src_time1.length>2){
      System.err.println("Please enter your time using format hh/mm,and check if it exist");
      System.exit(1);
    }

    if(args[i + 9].equalsIgnoreCase("am") || args[i + 9].equalsIgnoreCase("pm")){
      flight.setArrive(args[i+7]+" "+args[i+8] + " " +args[i+9]);
    }
    else {
      System.err.println("Please make sure you enter time as hh/mm am/pm");
      System.exit(0);
    }

    Date departuretime = flight.getDeparture();
    Date arrivaltime = flight.getArrival();
    if(departuretime.after(arrivaltime)){
      System.err.println("Please double check your departure time and arrival time. Dearture time should before arrival time");
      System.exit(0);
    }



    Airline airline = new Airline(args[i]);
    airline.addFlight(flight);

    boolean checkpretty = false;
    boolean standardout = false;
    boolean tofile = false;
    String prettyfileloc = "";

    //Check if the first two is [option], if so, output the information that user want
    for(int j=0; j<6; ++j){
      if(args[j].equals("-print")){
        String tempo1 = String.valueOf(flight.getNumber());
        String tempo2 = flight.getSource();
        String tempo3 = flight.getDepartureString();
        String tempo4 = flight.getDestination();
        String tempo5 = flight.getArrivalString();
        System.out.println("The flight number is: "+ tempo1);
        System.out.println("Three letter code of departure airport: "+ tempo2);
        System.out.println("Departure date and time: "+ tempo3);
        System.out.println("Three letter code of arrival airport: "+ tempo4);
        System.out.println("Departure date and time: "+ tempo5);

      }
      else if(args[j].equals("-README")){

        InputStream readme = Project3.class.getResourceAsStream("README.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
        String line = reader.readLine();
        System.out.println(line);
      }
      else if(args[j].equals("-textFile")){
        String location = args[j+1];
        String final_loc;
        boolean flag= false;

        if(!location.endsWith(".txt")){
          String t = location + ".txt";
          location = t;
        }

        File check = new File(location);
        if (check.isAbsolute()){
          final_loc=location;
        }
        else{
          String currentPath = new java.io.File(".").getCanonicalPath();
          final_loc = currentPath + "/" + location;
        }

        if(!check.isDirectory()){
          check = check.getParentFile();
        }
        if(!check.exists()){
          System.err.println("**This is not a valid route to any directory,please make sure enter a valid path");
          System.exit(1);
        }

        File file = new File(final_loc);

        if(!file.exists()){
          file.createNewFile();
          flag = true;
        }


        if(!flag){
          FileReader filereader = new FileReader(file);
          TextParser textparser = new TextParser(filereader);
          Airline airlinetext = textparser.parse();
          if(airlinetext == null){
            System.err.println("The text file is empty, please make sure it contains airline name same as command line");
            System.exit(1);
          }
          if(!airlinetext.getName().equals(args[i])){
            System.err.println("Please check your airline name to match it with the name with text file");
            System.exit(1);
          }
          airline = airlinetext;
        }
        else {
          airline = new Airline(args[i]);
        }

        //Add flight to the airline
        airline.addFlight(flight);

        FileWriter filewriter = new FileWriter(file);
        TextDumper textdumper = new TextDumper(filewriter);
        textdumper.dump(airline);

      }
      else if (args[j].equals("-pretty")){
        checkpretty = true;
        if (args[j+1].equals("-")) {
          standardout = true;
        }
        else {
          tofile = true;

          String location = args[j+1];
          String final_loc;
          boolean flag= false;

          if(!location.endsWith(".txt")){
            String t = location + ".txt";
            location = t;
          }

          File check = new File(location);
          if (check.isAbsolute()){
            final_loc=location;
          }
          else{
            String currentPath = new java.io.File(".").getCanonicalPath();
            final_loc = currentPath + "/" + location;
          }

          if(!check.isDirectory()){
            check = check.getParentFile();
          }
          if(!check.exists()){
            System.err.println("**This is not a valid route to any directory,please make sure enter a valid path");
            System.exit(1);
          }

          prettyfileloc = final_loc;
        }

      }
      else if(args[j].charAt(0)=='-' && !args[j].equals("-print")
              && !args[j].equals("-README") && !args[j].equals("-textFile")
              && !args[j].equals("-pretty")){
        if(!Objects.equals(args[j - 1], "-pretty")){
          System.err.println("Please enter a valid option");
          System.exit(1);
        }
      }
    }

    if(checkpretty){
        if (standardout) {
          String check = airline.getName();
          System.out.println("The name of airline is: " + check + "\n");

          ArrayList<Flight> temp = (ArrayList<Flight>) airline.getFlights();
          for(Flight t: temp){
            String result;

            Date endDate = t.getArrival();
            Date startDate = t.getDeparture();
            long duration  = endDate.getTime() - startDate.getTime();
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);

            result = "Flight number: " + t.getNumber() + "\n"
                    + "Departure airport: " + t.getSource() + "\n"
                    + "Departure date and time: " + t.getDeparture() + "\n"
                    + "Destination airport: " + t.getDestination() + "\n"
                    + "Arrival date and time: " + t.getArrival() + "\n"
                    + "Total duration of this flight: " + diffInMinutes + " min" + "\n";

            System.out.println(result);
          }
        }
        else if(tofile){



          File file = new File(prettyfileloc);

          if(!file.exists()){
            file.createNewFile();
          }


          FileWriter filewriter = new FileWriter(file);
          PrettyPrinter prettyprinter = new PrettyPrinter(filewriter);
          prettyprinter.dump(airline);
        }
      }


    System.exit(0);



  }
}
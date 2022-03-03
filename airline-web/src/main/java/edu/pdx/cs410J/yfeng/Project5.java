package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.AirportNames;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.*;
import java.util.Date;
import java.util.Map;

/**
 * The main class that parses the command line and communicates with the
 * Airline server using REST.
 */
public class Project5 {

    public static final String MISSING_ARGS = "Missing command line arguments";

    /**
     * Main function to handle command line
     * @param args command line argument
     * @throws IOException may throw ioexception
     */
    public static void main(String... args) throws IOException {
        String hostName = null;
        String portString = null;
        String name = null;
        String src = null;
        String dest = null;

        String number = null;
        String depart = null;
        String arrive = null;

        int srccheck = 0;
        int destcheck =0;

        String instruction = "";

        if(args.length == 0){
            System.err.println("Missing command line arguments. Please input [options] <args> in command line. [option] could" +
                    "be -print and -README and -search and -port and -host. <args> should be the following in the same order airline flightNumber srcletter" +
                    "departtime destletter arrivetime");
            System.exit(1);
        }
        else if(args.length == 1 && args[0].equals("-README")){
            printReadme();
            System.exit(0);
        }



        int i=0;
        boolean checksearch = false;
        boolean checkpretty = false;

        for(int curr=0;curr<args.length;++curr){
            if (args[curr].equals("-host")){
                curr += 1;
                i += 2;
                hostName = args[curr];
            }
            else if(args[curr].equals("-port")){
                curr += 1;
                i += 2;
                portString = args[curr];
            }
            else if(args[curr].equals("-search")){
                checksearch = true;
                i += 1;
            }
            else if(args[curr].charAt(0) == '-'){
                ++i;
            }
        }

        if(args.length == (i+1)){
            checkpretty = true;
            name = args[i];
        }

        if (hostName == null) {
            usage( MISSING_ARGS );
            return;

        } else if ( portString == null) {
            usage( "Missing port" );
            return;
        }

        int port;
        try {
            port = Integer.parseInt( portString );

        } catch (NumberFormatException ex) {
            usage("Port \"" + portString + "\" must be an integer");
            return;
        }

        if(checksearch){
            name = args[i];
            src = args[i+1];
            dest = args[i+2];

        }

        if(!checkpretty && !checksearch){
            name = args[i];
            //Set number to flight
            char[] ch = args[i+1].toCharArray();
            for(char code : ch){
                if(Character.isLetter(code) ){
                    System.err.println("Flight number should be numeric");
                    System.exit(1);
                }
            }
            number = args[i+1];


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
                src = args[i+2].toUpperCase();


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
                depart = args[i+3]+" "+args[i+4] + " " +args[i+5];
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
                dest = args[i+6].toUpperCase();


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
                arrive = args[i+7]+" "+args[i+8] + " " +args[i+9];
            }
            else {
                System.err.println("Please make sure you enter time as hh/mm am/pm");
                System.exit(0);
            }

            Date departuretime = new Date(depart);
            Date arrivaltime = new Date(arrive);
            if(departuretime.after(arrivaltime)){
                System.err.println("Please double check your departure time and arrival time. Dearture time should before arrival time");
                System.exit(0);
            }
        }

        for(int j=0; j< args.length; ++j) {
            if (args[j].equals("-print")) {
                System.out.println("The flight number is: " + number);
                System.out.println("Three letter code of departure airport: " + src);
                System.out.println("Departure date and time: " + depart);
                System.out.println("Three letter code of arrival airport: " + dest);
                System.out.println("Departure date and time: " + arrive);
            } else if (args[j].equals("-README")) {
                printReadme();
            }
        }


        AirlineRestClient client = new AirlineRestClient(hostName, port);

        String message = "";
        try {

            if (name != null && src == null && dest == null) {
                Airline airline = client.getAirline(name);
                if(airline.getFlights()==null){
                    System.err.println("There is not such airline");
                    System.exit(0);
                }
                StringWriter sw = new StringWriter();
                PrettyPrinter pretty = new PrettyPrinter(sw);
                pretty.dump(airline);
                message = sw.toString();
            }
            else if (name != null && src != null && dest != null && depart == null && arrive == null && number == null ){
                Airline airline = client.getAirlineFlight(name,src,dest);
                if(airline.getFlights()==null){
                    System.err.println("There is not such airline");
                    System.exit(0);
                }
                StringWriter sw = new StringWriter();
                PrettyPrinter pretty = new PrettyPrinter(sw);
                pretty.dump(airline);
                message = sw.toString();
            }
            else if(name != null && src != null && dest != null && depart != null && arrive != null && number != null) {
                Flight flight = new Flight(Integer.parseInt(number),src,depart,dest,arrive);
                client.addFlight(name,flight);
            }

        } catch (IOException | ParserException ex) {
            error("While contacting server: " + ex);
            return;
        } catch ( HttpRequestHelper.RestException e){
            error(e.getMessage());
            return;
        }

        System.out.println(message);

        System.exit(0);
    }

    /**
     * Error message
     * @param message string of error message
     */
    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);

        System.exit(1);
    }

    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java edu.pdx.cs410J.<login-id>.Project5 [options] <args>" + "\n" +
                "-host hostname Host computer on which the server runs\n" +
                "-port port Port on which the server is listening\n" +
                "-search Search for flights\n" +
                "-print Prints a description of the new flight\n" +
                "-README Prints a README for this project and exits");

    }

    /**
     * Print read me
     */
    public static void printReadme(){
        System.out.println("Project 5 Yixuan Feng -- This project is to add/retrieve airline from both client side and server side" +
                "\n" + "usage: java edu.pdx.cs410J.<login-id>.Project5 [options] <args>" + "\n" +
                "-host hostname Host computer on which the server runs\n" +
                "-port port Port on which the server is listening\n" +
                "-search Search for flights\n" +
                "-print Prints a description of the new flight\n" +
                "-README Prints a README for this project and exits");
    }
}
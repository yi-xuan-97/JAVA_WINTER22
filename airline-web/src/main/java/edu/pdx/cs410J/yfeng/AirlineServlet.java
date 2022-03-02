package edu.pdx.cs410J.yfeng;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.AirportNames;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>Airline</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple dictionary of words
 * and their definitions.
 */
public class AirlineServlet extends HttpServlet {
    static final String AIRLINENAME_PARAMETER = "airline";
    static final String FLIGHTNUMBER_PARAMETER = "flightNumber";
    static final String SRC_PARAMETER = "src";
    static final String DEPART_PARAMETER = "depart";
    static final String DEST_PARAMETER = "dest";
    static final String ARRIVE_PARAMETER = "arrive";

    private final Map<String, String> dictionary = new HashMap<>();
    private final Map<String, Airline> airlines = new HashMap<>();

    /**
     * Handles an HTTP GET request from a client by writing the definition of the
     * airline, all of the airline information are written to the HTTP response.
     * @param request HTTP request
     * @param response HTTP response
     * @throws IOException may throw io exception
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        response.setContentType( "text/plain" );


        String airlineName = getParameter( AIRLINENAME_PARAMETER, request );
        String src = getParameter(SRC_PARAMETER, request);
        String dest = getParameter(DEST_PARAMETER, request);

        if(airlineName == null && src == null && dest == null){
            missingRequiredParameter(response, AIRLINENAME_PARAMETER);
            response.setStatus( HttpServletResponse.SC_PRECONDITION_FAILED );
        }
        else if (airlineName != null && src == null && dest == null) {
            dumpAllAirline(airlineName, response);
        }
        else if(airlineName != null && src != null && dest != null){
            if(AirportNames.getName(src.toUpperCase())==null || AirportNames.getName(dest.toUpperCase())==null){
                airportCodeInvalid(response);
            }
            dumpAirlineFlights(airlineName,src,dest,response);
        }
        else {
//          writeAllDictionaryEntries(response);
            missingRequiredParameter(response, AIRLINENAME_PARAMETER);
        }
    }

    /**
     * Handles an HTTP POST request by storing the dictionary entry for the airline
     * @param request HTTP request
     * @param response HTTP response
     * @throws IOException may throw io exception
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        response.setContentType( "text/plain" );

        String airlineName = getParameter(AIRLINENAME_PARAMETER, request );
        if (airlineName == null) {
            missingRequiredParameter(response, AIRLINENAME_PARAMETER);
            response.setStatus( HttpServletResponse.SC_PRECONDITION_FAILED );
            return;
        }

        String flightNumberString = getParameter(FLIGHTNUMBER_PARAMETER, request );
        if ( flightNumberString == null) {
            missingRequiredParameter( response, FLIGHTNUMBER_PARAMETER );
            response.setStatus( HttpServletResponse.SC_PRECONDITION_FAILED );
            return;
        }

        String srcCode = getParameter(SRC_PARAMETER, request );
        if ( srcCode == null) {
            missingRequiredParameter( response, SRC_PARAMETER );
            response.setStatus( HttpServletResponse.SC_PRECONDITION_FAILED );
            return;
        }

        String depart = getParameter(DEPART_PARAMETER, request );
        if ( depart == null) {
            missingRequiredParameter( response, DEPART_PARAMETER );
            response.setStatus( HttpServletResponse.SC_PRECONDITION_FAILED );
            return;
        }


        String destCode = getParameter(DEST_PARAMETER, request );
        if ( destCode == null) {
            missingRequiredParameter( response, DEST_PARAMETER );
            response.setStatus( HttpServletResponse.SC_PRECONDITION_FAILED );
            return;
        }

        String arrive = getParameter(ARRIVE_PARAMETER, request );
        if ( arrive == null) {
            missingRequiredParameter( response, ARRIVE_PARAMETER );
            response.setStatus( HttpServletResponse.SC_PRECONDITION_FAILED );
            return;
        }

        if(AirportNames.getName(srcCode.toUpperCase())==null || AirportNames.getName(destCode.toUpperCase())==null){
            airportCodeInvalid(response);
        }

        if(checkflightnumber(flightNumberString)){
           numberInvalid(response);
        }

        if(checkDateFormat(depart)==false || checkDateFormat(arrive)==false){
            dateformatInvalid(response);
        }



        Airline airline = getOrCreateAirline(airlineName);
        Flight flight =new Flight(Integer.parseInt(flightNumberString),srcCode,depart,destCode,arrive);
        airline.addFlight(flight);

        this.dictionary.put(airlineName, flightNumberString);



        PrintWriter pw = response.getWriter();
//        pw.println(Messages.definedWordAs(airlineName, flightNumberString));
        pw.println(Messages.Print(flight));
        pw.flush();

        response.setStatus( HttpServletResponse.SC_OK);
    }

    /**
     * Handles an HTTP DELETE request by removing all dictionary entries.  This
     * behavior is exposed for testing purposes only.  It's probably not
     * something that you'd want a real application to expose.
     * @param request HTTP request
     * @param response HTTP response
     * @throws IOException may throw ioexception
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        this.dictionary.clear();

        PrintWriter pw = response.getWriter();
        pw.println(Messages.allDictionaryEntriesDeleted());
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);

    }

    /**
     * Check if flight number valid
     * @param number flight number
     * @return true if flight number invalid
     */
    private boolean checkflightnumber(String number){
        char[] ch = number.toCharArray();
        for(char code : ch){
            if(Character.isLetter(code) ){
                return true;
            }
        }

        return false;
    }

    /**
     * check if date format valid
     * @param date date and time
     * @return true if date valid
     */
    private boolean checkDateFormat(String date){
        String[] times = date.split(" ");


        String[] dates = times[0].split("/");
        char[] temp1 = times[0].toCharArray();
        for(char t : temp1){
            if(Character.isLetter(t)){
                return false;
            }
        }
        if(Integer.parseInt(dates[0])<0 || Integer.parseInt(dates[0])>12 ||
                Integer.parseInt(dates[1])<0 || Integer.parseInt(dates[1])>31 ||
                dates[0].length()>2 || dates[1].length()>2 || dates[2].length()>4
                || dates.length>3){
            return false;
        }


        String[] hour = times[1].split(":");
        char[] temp2 = times[1].toCharArray();
        for(char t : temp2){
            if(Character.isLetter(t)){
                return false;
            }
        }
        if(Integer.parseInt(hour[0])<0 || Integer.parseInt(hour[0])>12||
                Integer.parseInt(hour[1])<0 || Integer.parseInt(hour[1])>59 ||
                hour[0].length()>2 || hour[1].length()>2
                || hour.length>2){
            return false;
        }


        if(times[2].equalsIgnoreCase("am") && times[2].equalsIgnoreCase("pm")){
            return false;
        }

        return true;
    }

    /**
     * Writes an error message about a missing parameter to the HTTP response.
     *
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter( HttpServletResponse response, String parameterName )
            throws IOException
    {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

    /**
     * Check whether airport code is valid
     * @param response HTTP response
     * @throws IOException may throw ioexception
     */
    private void airportCodeInvalid( HttpServletResponse response)
            throws IOException
    {
        String message = Messages.airportCodeInvalid();
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

    /**
     * send error date
     * @param response http response
     * @throws IOException may throw ioexception
     */
    private void dateformatInvalid(HttpServletResponse response)
            throws IOException
    {
        String message = Messages.DateFormatInvalid();
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

    /**
     * send error date
     * @param response http response
     * @throws IOException may throw ioexception
     */
    private void numberInvalid(HttpServletResponse response)
            throws IOException
    {
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Flight number should be numberic");
    }

    /**
     * Writes the definition of the given word to the HTTP response.
     *
     * The text of the message is formatted with {@link TextDumper}
     */
    private void dumpAllAirline(String word, HttpServletResponse response) throws IOException {
        Airline airline = getAirline(word);

        if (airline == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "There is no airline with such information");
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        } else {
            PrintWriter pw = response.getWriter();

            XmlDumper dumper = new XmlDumper(pw);
            dumper.dump(airline);

            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    /**
     * Dump airline with source code and destination code
     * @param airlineName airline name
     * @param src source code
     * @param dest destination code
     * @param response HTTP response
     * @throws IOException may throw ioexception
     */
    private void dumpAirlineFlights(String airlineName, String src, String dest, HttpServletResponse response) throws IOException {
        Airline airline = getAirline(airlineName);

        if (airline == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "There is no airline with such information");
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        } else {
            Airline temp = new Airline(airlineName);
            ArrayList<Flight> flights = (ArrayList<Flight>) airline.getFlights();
            for(Flight t: flights){
                if(Objects.equals(t.getSource(), src) && Objects.equals(t.getDestination(), dest)){
                    temp.addFlight(t);
                }
            }

            PrintWriter pw = response.getWriter();

            XmlDumper dumper = new XmlDumper(pw);
            dumper.dump(temp);

            response.setStatus(HttpServletResponse.SC_OK);
        }
    }


    /**
     * Writes all of the dictionary entries to the HTTP response.
     *
     * The text of the message is formatted with {@link TextDumper}
     */
    private void writeAllDictionaryEntries(HttpServletResponse response ) throws IOException
    {
        PrintWriter pw = response.getWriter();
        TextDumper dumper = new TextDumper(pw);
//        dumper.dump(dictionary);

        response.setStatus( HttpServletResponse.SC_OK );
    }

    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     *         <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
        String value = request.getParameter(name);
        if (value == null || "".equals(value)) {
            return null;

        } else {
            return value;
        }
    }

    /**
     * get airline, if airline not exist create one
     * @param airlineName airline name
     * @return airline object
     */
    @VisibleForTesting
    Airline getOrCreateAirline(String airlineName) {
        Airline airline = getAirline(airlineName);
        if (airline == null) {
            airline = new Airline(airlineName);
            this.airlines.put(airlineName, airline);
        }

        return airline;
    }

    /**
     * get definition
     * @param word word
     * @return word
     */
    @VisibleForTesting
    String getDefinition(String word) {
        return this.dictionary.get(word);
    }

    /**
     * Get airline
     * @param airlineName airline name
     * @return airline object
     */
    private Airline getAirline(String airlineName) {
        return this.airlines.get(airlineName);
    }
}

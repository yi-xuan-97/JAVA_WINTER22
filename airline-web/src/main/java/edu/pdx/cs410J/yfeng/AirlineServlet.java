package edu.pdx.cs410J.yfeng;

import com.google.common.annotations.VisibleForTesting;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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
     * word specified in the "word" HTTP parameter to the HTTP response.  If the
     * "word" parameter is not specified, all of the entries in the dictionary
     * are written to the HTTP response.
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        response.setContentType( "text/plain" );

        String airlineName = getParameter( AIRLINENAME_PARAMETER, request );
        if (airlineName != null) {
            dumpAirline(airlineName, response);

        } else {
//          writeAllDictionaryEntries(response);
            missingRequiredParameter(response, AIRLINENAME_PARAMETER);
        }
    }

    /**
     * Handles an HTTP POST request by storing the dictionary entry for the
     * "word" and "definition" request parameters.  It writes the dictionary
     * entry to the HTTP response.
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        response.setContentType( "text/plain" );

        String airlineName = getParameter(AIRLINENAME_PARAMETER, request );
        if (airlineName == null) {
            missingRequiredParameter(response, AIRLINENAME_PARAMETER);
            return;
        }

        String flightNumberString = getParameter(FLIGHTNUMBER_PARAMETER, request );
        if ( flightNumberString == null) {
            missingRequiredParameter( response, FLIGHTNUMBER_PARAMETER );
            return;
        }

        String srcCode = getParameter(SRC_PARAMETER, request );
        if ( srcCode == null) {
            missingRequiredParameter( response, SRC_PARAMETER );
            return;
        }

        String depart = getParameter(DEPART_PARAMETER, request );
        if ( depart == null) {
            missingRequiredParameter( response, DEPART_PARAMETER );
            return;
        }


        String destCode = getParameter(DEST_PARAMETER, request );
        if ( destCode == null) {
            missingRequiredParameter( response, DEST_PARAMETER );
            return;
        }

        String arrive = getParameter(ARRIVE_PARAMETER, request );
        if ( arrive == null) {
            missingRequiredParameter( response, ARRIVE_PARAMETER );
            return;
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
     * Writes the definition of the given word to the HTTP response.
     *
     * The text of the message is formatted with {@link TextDumper}
     */
    private void dumpAirline(String word, HttpServletResponse response) throws IOException {
        Airline airline = getAirline(word);

        if (airline == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        } else {
            PrintWriter pw = response.getWriter();

//            Map<String, String> wordDefinition = Map.of(word, definition);

//            TextDumper dumper = new TextDumper(pw);
            XmlDumper dumper = new XmlDumper(pw);
            dumper.dump(airline);

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

    @VisibleForTesting
    Airline getOrCreateAirline(String airlineName) {
        Airline airline = getAirline(airlineName);
        if (airline == null) {
            airline = new Airline(airlineName);
            this.airlines.put(airlineName, airline);
        }

        return airline;
    }

    @VisibleForTesting
    String getDefinition(String word) {
        return this.dictionary.get(word);
    }

    private Airline getAirline(String airlineName) {
        return this.airlines.get(airlineName);
    }
}

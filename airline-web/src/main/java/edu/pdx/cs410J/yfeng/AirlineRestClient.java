package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * A helper class for accessing the rest client.  Note that this class provides
 * an example of how to make gets and posts to a URL.  You'll need to change it
 * to do something other than just send dictionary entries.
 */
public class AirlineRestClient extends HttpRequestHelper
{
    private static final String WEB_APP = "airline";
    private static final String SERVLET = "flights";

    private final String url;


    /**
     * Creates a client to the airline REST service running on the given host and port
     * @param hostName The name of the host
     * @param port The port
     */
    public AirlineRestClient( String hostName, int port )
    {
        this.url = String.format( "http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET );
    }

    /**
     * Returns all dictionary entries from the server
     */
    public Map<String, String> getAllDictionaryEntries() throws IOException, ParserException {
        Response response = get(this.url, Map.of());

        TextParser parser = new TextParser(new StringReader(response.getContent()));
//    return parser.parse();
        return null;
    }

    /**
     * Returns the definition for the given word
     */
//    public String getDefinition(String word) throws IOException, ParserException {
//        Response response = get(this.url, Map.of("word", word));
//        throwExceptionIfNotOkayHttpStatus(response);
//        String content = response.getContent();
//
//        TextParser parser = new TextParser(new StringReader(content));
////    return parser.parse().get(word);
//        return null;
//    }
//
//    public void addDictionaryEntry(String word, String definition) throws IOException {
//        Response response = post(this.url, Map.of("word", word, "definition", definition));
//        throwExceptionIfNotOkayHttpStatus(response);
//    }

    /**
     * Remove all dictionary
     * @throws IOException may throw IO expectation
     */
    public void removeAllDictionaryEntries() throws IOException {
        Response response = delete(this.url, Map.of());
        throwExceptionIfNotOkayHttpStatus(response);
    }

    /**
     * Returns the definition for the given airline
     * @return get airline information with airline name
     */
    public Airline getAirline(String airlineName) throws IOException, ParserException {
        Response response = get(this.url, Map.of("airline", airlineName));
        throwExceptionIfNotOkayHttpStatus(response);
        String content = response.getContent();

        XmlParser parser = new XmlParser(new StringReader(content));
        return parser.parse();
    }

    /**
     * Get flights with in airline
     * @param airlineName airline name
     * @param src aiport source code
     * @param dest airport source code
     * @return airline object with specific name, source code and destnation code
     * @throws IOException may throw io exception
     * @throws ParserException may throw parser exveption
     */
    public Airline getAirlineFlight(String airlineName, String src, String dest) throws IOException, ParserException {
        Response response = get(this.url, Map.of("airline", airlineName, "src", src, "dest", dest));
        throwExceptionIfNotOkayHttpStatus(response);
        String content = response.getContent();

        XmlParser parser = new XmlParser(new StringReader(content));
        return parser.parse();
    }

    /**
     * Add a new flight
     * @param airlineName airline name
     * @param flight flight object
     * @throws IOException may throw ioexception
     */
    public void addFlight(String airlineName, Flight flight) throws IOException {
        Response response = post(this.url, Map.of("airline", airlineName, "flightNumber", String.valueOf(flight.getNumber()),
                "src", flight.getSource(), "depart", flight.getDepartureString(),
                "dest", flight.getDestination(),"arrive", flight.getArrivalString()));
        throwExceptionIfNotOkayHttpStatus(response);
    }

    /**
     * Remove all airlines
     * @throws IOException may throw ioexception
     */
    public void removeAllAirlines() throws IOException {
        Response response = delete(this.url, Map.of());
        throwExceptionIfNotOkayHttpStatus(response);
    }

    /**
     * Throw exveption when Http status is not SC_OK
     * @param response HTTP response
     */
    private void throwExceptionIfNotOkayHttpStatus(Response response) {
        int code = response.getCode();
        if (code != HTTP_OK) {
            String message = response.getContent();
            throw new RestException(code, message);
        }
    }

}

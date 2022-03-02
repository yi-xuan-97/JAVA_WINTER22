package edu.pdx.cs410J.yfeng;

/**
 * Class for formatting messages on the server side.  This is mainly to enable
 * test methods that validate that the server returned expected strings.
 */
public class Messages
{
    /**
     * Return message with error information
     * @param parameterName parameter name
     * @return parameter name
     */
    public static String missingRequiredParameter( String parameterName )
    {
        return String.format("The required parameter \"%s\" is missing", parameterName);
    }

    /**
     * Return message with error information
     * @return string of error information
     */
    public static String DateFormatInvalid()
    {
        return "Please enter your time using format mm/dd/yyyy hh/mm am/pm,and check if it exist";
    }


    /**
     * Return message with error information
     * @return string of error information
     */
    public static String airportCodeInvalid()
    {
        return "Please double check if this airport code exist";
    }

    /**
     * return information of airline
     * @param airlineName airline name
     * @param src source code
     * @param dest destination code
     * @return string of error information
     */
    public static String definedWordAs(String airlineName, String src, String dest )
    {
        return String.format( "%s with %s as %s", airlineName, src, dest );
    }

    /**
     * information about flight
     * @param flight flight object
     * @return string of information
     */
    public static String Print(Flight flight)
    {
        String tempo1 = String.valueOf(flight.getNumber());
        String tempo2 = flight.getSource();
        String tempo3 = flight.getDepartureString();
        String tempo4 = flight.getDestination();
        String tempo5 = flight.getArrivalString();
        String result = tempo1 + " " + tempo2 + " " + tempo3 + " " + tempo4 + " " + tempo5 ;
        return result;
    }

    /**
     * delete all entries
     * @return string of information
     */
    public static String allDictionaryEntriesDeleted() {
        return "All dictionary entries have been deleted";
    }

}

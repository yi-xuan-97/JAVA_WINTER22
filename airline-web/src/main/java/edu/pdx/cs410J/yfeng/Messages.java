package edu.pdx.cs410J.yfeng;

/**
 * Class for formatting messages on the server side.  This is mainly to enable
 * test methods that validate that the server returned expected strings.
 */
public class Messages
{
    public static String missingRequiredParameter( String parameterName )
    {
        return String.format("The required parameter \"%s\" is missing", parameterName);
    }

    public static String definedWordAs(String word, String definition )
    {
        return String.format( "Defined %s as %s", word, definition );
    }

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

    public static String allDictionaryEntriesDeleted() {
        return "All dictionary entries have been deleted";
    }

}

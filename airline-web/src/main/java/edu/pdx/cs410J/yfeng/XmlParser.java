package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.AirportNames;
import edu.pdx.cs410J.ParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.Reader;

/**
 * XmlParser class parse the xml file and create an airline object from it
 */
public class XmlParser implements AirlineParser<Airline> {

//    private final String path;
    private final Reader reader;

    /**
     * Class constructor
     * @param reader path of the xml file
     */
    public XmlParser(Reader reader){
        this.reader = reader;
//        this.path = path;
    }

    /**
     * Parse the xml file and make an airline object
     * @return airline object
     * @throws ParserException may throw parser exception
     */
    @Override
    public Airline parse() throws ParserException {
        AirlineXmlHelper helper = new AirlineXmlHelper();

        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        factory.setValidating(true);

        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();
            builder.setErrorHandler(helper);
            builder.setEntityResolver(helper);
            InputSource is = new InputSource(this.reader);
            Document doc = builder.parse(is);
            NodeList list = doc.getElementsByTagName("flight");
            String airlinename = doc.getElementsByTagName("name").item(0).getTextContent();
            Airline airline = new Airline(airlinename);
            for (int i = 0; i < list.getLength(); ++i) {

                Node flight = list.item(i);

                if (flight.getNodeType() == Node.ELEMENT_NODE) {
                    Flight aflight = new Flight();

                    Element element = (Element) flight;

                    String number = element.getElementsByTagName("number").item(0).getTextContent();
                    String src = element.getElementsByTagName("src").item(0).getTextContent();
                    if(AirportNames.getName(src.toUpperCase())==null){
                        System.err.println("Please double check if this airport code exist (xml src)");
                        System.exit(1);
                    }

                    NodeList depart_date = element.getElementsByTagName("date");
                    String day1 = depart_date.item(0).getAttributes().getNamedItem("day").getTextContent();
                    String month1 = depart_date.item(0).getAttributes().getNamedItem("month").getTextContent();
                    String year1 = depart_date.item(0).getAttributes().getNamedItem("year").getTextContent();

                    String day2 = depart_date.item(1).getAttributes().getNamedItem("day").getTextContent();
                    String month2 = depart_date.item(1).getAttributes().getNamedItem("month").getTextContent();
                    String year2 = depart_date.item(1).getAttributes().getNamedItem("year").getTextContent();

                    NodeList depart_time = element.getElementsByTagName("time");
                    String hour1 = depart_time.item(0).getAttributes().getNamedItem("hour").getTextContent();
                    String minute1 = depart_time.item(0).getAttributes().getNamedItem("minute").getTextContent();
                    String period1 = "am";

                    String hour2 = depart_time.item(1).getAttributes().getNamedItem("hour").getTextContent();
                    String minute2 = depart_time.item(1).getAttributes().getNamedItem("minute").getTextContent();
                    String period2 = "am";

                    String dest = element.getElementsByTagName("dest").item(0).getTextContent();
                    if(AirportNames.getName(dest.toUpperCase())==null){
                        System.err.println("Please double check if this airport code exist (xml dest)");
                        System.exit(1);
                    }

                    int h1 = Integer.parseInt(hour1);
                    if (h1 >= 12) {
                        period1 = "pm";
                        if(h1>12){
                            int result = Integer.parseInt(hour1) - 12;
                            hour1 = String.valueOf(result);
                        }
                    }

                    int h2 = Integer.parseInt(hour2);
                    if (h2 >= 12) {
                        period2 = "pm";
                        if(h2 > 12){
                            int result = Integer.parseInt(hour2) - 12;
                            hour2 = String.valueOf(result);
                        }
                    }

                    String departure_time = month1 + "/" + day1 + "/" + year1 + " " + hour1 + ":" + minute1 + " " + period1;
                    String arrive_time = month2 + "/" + day2 + "/" + year2 + " " + hour2 + ":" + minute2 + " " + period2;

                    aflight.setFlightNumber(number);
                    aflight.setSrc(src);
                    aflight.setDepart(departure_time);
                    aflight.setDest(dest);
                    aflight.setArrive(arrive_time);

                    airline.addFlight(aflight);
                }
            }

            return airline;

        } catch (ParserConfigurationException | IOException e) {
            System.err.println("INTERALL ERRO: " + e.getMessage());
        } catch (SAXException e) {
            System.err.println("Please check your xml file, see if it is a valid xml file");
            System.exit(0);
        }



        return null;
    }
}

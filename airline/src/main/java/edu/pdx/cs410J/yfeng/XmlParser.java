package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlParser implements AirlineParser<Airline> {

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
            Document doc = builder.parse("/Users/yixuanfeng/Documents/GitHub/JAVA_WINTER22/airline/src/test/resources/edu/pdx/cs410J/yfeng/valid-airline.xml");
            NodeList list = doc.getElementsByTagName("flight");
            String airlinename = doc.getElementsByTagName("name").item(0).getTextContent();
            Airline airline = new Airline(airlinename);
            for(int i=0; i<list.getLength(); ++i){

                Node flight = list.item(i);

                if(flight.getNodeType()==Node.ELEMENT_NODE){
                    Flight aflight = new Flight();

                    Element element = (Element) flight;

                    String number = element.getElementsByTagName("number").item(0).getTextContent();
                    String src = element.getElementsByTagName("src").item(0).getTextContent();

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

                    if(Integer.parseInt(hour1)>12){
                        period1 = "pm";
                        hour1 = String.valueOf(Integer.parseInt(hour1) - 12);
                    }

                    if(Integer.parseInt(hour2)>12){
                        period1 = "pm";
                        hour1 = String.valueOf(Integer.parseInt(hour2) - 12);
                    }

                    String departure_time = month1 + "/" +day1 + "/" + year1 + " " + hour1 + ":" + minute1 + " " + period1;
                    String arrive_time = month2 + "/" +day2 + "/" + year2 + " " + hour2 + ":" + minute2 + " " + period2;

                    System.out.println(number);
                    System.out.println(src);
                    System.out.println(departure_time);
                    System.out.println(dest);
                    System.out.println(arrive_time);


                    aflight.setFlightNumber(number);
                    aflight.setSrc(src);
                    aflight.setDepart(departure_time);
                    aflight.setDest(dest);
                    aflight.setArrive(arrive_time);

                    System.out.println(aflight.getArrival());
                    System.out.println(aflight.getDeparture());

                    airline.addFlight(aflight);
                }
            }

            return airline;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }



        return null;
    }
}

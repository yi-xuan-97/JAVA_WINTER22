package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.AirlineDumper;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *XmlDumper that dump airline object into xml file
 */
public class XmlDumper implements AirlineDumper<Airline> {

//    private final String path;
    private final Writer writer;

    /**
     * Class constructor
     * @param writer to xml file
     */
    public XmlDumper(Writer writer) {
//        this.path = path;
        this.writer = writer;
    }

    /**
     * Dump airline information into xml file
     * @param airline airline object
     */
    @Override
    public void dump(Airline airline) {
        AirlineXmlHelper helper = new AirlineXmlHelper();
        Document doc = null;
        String systemID = "http://www.cs.pdx.edu/~whitlock/dtds/airline.dtd";
        String publicID = "-//Portland State University//DTD CS410J Airline//EN";

        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            DOMImplementation dom =
                    builder.getDOMImplementation();

            builder.setErrorHandler(helper);
            builder.setEntityResolver(helper);

            DocumentType docType =
                    dom.createDocumentType("phonebook", publicID,systemID);
            doc = dom.createDocument(null, "airline",
                    docType);

            Element root = doc.getDocumentElement();

            String airlinename = airline.getName();
            Element airlinen = doc.createElement("name");
            root.appendChild(airlinen);
            airlinen.appendChild(doc.createTextNode(airlinename));

            ArrayList<Flight> flights = (ArrayList<Flight>) airline.getFlights();

            for(Flight f:flights){
                String num = String.valueOf(f.getNumber());
                String src = f.getSource();
                Date src_date = f.getDeparture();
                Calendar cal = Calendar.getInstance();
                cal.setTime(src_date);
                String src_day = String.valueOf(cal.get(Calendar.DATE));
                String src_month = String.valueOf(cal.get(Calendar.MONTH)+1);
                String src_year = String.valueOf(cal.get(Calendar.YEAR));
                String src_hour = String.valueOf(src_date.getHours());
                String src_min = String.valueOf(src_date.getMinutes());
                String dest = f.getDestination();
                Date dest_date = f.getArrival();
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(dest_date);
                String dest_day = String.valueOf(cal1.get(Calendar.DATE));
                String dest_month = String.valueOf(cal1.get(Calendar.MONTH)+1);
                String dest_year = String.valueOf(cal1.get(Calendar.YEAR));
                String dest_hour = String.valueOf(dest_date.getHours());
                String dest_min = String.valueOf(dest_date.getMinutes());

                Element flight = doc.createElement("flight");
                root.appendChild(flight);

                Element number = doc.createElement("number");
                flight.appendChild(number);
                number.appendChild(doc.createTextNode(num));
                Element src_code = doc.createElement("src");
                flight.appendChild(src_code);
                src_code.appendChild(doc.createTextNode(src));

                Element depart = doc.createElement("depart");
                flight.appendChild(depart);
                Element date1 = doc.createElement("date");
                depart.appendChild(date1);
                date1.setAttribute("day", src_day);
                date1.setAttribute("month", src_month);
                date1.setAttribute("year", src_year);
                Element time1 = doc.createElement("time");
                depart.appendChild(time1);
                time1.setAttribute("hour", src_hour);
                time1.setAttribute("minute",src_min);

                Element dest_code = doc.createElement("dest");
                flight.appendChild(dest_code);
                dest_code.appendChild(doc.createTextNode(dest));

                Element arrival = doc.createElement("arrive");
                flight.appendChild(arrival);
                Element date2 = doc.createElement("date");
                arrival.appendChild(date2);
                date2.setAttribute("day", dest_day);
                date2.setAttribute("month", dest_month);
                date2.setAttribute("year", dest_year);
                Element time2 = doc.createElement("time");
                arrival.appendChild(time2);
                time2.setAttribute("hour", dest_hour);
                time2.setAttribute("minute",dest_min);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transf = transformerFactory.newTransformer();

            transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transf.setOutputProperty(OutputKeys.INDENT, "yes");
            transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transf.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, systemID);

            DOMSource source = new DOMSource(doc);

//            File myFile = new File(path);

//            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(this.writer);

//            transf.transform(source, console);
            transf.transform(source, file);

        } catch (ParserConfigurationException | DOMException | TransformerException ex) {
           System.err.println("INTERNAL ERROR: "+ ex.getMessage());
        }


    }
}

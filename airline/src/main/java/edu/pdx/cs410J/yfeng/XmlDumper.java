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
import java.io.StringWriter;
import java.io.Writer;


public class XmlDumper implements AirlineDumper<Airline> {

    @Override
    public void dump(Airline airline) {
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
            DocumentType docType =
                    dom.createDocumentType("phonebook", publicID,systemID);
            doc = dom.createDocument(null, "airline",
                    docType);

            Element rootElement = doc.createElement("airline");
            doc.appendChild(rootElement);

            String airlinename = airline.getName();

            doc.createElement("name");
            rootElement.appendChild(doc.createElement(airlinename));


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transf = transformerFactory.newTransformer();

            transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transf.setOutputProperty(OutputKeys.INDENT, "yes");
            transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transf.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, systemID);

            DOMSource source = new DOMSource(doc);

            File myFile = new File("/Users/yixuanfeng/Documents/GitHub/JAVA_WINTER22/airline/src/test/resources/edu/pdx/cs410J/yfeng/valid-airline1.xml");

            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(myFile);

            transf.transform(source, console);
            transf.transform(source, file);

        } catch (ParserConfigurationException ex) {
           ex.printStackTrace();
        } catch (DOMException ex) {
            ex.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }
}

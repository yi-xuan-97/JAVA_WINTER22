package edu.pdx.cs410J.yfeng;

import java.io.*;

import edu.pdx.cs410J.ParserException;
import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.sax.*;

/***
 * Converter class that convert text file to xml file
 */
public class Converter {


    /***
     * Main function that convert files
     * @param args command line arguement that reads in path of text file and xml file
     * @throws IOException may throw IOEception
     * @throws ParserException may throw ParserException
     */
    public static void main(String[] args) throws IOException, ParserException {

        if(args.length < 2){
            System.err.println("Missing command line arguments. Please input [options] <args> in command line. [option] could" +
                    "be -print and -README. <args> should be the following in the same order airline flightNumber srcletter" +
                    "departtime destletter arrivetime");
            System.exit(1);
        }

        String location = args[0];
        String locationto = args[1];

        String final_loc;
        String final_loc1;
        boolean flag= false;

        Airline airline = null;

        if(!location.endsWith(".txt")){
            String t = location + ".txt";
            location = t;
        }

        File check = new File(location);
        if (check.isAbsolute()){
            final_loc=location;
        }
        else{
            String currentPath = new java.io.File(".").getCanonicalPath();
            final_loc = currentPath + "/" + location;
        }

        if(!check.isDirectory()){
            check = check.getParentFile();
        }
        if(!check.exists()){
            System.err.println("**This is not a valid route to any directory,please make sure enter a valid path");
            System.exit(1);
        }

        File file = new File(final_loc);

        if(!file.exists()){
            file.createNewFile();
            flag = true;
        }




        if(!locationto.endsWith(".xml")){
            String t = locationto + ".xml";
            locationto = t;
        }

        File check1 = new File(locationto);
        if (check1.isAbsolute()){
            final_loc1=locationto;
        }
        else{
            String currentPath = new java.io.File(".").getCanonicalPath();
            final_loc1 = currentPath + "/" + locationto;
        }

        if(!check.isDirectory()){
            check = check.getParentFile();
        }

        if(!check.exists()){
            System.err.println("**This is not a valid route to any directory,please make sure enter a valid path");
            System.exit(1);
        }




        if(!flag){
            FileReader filereader = new FileReader(file);
            TextParser textparser = new TextParser(filereader);
            airline = textparser.parse();
            if(airline == null){
                System.err.println("The text file is empty, please make sure it contains airline name same as command line");
                System.exit(1);
            }
        }


        FileWriter filewriter = new FileWriter(file);
        XmlDumper xmldumper = new XmlDumper(filewriter);
        xmldumper.dump(airline);

        System.exit(0);
    }

}

package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.ParserException;

import java.io.IOException;

public class Project1 {
    public static void main(String[] args) throws ParserException, IOException {
        Project2.main(args);
        if(args.length == 0){
            System.err.println("Missing command line arguments. Please input [options] <args> in command line. [option] could" +
                    "be -print and -README. <args> should be the following in the same order airline flightNumber srcletter" +
                    "departtime destletter arrivetime");
            System.exit(1);
        }
    }
}

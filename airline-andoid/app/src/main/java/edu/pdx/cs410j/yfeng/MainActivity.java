package edu.pdx.cs410j.yfeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Airline> airline = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = new File(this.getFilesDir(), "airline.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String airlines;
            while ( (airlines = br.readLine()) != null) {

                String [] air = airlines.split("\\|");
                String name = air[0];
                String[] finfo = air[1].split("\\s+");

                Flight flight = new Flight(Integer.parseInt(finfo[0]),finfo[1],finfo[2]+ " " +finfo[3]+ " " +finfo[4]
                        ,finfo[5], finfo[6]+ " " +finfo[7]+ " " +finfo[8]);
                Airline airline = new Airline(name);
                airline.addFlight(flight);


                boolean check = false;
                for (Airline item : MainActivity.airline) {
                    if (item.getName().equals(name)) {
                        item.addFlight(flight);
                        check = true;
                    }
                }

                if (check==false) {
                    MainActivity.airline.add(airline);
                }

            }
        } catch (FileNotFoundException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        Button name = findViewById(R.id.add);
            name.setOnClickListener((view -> {
                Intent intent = new Intent(MainActivity.this, AddAirlineActivity.class);
                startActivity(intent);
            }));

            Button prettyprint = findViewById(R.id.prettyPrint);
            prettyprint.setOnClickListener((view -> {
                Intent intent = new Intent(MainActivity.this, PrettyPrintActivity.class);
                startActivity(intent);
            }));

            Button print = findViewById(R.id.print);
            print.setOnClickListener((view -> {
                Intent intent = new Intent(MainActivity.this, PrintActivity.class);
                startActivity(intent);
            }));

            Button search = findViewById(R.id.search);
            search.setOnClickListener((view -> {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }));

            Button help = findViewById(R.id.help);
            help.setOnClickListener((view -> {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }));

            try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
                for(Airline airline: airline){
                    ArrayList<Flight> temp = (ArrayList<Flight>) airline.getFlights();
                    for(Flight t:temp){
                        String result = "";
                        result = airline.getName() + "|" + t.getNumber()
                                + " " + t.getSource() + " "
                                + t.getDepartureString().replace("$", "").replace(",", "") + " "
                                + t.getDestination() + " "
                                + t.getArrivalString().replace("$", "").replace(",", "");
                        pw.println(result);
                    }
                }

            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }
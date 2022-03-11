package edu.pdx.cs410j.yfeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

import edu.pdx.cs410J.AirportNames;

public class AddAirlineActivity<checkInput> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_airline);

        Button submit = findViewById(R.id.button_submit_add);
        submit.setOnClickListener((view) -> {
            EditText name = findViewById(R.id.input_name);
            EditText number = findViewById(R.id.input_number);
            EditText src = findViewById(R.id.input_src);
            EditText depart = findViewById(R.id.input_departure);
            EditText dest = findViewById(R.id.input_dest);
            EditText arrive = findViewById(R.id.input_arrive);

            String sname = name.getText().toString();
            String snumber = number.getText().toString();
            String ssrc = src.getText().toString().toUpperCase();
            String sdepart = depart.getText().toString();
            String sdest = dest.getText().toString().toUpperCase();
            String sarrive = arrive.getText().toString();

            if (checkInput(snumber, ssrc, sdepart, sdest, sarrive)) {
                Flight flight = new Flight(Integer.valueOf(snumber), ssrc, sdepart, sdest, sarrive);
                Airline airline = new Airline(sname);
                airline.addFlight(flight);

                boolean check = false;
                for (Airline item : MainActivity.airline) {
                    if (item.getName().equals(sname)) {
                        item.addFlight(flight);
                        check = true;
                    }
                }

                if (check==false) {
                    MainActivity.airline.add(airline);
                }

                Toast.makeText(AddAirlineActivity.this, "Adding was successful!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddAirlineActivity.this, MainActivity.class);
                startActivity(intent);
            }


        });

    }

    public boolean checkInput(String number, String src, String depart, String dest, String arrive){
        char[] ch = number.toCharArray();
        for(char code : ch){
            if(Character.isLetter(code) ){
                Toast.makeText(AddAirlineActivity.this, "Flight number", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        char[] chars = src.toCharArray();
        for(char code : chars){
            if(!Character.isLetter(code)){
                Toast.makeText(AddAirlineActivity.this, "Source code", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if(src.length()!=3){
            Toast.makeText(AddAirlineActivity.this, "Source code", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(AirportNames.getName(src.toUpperCase())==null){
            Toast.makeText(AddAirlineActivity.this, "Source code", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(checkDateFormat(depart)==false){
            Toast.makeText(AddAirlineActivity.this, "Depart time", Toast.LENGTH_SHORT).show();
            return false;
        }


        char[] chars1 = dest.toCharArray();
        for(char code : chars1){
            if(!Character.isLetter(code)){
                Toast.makeText(AddAirlineActivity.this, "Dest code", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if(dest.length()!=3){
            Toast.makeText(AddAirlineActivity.this, "Dest code", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(AirportNames.getName(dest.toUpperCase())==null){
            Toast.makeText(AddAirlineActivity.this, "Dest code", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(checkDateFormat(arrive)==false){
            Toast.makeText(AddAirlineActivity.this, "Arrive time", Toast.LENGTH_SHORT).show();
            return false;
        }


        Date departuretime = new Date(depart);
        Date arrivaltime = new Date(arrive);
        if(departuretime.after(arrivaltime)){
            Toast.makeText(AddAirlineActivity.this, "Time sequence", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private static boolean checkDateFormat(String date){
        
        String[] times = date.split(" ");
        if(times.length!=3){
            return false;
        }

        String[] dates = times[0].split("/");
        char[] temp1 = times[0].toCharArray();
        for(char t : temp1){
            if(Character.isLetter(t)){
                return false;
            }
        }
        if(Integer.parseInt(dates[0])<0 || Integer.parseInt(dates[0])>12 ||
                Integer.parseInt(dates[1])<0 || Integer.parseInt(dates[1])>31 ||
                dates[0].length()>2 || dates[1].length()>2 || dates[2].length()>4
                || dates.length>3){
            return false;
        }


        String[] hour = times[1].split(":");
        char[] temp2 = times[1].toCharArray();
        for(char t : temp2){
            if(Character.isLetter(t)){
                return false;
            }
        }
        if(Integer.parseInt(hour[0])<0 || Integer.parseInt(hour[0])>12||
                Integer.parseInt(hour[1])<0 || Integer.parseInt(hour[1])>59 ||
                hour[0].length()>2 || hour[1].length()>2
                || hour.length>2){
            return false;
        }


        if(times[2].equalsIgnoreCase("am") && times[2].equalsIgnoreCase("pm")){
            return false;
        }

        return true;
    }
}
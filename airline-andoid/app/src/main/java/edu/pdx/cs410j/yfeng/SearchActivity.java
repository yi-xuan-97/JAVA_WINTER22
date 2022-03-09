package edu.pdx.cs410j.yfeng;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button search = findViewById(R.id.button_search);
        search.setOnClickListener((view) -> {
            EditText name = findViewById(R.id.getName);
            EditText src = findViewById(R.id.text_source);
            EditText dest = findViewById(R.id.text_dest);
            String sname = name.getText().toString();
            String ssrc = src.getText().toString();
            String sdest = dest.getText().toString();

            String temp = "";
            TextView info = findViewById(R.id.text_search);
            for(Airline airline: MainActivity.airline){
                if(airline.getName().equals(sname)){
                    for (Flight flight:airline.getFlights()){
                        if(flight.getSource().equals(ssrc) && flight.getDestination().equals(sdest)){
                            temp += airline.print();
                        }
                    }
                }
            }
            if (temp.equals("")){
                temp = "There is no such flight";
            }

            info.setText(temp);
        });
    }
}
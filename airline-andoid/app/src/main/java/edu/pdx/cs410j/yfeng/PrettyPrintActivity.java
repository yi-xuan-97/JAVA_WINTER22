package edu.pdx.cs410j.yfeng;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PrettyPrintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pretty_print);

        TextView info = findViewById(R.id.text_prettyprint);
        String temp = "";
        for(Airline airline:MainActivity.airline){
            temp += airline.prettyprint();
        }
        if(temp.equals("")){
            temp="There is no airline";
        }
        info.setText(temp);
    }
}
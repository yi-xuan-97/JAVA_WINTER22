package edu.pdx.cs410j.yfeng;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PrintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        TextView info = findViewById(R.id.text_print);
        String temp = "";
        for(Airline airline: MainActivity.airline){
            temp += airline.print();
        }
        if(temp.equals("")){
            temp="There is no airline";
        }
        info.setText(temp);
    }
}
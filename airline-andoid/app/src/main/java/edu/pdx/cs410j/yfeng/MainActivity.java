package edu.pdx.cs410j.yfeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Airline> airline = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }
} 
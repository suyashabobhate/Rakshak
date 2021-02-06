package com.example.rakshak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainpage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        Button report=findViewById(R.id.report);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((Button)v ).getText().toString().equalsIgnoreCase("Report Crime"))
                {
                    startActivity(new Intent(mainpage.this,reportpage.class));
                }
            }
        });
        Button missing=findViewById(R.id.missing);
        missing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((Button)v ).getText().toString().equalsIgnoreCase("Missing Person"))
                {
                    startActivity(new Intent(mainpage.this,mainpage.class));
                }
            }
        });
        Button searchcrime=findViewById(R.id.searchcrime);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((Button)v ).getText().toString().equalsIgnoreCase("Search for Crimes"))
                {
                    startActivity(new Intent(mainpage.this,mainpage.class));
                }
            }
        });
    }
}
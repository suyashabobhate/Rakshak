package com.example.rakshak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class addmissing extends Activity {

    DatabaseHandler db=new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.missingpage);


        Button add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((Button)v ).getText().toString().equalsIgnoreCase("Add missing person"))
                {
                    startActivity(new Intent(addmissing.this,addmissing.class));
                }
            }
        });
    }
}
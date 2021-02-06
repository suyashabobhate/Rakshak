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

public class missingpage extends Activity {

    DatabaseHandler db=new DatabaseHandler(this);
    List<Crime> missing=new ArrayList<>();
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.missingpage);

        missing=db.getAllCrime("Missing");
        List<String> MissingPeopleList=new ArrayList<>();
        for(Crime crime:missing){
            MissingPeopleList.add(crime.getName());
        }
        //Parcelable state = listview2.onSaveInstanceState();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                MissingPeopleList);
        listview.setAdapter(arrayAdapter);


        Button add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((Button)v ).getText().toString().equalsIgnoreCase("Add missing person"))
                {
                    startActivity(new Intent(missingpage.this,addmissing.class));
                }
            }
        });
    }
}
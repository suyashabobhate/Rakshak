package com.example.rakshak;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signup extends Activity {

    DatabaseHandler db=new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        final EditText nametv=findViewById(R.id.nameedittext);
        final EditText agetv=findViewById(R.id.ageedittext);
        final EditText mobilenotv=findViewById(R.id.mobileedittext);
        final EditText mailidtv=findViewById(R.id.mailedittext);
        final EditText passwordtv=findViewById(R.id.passwordedittext);

        final String nameString=nametv.getText().toString();
        final String ageString=agetv.getText().toString();
        final String mobilenoString=mobilenotv.getText().toString();
        final String mailidString=mailidtv.getText().toString();
        final String password=passwordtv.getText().toString();

        //store

        Button submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameString=nametv.getText().toString();
                final String ageString=agetv.getText().toString();
                final String mobilenoString=mobilenotv.getText().toString();
                final String mailidString=mailidtv.getText().toString();
                final String password=passwordtv.getText().toString();

                if(((Button)v ).getText().toString().equalsIgnoreCase("SUBMIT"))
                {
                    db.addDataToUser(nameString,ageString,mobilenoString,mailidString,password);
                    startActivity(new Intent(signup.this,mainpage.class));
                }
            }
        });
    }
}
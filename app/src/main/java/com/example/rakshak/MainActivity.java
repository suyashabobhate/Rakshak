package com.example.rakshak;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler db=new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nameTextView=findViewById(R.id.nameedit);
        final EditText passwordTextView=findViewById(R.id.passwordedittext);

        final Button loginButton=findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String name= nameTextView.getText().toString();
                final String password=passwordTextView.getText().toString();
                if(((Button)v ).getText().toString().equalsIgnoreCase("LOGIN"))
                {
                    //check database whether name is associated with password if it is then
                    if(db.getDataToVerify(name,password)) {
                        startActivity(new Intent(MainActivity.this, mainpage.class));
                    }
                    else{
                        //alert the name or password is wrong
                        loginButton.setError("The name or password you entered is incorrect. Kindly enter the correct credentials");
                    }
                }
            }
        });

        Button forgotPasswordButton=findViewById(R.id.forgotpassword);
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((Button)v ).getText().toString().equalsIgnoreCase("FORGOT PASSWORD"))
                {
                    //go to different page through mail
                }
            }
        });

        Button signupButton=findViewById(R.id.signup);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((Button)v ).getText().toString().equalsIgnoreCase("SIGN UP"))
                {
                    startActivity(new Intent(MainActivity.this,signup.class));
                }
            }
        });
    }
}
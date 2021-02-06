package com.example.rakshak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class reportpage extends Activity implements AdapterView.OnItemSelectedListener {
        String[] categoryArr = { "Personal Crimes", "Inchoate Crimes", "Statutory Crimes", "Property Crimes", "Financial Crimes"};
        DatabaseHandler db=new DatabaseHandler(this);
        Spinner spin;
        String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportpage);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spin = (Spinner) findViewById(R.id.categoryspinner);
        spin.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categoryArr);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(arrayAdapter);

        ImageView info=findViewById(R.id.info);
        info.setClickable(true);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "Personal crimes are those that result in physical or mental harm to another person eg. assault and battery,child abuse,domestic abuse,kidnapping,rape and statutory rape\n"+
                                "Property crimes typically involve interference with the property of another eg. burglary,larceny,robbery,auto theft,shoplifting\n"+
                                "Inchoate crimes refer to those crimes that were initiated but not completed, and acts that assist in the commission of another crime eg aiding and abetting, attempt, and conspiracy\n"+
                                " Three significant types of statutory crimes are alcohol related crimes, drug crimes, traffic offenses, and financial/white collar crimes\n"+
                                "financial crimes often involve deception or fraud for financial gain eg blackmail, embezzlement and money laundering, tax evasion, and cybercrime.",
                        Toast.LENGTH_LONG).show();
            }
        });

        EditText timetv=findViewById(R.id.time);
        EditText datetv=findViewById(R.id.date);
        EditText summarytextView=findViewById(R.id.summary);
        EditText suspecttextView=findViewById(R.id.suspects);

        final String time=timetv.getText().toString();
        final String date=datetv.getText().toString();
        final String summary=summarytextView.getText().toString();
        final String suspects=suspecttextView.getText().toString();

        //store
        final Button submitButton=findViewById(R.id.signup);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((Button)v ).getText().toString().equalsIgnoreCase("SIGN UP"))
                {
                    db.addDataToReports(category,time,date,summary,suspects);

                }
            }
        });

        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //store category
        category=spin.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
package com.example.togetherwegrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class PlanInput extends AppCompatActivity{
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_input);
        /*initialize dropdown list and buttons*/
        Spinner spworktype = findViewById(R.id.spworktype);
        Spinner spworkload = findViewById(R.id.spworkload);
        Spinner spfreshness = findViewById(R.id.spfreshness);
        Spinner sphour1 = findViewById(R.id.sphour1);
        Spinner sphour2 = findViewById(R.id.sphour2);
        Spinner sphour3 = findViewById(R.id.sphour3);
        Spinner spage = findViewById(R.id.spage);
        Spinner spmost = findViewById(R.id.spmost);
        Spinner spsecond = findViewById(R.id.spsecond);
        Spinner spthird = findViewById(R.id.spthird);
        Spinner spdis = findViewById(R.id.spdis);
        Button iptBTM = findViewById(R.id.inputBTM);

        /*assign content for work type dropdown list*/
        ArrayAdapter<CharSequence> worktypeAdapter = ArrayAdapter.createFromResource(this,R.array.worktype, android.R.layout.simple_spinner_dropdown_item);
        worktypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spworktype.setAdapter(worktypeAdapter);
        spworktype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text = spworktype.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*onclick listener for back to main page*/
        iptBTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMain = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intentToMain);
                finish();
            }
        });
    }


}
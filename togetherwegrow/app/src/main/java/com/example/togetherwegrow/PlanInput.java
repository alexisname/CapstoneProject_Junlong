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
        ArrayAdapter<CharSequence> worktypeAdapter = ArrayAdapter.createFromResource(this,R.array.worktype, R.layout.spinner_layout);
        worktypeAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spworktype.setAdapter(worktypeAdapter);
        spworktype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text = spworktype.getSelectedItem().toString();
                /*TODO
                * send this to db*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*assign content for work load dropdown list*/
        ArrayAdapter<CharSequence> workloadAdapter = ArrayAdapter.createFromResource(this,R.array.workload, R.layout.spinner_layout);
        workloadAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spworkload.setAdapter(workloadAdapter);
        spworkload.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text = spworkload.getSelectedItem().toString();
                /*TODO
                 * send this to db*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign content for energy dropdown list*/
        ArrayAdapter<CharSequence> freshAdapter = ArrayAdapter.createFromResource(this,R.array.fresh, R.layout.spinner_layout);
        freshAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spfreshness.setAdapter(freshAdapter);
        spfreshness.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text = spfreshness.getSelectedItem().toString();
                /*TODO
                 * send this to db*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign content for activityhour dropdown list*/
        ArrayAdapter<CharSequence> hour1Adapter = ArrayAdapter.createFromResource(this,R.array.activityhour, R.layout.spinner_layout);
        hour1Adapter.setDropDownViewResource(R.layout.spinner_layout);
        sphour1.setAdapter(hour1Adapter);
        sphour1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text = sphour1.getSelectedItem().toString();
                /*TODO
                 * send this to db*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign content for activityhour dropdown list*/
        ArrayAdapter<CharSequence> hour2Adapter = ArrayAdapter.createFromResource(this,R.array.activityhour, R.layout.spinner_layout);
        hour2Adapter.setDropDownViewResource(R.layout.spinner_layout);
        sphour2.setAdapter(hour2Adapter);
        sphour2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text = sphour2.getSelectedItem().toString();
                /*TODO
                 * send this to db*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign content for activityhour dropdown list*/
        ArrayAdapter<CharSequence> hour3Adapter = ArrayAdapter.createFromResource(this,R.array.activityhour, R.layout.spinner_layout);
        hour3Adapter.setDropDownViewResource(R.layout.spinner_layout);
        sphour3.setAdapter(hour3Adapter);
        sphour3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text = sphour3.getSelectedItem().toString();
                /*TODO
                 * send this to db*/
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
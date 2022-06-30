package com.example.togetherwegrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class PlanInput extends AppCompatActivity{
    /*fields to be sent to database*/
    private String username;
    private String worktype;
    private String workload;
    private String freshnessafterwork;
    private String activityhour1;
    private String activityhour2;
    private String activityhour3;
    private String childage;
    private String mostpreferred;
    private String secondpreferred;
    private String thirdpreferred;
    private String dislike;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_input);
        /*receive current username and pass it to field username*/
        Intent intent = getIntent();
        username = intent.getStringExtra(Intent.EXTRA_TEXT);
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
        Button iptSMT = findViewById(R.id.inputSMT);

        /*assign content for work type dropdown list*/
        ArrayAdapter<CharSequence> worktypeAdapter = ArrayAdapter.createFromResource(this,R.array.worktype, R.layout.spinner_layout);
        worktypeAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spworktype.setAdapter(worktypeAdapter);
        spworktype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                worktype = spworktype.getSelectedItem().toString();
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
                workload = spworkload.getSelectedItem().toString();
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
                freshnessafterwork = spfreshness.getSelectedItem().toString();
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
                activityhour1 = sphour1.getSelectedItem().toString();
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
                activityhour2 = sphour2.getSelectedItem().toString();
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
                activityhour3 = sphour3.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        /*assign content for age dropdown list*/
        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this,R.array.age, android.R.layout.simple_spinner_dropdown_item);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spage.setAdapter(ageAdapter);
        spage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                childage = spage.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign content for activity 1 dropdown list*/
        ArrayAdapter<CharSequence> mostAdapter = ArrayAdapter.createFromResource(this,R.array.activities, android.R.layout.simple_spinner_dropdown_item);
        mostAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spmost.setAdapter(mostAdapter);
        spmost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mostpreferred = spmost.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign content for activity 2 dropdown list*/
        ArrayAdapter<CharSequence> secondAdapter = ArrayAdapter.createFromResource(this,R.array.activities, android.R.layout.simple_spinner_dropdown_item);
        secondAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spsecond.setAdapter(secondAdapter);
        spsecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                secondpreferred = spsecond.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign content for activity 3 dropdown list*/
        ArrayAdapter<CharSequence> thirdAdapter = ArrayAdapter.createFromResource(this,R.array.activities, android.R.layout.simple_spinner_dropdown_item);
        thirdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spthird.setAdapter(thirdAdapter);
        spthird.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                thirdpreferred = spthird.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign content for disklike dropdown list*/
        ArrayAdapter<CharSequence> disAdapter = ArrayAdapter.createFromResource(this,R.array.activities, android.R.layout.simple_spinner_dropdown_item);
        disAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdis.setAdapter(disAdapter);
        spdis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dislike = spdis.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*calculate points according to input*/


        /*
        add onclick listener to submit input button
        when clicked, call calPoints() method to calculate input
        then redirect to result page, fetch data according to input and display result
         */
        iptSMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if all fields are filled, calculate
                if(!username.equals("") && !worktype.equals("")&& !workload.equals("")&& !freshnessafterwork.equals("")
                        && !activityhour1.equals("")&& !activityhour2.equals("")&& !activityhour3.equals("")
                        && !childage.equals("")&& !mostpreferred.equals("")&& !secondpreferred.equals("")
                        && !thirdpreferred.equals("")&& !dislike.equals("")){
                    InputMatch inputMatch = new InputMatch(worktype,workload,freshnessafterwork,mostpreferred,secondpreferred,thirdpreferred,childage);
                    inputMatch.calPoints();//pass input to inputmatch then calculate
                    //redirect to result page, with calculated points
                    Intent intentSMT = new Intent(getApplicationContext(),ActivityResult.class);
                    intentSMT.putExtra("energy",inputMatch.getEnergy());
                    intentSMT.putExtra("fresh",inputMatch.getFreshness());
                    startActivity(intentSMT);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        /*
//        add onclick listener to submit input button
//        it checks if all fields are filled
//        and POST to database
//        if succeed, it shows submit succeeds
//        else shows error message
//         */
//        iptSMT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /*check if all fields are filled*/
//                if(!username.equals("") && !worktype.equals("")&& !workload.equals("")&& !freshnessafterwork.equals("")
//                        && !activityhour1.equals("")&& !activityhour2.equals("")&& !activityhour3.equals("")
//                        && !childage.equals("")&& !mostpreferred.equals("")&& !secondpreferred.equals("")
//                        && !thirdpreferred.equals("")&& !dislike.equals("")){
//                    /*handler to open a thread to pass the message to database*/
//                    Handler handler = new Handler(Looper.getMainLooper());
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            /*put data input array and write to database*/
//                            String[] field = new String[12];
//                            field[0] = "username";
//                            field[1] = "worktype";
//                            field[2] = "workload";
//                            field[3] = "freshnessafterwork";
//                            field[4] = "activityhour1";
//                            field[5] = "activityhour2";
//                            field[6] = "activityhour3";
//                            field[7] = "childage";
//                            field[8] = "mostpreferred";
//                            field[9] = "secondpreferred";
//                            field[10] = "thirdpreferred";
//                            field[11] = "dislike";
//                            String[] data = new String[12];
//                            data[0] = username;
//                            data[1] = worktype;
//                            data[2] = workload;
//                            data[3] = freshnessafterwork;
//                            data[4] = activityhour1;
//                            data[5] = activityhour2;
//                            data[6] = activityhour3;
//                            data[7] = childage;
//                            data[8] = mostpreferred;
//                            data[9] = secondpreferred;
//                            data[10] = thirdpreferred;
//                            data[11] = dislike;
//                            /*use SendData helper for url connection and IO stream
//                             * check the returned result on completion of data sending
//                             */
//                            SendData sendData = new SendData("http://10.0.0.146/TogetherWeGrow/input.php","POST",field,data);
//                            if(sendData.startSend()){
//                                if(sendData.onComplete()){
//                                    String result = sendData.getResult();
//                                    if(result.equals("Your input has been saved")){ //if log in succeed, redirect to welcome home page
//                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
//                                    }
//                                    else {//show error message to user
//                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            }
//                        }
//                    });
//                }
//                else {//if user leaves a field empty
//                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

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
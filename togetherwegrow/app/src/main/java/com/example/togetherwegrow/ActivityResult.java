package com.example.togetherwegrow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ActivityResult extends AppCompatActivity {
    /*specify fields and ui in xml*/
    double energy;
    double fresh;
    int childage;
    int hour1;
    int minute1;
    int hour2;
    int minute2;
    int hour3;
    int minute3;
    int alarmDay;
    String username;
    String activityOne;
    String activityTwo;
    String activityThree;
    String selectDay;
    String dislike;
    ArrayList<Integer> alarmDays;
    TextView clickForRes;
    TextView activityColumnOne;
    TextView activityColumnTwo;
    TextView activityColumnThree;
    String[] retrievedActivity;
    Button btnBack;
    Button btnConfirm;
    Button btnShuffleOne;
    Button btnShuffleTwo;
    Button btnShuffleThree;
    Button btnSetOne;
    Button btnSetTwo;
    Button btnSetThree;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        /*initialize fields*/
        Intent intent = getIntent(); //get calculated result from
        energy = intent.getDoubleExtra("energy",0);
        fresh = intent.getDoubleExtra("fresh",0);
        childage = intent.getIntExtra("age",0);
        hour1 = intent.getIntExtra("hour1",0);
        minute1 = intent.getIntExtra("minute1",0);
        hour2 = intent.getIntExtra("hour2",0);
        minute2 = intent.getIntExtra("minute2",0);
        hour3 = intent.getIntExtra("hour3",0);
        minute3 = intent.getIntExtra("minute3",0);
        username = intent.getStringExtra("username");
        selectDay = intent.getStringExtra("selectDay");
        dislike = intent.getStringExtra("dislike");
        //listResult = (ListView) findViewById(R.id.listResult);
        clickForRes = findViewById(R.id.clickRes);
        btnBack = findViewById(R.id.btnBack);
        btnConfirm = findViewById(R.id.btnConfirm);
        activityColumnOne = findViewById(R.id.activityOne);
        activityColumnTwo = findViewById(R.id.activityTwo);
        activityColumnThree = findViewById(R.id.activityThree);
        btnShuffleOne = findViewById(R.id.btnShuffleOne);
        btnShuffleTwo = findViewById(R.id.btnShuffleTwo);
        btnShuffleThree = findViewById(R.id.btnShuffleThree);
        btnSetOne = findViewById(R.id.btnSetOne);
        btnSetTwo = findViewById(R.id.btnSetTwo);
        btnSetThree = findViewById(R.id.btnSetThree);
        alarmDays = new ArrayList<>();


        /*retrieve result from db and store them to string*/
        retrieveData(energy,fresh,childage);
        /*randomly pick activities from result and set text to display*/
        clickForRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectDay.equals("Monday")){
                    alarmDay = Calendar.MONDAY;
                }
                if(selectDay.equals("Tuesday")){
                    alarmDay = Calendar.TUESDAY;
                }
                if(selectDay.equals("Wednesday")){
                    alarmDay = Calendar.WEDNESDAY;
                }
                if(selectDay.equals("Thursday")){
                    alarmDay = Calendar.TUESDAY;
                }
                if(selectDay.equals("Friday")){
                    alarmDay = Calendar.FRIDAY;
                }
                if(selectDay.equals("Saturday")){
                    alarmDay = Calendar.SATURDAY;
                }
                if(selectDay.equals("Sunday")){
                    alarmDay = Calendar.SUNDAY;
                }

                /*according to number of time selected by user, randomly assign result for display*/
                if(hour2>=0 && hour3>=0){
                    int numOfRetrieved = retrievedActivity.length;
                    int rdm1 = (int)((Math.random()*numOfRetrieved));
                    int rdm2 = (int)((Math.random()*numOfRetrieved));
                    int rdm3 = (int)((Math.random()*numOfRetrieved));
                    String minuteOne = minute1<10?"0"+String.valueOf(minute1):String.valueOf(minute1);
                    String minuteTwo = minute2<10?"0"+String.valueOf(minute2):String.valueOf(minute2);
                    String minuteThree = minute3<10?"0"+String.valueOf(minute3):String.valueOf(minute3);
                    activityOne = retrievedActivity[rdm1];
                    activityTwo = retrievedActivity[rdm2];
                    activityThree = retrievedActivity[rdm3];
                    activityColumnOne.setText(activityOne+" At: "+String.valueOf(hour1)+" : "+minuteOne);
                    activityColumnTwo.setText(activityTwo+" At: "+String.valueOf(hour2)+" : "+minuteTwo);
                    activityColumnThree.setText(activityThree+" At: "+String.valueOf(hour3)+" : "+minuteThree);
                }
                else if(hour2>=0){
                    int numOfRetrieved = retrievedActivity.length;
                    int rdm1 = (int)((Math.random()*numOfRetrieved));
                    int rdm2 = (int)((Math.random()*numOfRetrieved));
                    String minuteOne = minute1<10?"0"+String.valueOf(minute1):String.valueOf(minute1);
                    String minuteTwo = minute2<10?"0"+String.valueOf(minute2):String.valueOf(minute2);
                    activityOne = retrievedActivity[rdm1];
                    activityTwo = retrievedActivity[rdm2];
                    activityColumnOne.setText(activityOne+" At: "+String.valueOf(hour1)+" : "+minuteOne);
                    activityColumnTwo.setText(activityTwo+" At: "+String.valueOf(hour2)+" : "+minuteTwo);
                    activityColumnThree.setText("Time has not been selected");
                }
                else{
                    int numOfRetrieved = retrievedActivity.length;
                    int rdm1 = (int)((Math.random()*numOfRetrieved));
                    String minuteOne = minute1<10?"0"+String.valueOf(minute1):String.valueOf(minute1);
                    activityOne = retrievedActivity[rdm1];
                    activityColumnOne.setText(activityOne+" At: "+String.valueOf(hour1)+" : "+minuteOne);
                    activityColumnTwo.setText("Time has not been selected");
                    activityColumnThree.setText("Time has not been selected");
                }
            }
        });

        /*shuffle click for each activity so that user can change if they don't like a particular one*/
        btnShuffleOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hour1>=0){
                    int numOfRetrieved = retrievedActivity.length;
                    int rdm = (int)((Math.random()*numOfRetrieved));
                    String minuteOne = minute1<10?"0"+String.valueOf(minute1):String.valueOf(minute1);
                    activityOne = retrievedActivity[rdm];
                    activityColumnOne.setText(activityOne+" At: "+String.valueOf(hour1)+" : "+minuteOne);
                }
            }
        });
        btnShuffleTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hour2>=0){
                    int numOfRetrieved = retrievedActivity.length;
                    int rdm = (int)((Math.random()*numOfRetrieved));
                    String minuteTwo = minute2<10?"0"+String.valueOf(minute2):String.valueOf(minute2);
                    activityTwo = retrievedActivity[rdm];
                    activityColumnTwo.setText(activityTwo+" At: "+String.valueOf(hour2)+" : "+minuteTwo);
                }
            }
        });
        btnShuffleThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hour3>=0){
                    int numOfRetrieved = retrievedActivity.length;
                    int rdm = (int)((Math.random()*numOfRetrieved));
                    String minuteThree = minute3<10?"0"+String.valueOf(minute3):String.valueOf(minute3);
                    activityThree = retrievedActivity[rdm];
                    activityColumnThree.setText(activityThree+" At: "+String.valueOf(hour3)+" : "+minuteThree);
                }
            }
        });

        /*set alarm for each activity*/
        btnSetOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hour1>=0){
                    Intent intentAlarmOne = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intentAlarmOne.putExtra(AlarmClock.EXTRA_HOUR, hour1);
                    intentAlarmOne.putExtra(AlarmClock.EXTRA_MINUTES, minute1);
                    intentAlarmOne.putExtra(AlarmClock.EXTRA_MESSAGE, activityOne);
                    if(alarmDay>0){
                        alarmDays.add(alarmDay);
                        intentAlarmOne.putExtra(AlarmClock.EXTRA_DAYS, alarmDays);
                    }
                    intentAlarmOne.putExtra(AlarmClock.EXTRA_SKIP_UI,true);
                    startActivity(intentAlarmOne);
                }
            }
        });
        btnSetTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hour2>=0){

                    Intent intentAlarmTwo = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intentAlarmTwo.putExtra(AlarmClock.EXTRA_HOUR, hour2);
                    intentAlarmTwo.putExtra(AlarmClock.EXTRA_MINUTES, minute2);
                    if(alarmDay>0){
                        alarmDays.add(alarmDay);
                        intentAlarmTwo.putExtra(AlarmClock.EXTRA_DAYS, alarmDays);
                    }
                    intentAlarmTwo.putExtra(AlarmClock.EXTRA_MESSAGE, activityTwo);
                    intentAlarmTwo.putExtra(AlarmClock.EXTRA_SKIP_UI,true);
                    startActivity(intentAlarmTwo);
                }
            }
        });
        btnSetThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hour3>=0){
                    Intent intentAlarmThree = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intentAlarmThree.putExtra(AlarmClock.EXTRA_HOUR, hour3);
                    intentAlarmThree.putExtra(AlarmClock.EXTRA_MINUTES, minute3);
                    intentAlarmThree.putExtra(AlarmClock.EXTRA_MESSAGE, activityThree);
                    if(alarmDay>0){
                        alarmDays.add(alarmDay);
                        intentAlarmThree.putExtra(AlarmClock.EXTRA_DAYS, alarmDays);
                    }
                    intentAlarmThree.putExtra(AlarmClock.EXTRA_SKIP_UI,true);
                    startActivity(intentAlarmThree);
                }
            }
        });


        /*set onclick listener for back button*/
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(getApplicationContext(),PlanInput.class);
                intentBack.putExtra("username",username);
                startActivity(intentBack);
                finish();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentConfirm = new Intent(getApplicationContext(),StatusTrack.class);
                if(activityOne!=null){
                    intentConfirm.putExtra("activityOne",activityOne);
                }
                if(activityTwo!=null){
                    intentConfirm.putExtra("activityTwo",activityTwo);
                }
                if(activityThree!=null){
                    intentConfirm.putExtra("activityThree",activityThree);
                }
                startActivity(intentConfirm);
                finish();
            }
        });

    }


    /*function to retrieve data via GET method*/
    public void retrieveData(double energy, double fresh, int childage){
        RequestQueue queue = Volley.newRequestQueue(this);
        //convert double to string to pass to url for GET method
        String energyRetrieve = String.valueOf(energy);
        String freshRetrieve = String.valueOf(fresh);
        String ageRetrieve = String.valueOf(childage);
        String url = "http://10.0.0.74/TogetherWeGrow/findActivity.php?energy="+energyRetrieve+ "&fresh=" + freshRetrieve+ "&age=" + ageRetrieve+"&dislike=" + dislike;
        //GET request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(ActivityResult.this, "responded", Toast.LENGTH_SHORT).show();
                        Log.e("", response);
                        parseJson(response);//use parseJson method to retrieve data from json
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityResult.this, "error", Toast.LENGTH_SHORT).show();
//                textView.setText("not responding");
            }
        });
        queue.add(stringRequest);
    }

    /*method to display data in jason*/
    public void parseJson(String response){
        try {
            JSONArray jsonArray = new JSONArray(response);
            retrievedActivity = new String[jsonArray.length()];
            for(int i=0; i<jsonArray.length(); i++){//for each item in that jsonArray, get desired data
                String activityVar;
//                double energyVar;
//                double freshVar;
//                int ageVar;
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.e("activity: ",jsonObject.getString("activity"));
                activityVar = jsonObject.getString("activity");
                retrievedActivity[i]=activityVar;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
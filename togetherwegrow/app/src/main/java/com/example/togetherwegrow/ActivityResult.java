package com.example.togetherwegrow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
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
    int rdm1;
    int rdm2;
    int rdm3;
    String username;
    String activityOne;
    String activityTwo;
    String activityThree;
    String selectDay;
    String dislike;
    String email;
    ArrayList<Integer> alarmDays;
    TextView clickForRes;
    TextView activityColumnOne;
    TextView activityColumnTwo;
    TextView activityColumnThree;
    TextView description;
    String[] retrievedActivity;
    String[] activityDescription;
    Button btnBack;
    Button btnBtm;
    Button btnShuffleOne;
    Button btnShuffleTwo;
    Button btnShuffleThree;
    Button btnSetOne;
    Button btnSetTwo;
    Button btnSetThree;
    Button btnCalOne;
    Button btnCalTwo;
    Button btnCalThree;




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
        email = intent.getStringExtra("email");
        //listResult = (ListView) findViewById(R.id.listResult);
        clickForRes = findViewById(R.id.clickRes);
        btnBack = findViewById(R.id.btnBack);
        btnBtm = findViewById(R.id.btnBtm);
        activityColumnOne = findViewById(R.id.activityOne);
        activityColumnTwo = findViewById(R.id.activityTwo);
        activityColumnThree = findViewById(R.id.activityThree);
        btnShuffleOne = findViewById(R.id.btnShuffleOne);
        btnShuffleTwo = findViewById(R.id.btnShuffleTwo);
        btnShuffleThree = findViewById(R.id.btnShuffleThree);
        btnSetOne = findViewById(R.id.btnSetOne);
        btnSetTwo = findViewById(R.id.btnSetTwo);
        btnSetThree = findViewById(R.id.btnSetThree);
        btnCalOne = findViewById(R.id.btnCalOne);
        btnCalTwo = findViewById(R.id.btnCalTwo);
        btnCalThree = findViewById(R.id.btnCalThree);
        description = findViewById(R.id.description);
        alarmDays = new ArrayList<>();
        Log.e("",email);


        /*retrieve result from db and store them to string*/
        retrieveData(energy,fresh,childage);
        /*randomly pick activities from result and set text to display*/
        clickForRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("before click retrieve: "+SystemClock.currentThreadTimeMillis());
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
                    rdm1 = (int)((Math.random()*numOfRetrieved));
                    rdm2 = (int)((Math.random()*numOfRetrieved));
                    rdm3 = (int)((Math.random()*numOfRetrieved));
                    String minuteOne = minute1<10?"0"+String.valueOf(minute1):String.valueOf(minute1);
                    String minuteTwo = minute2<10?"0"+String.valueOf(minute2):String.valueOf(minute2);
                    String minuteThree = minute3<10?"0"+String.valueOf(minute3):String.valueOf(minute3);
                    activityOne = retrievedActivity[rdm1];
                    activityTwo = retrievedActivity[rdm2];
                    activityThree = retrievedActivity[rdm3];
                    Log.e("rdm1: ",Integer.toString(rdm1));
                    activityColumnOne.setText(activityOne+" At: "+String.valueOf(hour1)+" : "+minuteOne);
                    activityColumnTwo.setText(activityTwo+" At: "+String.valueOf(hour2)+" : "+minuteTwo);
                    activityColumnThree.setText(activityThree+" At: "+String.valueOf(hour3)+" : "+minuteThree);
                    System.out.println("after retrieve: "+SystemClock.currentThreadTimeMillis());
                }
                else if(hour2>=0){
                    int numOfRetrieved = retrievedActivity.length;
                    rdm1 = (int)((Math.random()*numOfRetrieved));
                    rdm2 = (int)((Math.random()*numOfRetrieved));
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
                    rdm1 = (int)((Math.random()*numOfRetrieved));
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
                    rdm1 = (int)((Math.random()*numOfRetrieved));
                    String minuteOne = minute1<10?"0"+String.valueOf(minute1):String.valueOf(minute1);
                    activityOne = retrievedActivity[rdm1];
                    activityColumnOne.setText(activityOne+" At: "+String.valueOf(hour1)+" : "+minuteOne);
                }
            }
        });
        btnShuffleTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hour2>=0){
                    int numOfRetrieved = retrievedActivity.length;
                    rdm2 = (int)((Math.random()*numOfRetrieved));
                    String minuteTwo = minute2<10?"0"+String.valueOf(minute2):String.valueOf(minute2);
                    activityTwo = retrievedActivity[rdm2];
                    activityColumnTwo.setText(activityTwo+" At: "+String.valueOf(hour2)+" : "+minuteTwo);
                }
            }
        });
        btnShuffleThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hour3>=0){
                    int numOfRetrieved = retrievedActivity.length;
                    rdm3 = (int)((Math.random()*numOfRetrieved));
                    String minuteThree = minute3<10?"0"+String.valueOf(minute3):String.valueOf(minute3);
                    activityThree = retrievedActivity[rdm3];
                    activityColumnThree.setText(activityThree+" At: "+String.valueOf(hour3)+" : "+minuteThree);
                }
            }
        });

        /*set alarm for each activity*/
        btnSetOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!activityOne.equals("")){
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
                if(!activityTwo.equals("")){

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
                if(!activityThree.equals("")){
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

        /*set onclick listener for setting calendar events*/
        btnCalOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!activityOne.equals("")){
                    Intent intentCalOne = new Intent(Intent.ACTION_INSERT);
                    intentCalOne.setData(CalendarContract.Events.CONTENT_URI);
                    intentCalOne.putExtra(CalendarContract.Events.TITLE, activityOne);
                    if(!email.equals("")){
                        intentCalOne.putExtra(Intent.EXTRA_EMAIL,email);
                    }
                    if(intentCalOne.resolveActivity((getPackageManager()))!=null){
                        startActivity(intentCalOne);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "There is no app that can support this action",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnCalTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!activityTwo.equals("")){
                    Intent intentCalOne = new Intent(Intent.ACTION_INSERT);
                    intentCalOne.setData(CalendarContract.Events.CONTENT_URI);
                    intentCalOne.putExtra(CalendarContract.Events.TITLE, activityTwo);
                    if(!email.equals("")){
                        intentCalOne.putExtra(Intent.EXTRA_EMAIL,email);
                    }
                    if(intentCalOne.resolveActivity((getPackageManager()))!=null){
                        startActivity(intentCalOne);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "There is no app that can support this action",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnCalThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!activityThree.equals("")){
                    Intent intentCalOne = new Intent(Intent.ACTION_INSERT);
                    intentCalOne.setData(CalendarContract.Events.CONTENT_URI);
                    intentCalOne.putExtra(CalendarContract.Events.TITLE, activityThree);
                    if(!email.equals("")){
                        intentCalOne.putExtra(Intent.EXTRA_EMAIL,email);
                    }
                    if(intentCalOne.resolveActivity((getPackageManager()))!=null){
                        startActivity(intentCalOne);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "There is no app that can support this action",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        activityColumnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityOne!=null){
                    Log.e("rdm1 description: ",Integer.toString(rdm1));
                    Log.e("description: ",activityDescription[rdm1]);
                    String des = activityDescription[rdm1];
                    description.setText(des);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Activity So Far",Toast.LENGTH_SHORT).show();
                }
            }
        });
        activityColumnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityTwo!=null){
                    description.setText(activityDescription[rdm2]);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Activity So Far",Toast.LENGTH_SHORT).show();
                }
            }
        });
        activityColumnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityThree!=null){
                    description.setText(activityDescription[rdm3]);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Activity So Far",Toast.LENGTH_SHORT).show();
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
        /*set onclick listener for back to main button*/
        btnBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBtm = new Intent(getApplicationContext(),MainActivity.class);
//                if(activityOne!=null){
//                    intentConfirm.putExtra("activityOne",activityOne);
//                }
//                if(activityTwo!=null){
//                    intentConfirm.putExtra("activityTwo",activityTwo);
//                }
//                if(activityThree!=null){
//                    intentConfirm.putExtra("activityThree",activityThree);
//                }
                intentBtm.putExtra("username",username);
                startActivity(intentBtm);
                finish();
            }
        });
        System.out.println("result on create: "+SystemClock.currentThreadTimeMillis());

    }


    /*function to retrieve data via GET method*/
    public void retrieveData(double energy, double fresh, int childage){
        RequestQueue queue = Volley.newRequestQueue(this);
        //convert double to string to pass to url for GET method
        String energyRetrieve = String.valueOf(energy);
        String freshRetrieve = String.valueOf(fresh);
        String ageRetrieve = String.valueOf(childage);
        String url = "http://35.183.174.53/TogetherWeGrow/findActivity.php?energy="+energyRetrieve+ "&fresh=" + freshRetrieve+ "&age=" + ageRetrieve+"&dislike=" + dislike;
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
            activityDescription = new String[jsonArray.length()];
            for(int i=0; i<jsonArray.length(); i++){//for each item in that jsonArray, get desired data
                String activityVar;
//                double energyVar;
//                double freshVar;
//                int ageVar;
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //Log.e("activity: ",jsonObject.getString("activity"));
                activityVar = jsonObject.getString("activity");
                retrievedActivity[i]=activityVar;
                activityDescription[i] = jsonObject.getString("Description");
                //Log.e("description: ",activityDescription[i]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
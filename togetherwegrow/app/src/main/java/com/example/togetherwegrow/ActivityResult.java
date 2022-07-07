package com.example.togetherwegrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

public class ActivityResult extends AppCompatActivity {
    double energy;
    double fresh;
    int childage;
    int hour1;
    int minute1;
    int hour2;
    int minute2;
    int hour3;
    int minute3;
    TextView clickForRes;
    Button btnRes;
    String[] retrievedActivity;
    ArrayList<String> displayActivity;

    ListView listResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

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
        listResult = (ListView) findViewById(R.id.listResult);
        clickForRes = findViewById(R.id.clickRes);
        displayActivity = new ArrayList<>();


        /*retrieve result from db and store them to string*/
        retrieveData(energy,fresh,childage);

        clickForRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*according to number of time selected by user, randomly assign result for display*/
        if(hour2>=0 && hour3>=0){
            int numOfRetrieved = retrievedActivity.length;
            int rdm1 = (int)((Math.random()*numOfRetrieved));
            int rdm2 = (int)((Math.random()*numOfRetrieved));
            int rdm3 = (int)((Math.random()*numOfRetrieved));
            displayActivity.add(retrievedActivity[rdm1]+" Time is: "+String.valueOf(hour1)+" : "+String.valueOf(minute1));
            displayActivity.add(retrievedActivity[rdm2]+" Time is: "+String.valueOf(hour2)+" : "+String.valueOf(minute2));
            displayActivity.add(retrievedActivity[rdm3]+" Time is: "+String.valueOf(hour3)+" : "+String.valueOf(minute3));
        }
        else if(hour2>=0){
            int numOfRetrieved = retrievedActivity.length;
            int rdm1 = (int)((Math.random()*numOfRetrieved));
            int rdm2 = (int)((Math.random()*numOfRetrieved));
            displayActivity.add(retrievedActivity[rdm1]+" Time is: "+String.valueOf(hour1)+" : "+String.valueOf(minute1));
            displayActivity.add(retrievedActivity[rdm2]+" Time is: "+String.valueOf(hour2)+" : "+String.valueOf(minute2));
        }
        else{
            int numOfRetrieved = retrievedActivity.length;
            int rdm1 = (int)((Math.random()*numOfRetrieved));
            displayActivity.add(retrievedActivity[rdm1]+" Time is: "+String.valueOf(hour1)+" : "+String.valueOf(minute1));
        }
        /*display to listview at this page*/
        ArrayAdapter arrayAdapterForResult = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,displayActivity);
        listResult.setAdapter(arrayAdapterForResult);
            }
        });
        /*according to number of time selected by user, randomly assign result for display*/
//        if(hour2>=0 && hour3>=0){
//            int numOfRetrieved = retrievedActivity.length;
//            displayActivity = new String[3];
//            int rdm1 = (int)((Math.random()*numOfRetrieved));
//            int rdm2 = (int)((Math.random()*numOfRetrieved));
//            int rdm3 = (int)((Math.random()*numOfRetrieved));
//            displayActivity[0] = retrievedActivity[rdm1]+" Time is: "+String.valueOf(hour1)+" : "+String.valueOf(minute1);
//            displayActivity[1] = retrievedActivity[rdm2]+" Time is: "+String.valueOf(hour2)+" : "+String.valueOf(minute2);
//            displayActivity[2] = retrievedActivity[rdm3]+" Time is: "+String.valueOf(hour3)+" : "+String.valueOf(minute3);
//        }
//        else if(hour2>=0){
//            int numOfRetrieved = retrievedActivity.length;
//            displayActivity = new String[2];
//            int rdm1 = (int)((Math.random()*numOfRetrieved));
//            int rdm2 = (int)((Math.random()*numOfRetrieved));
//            displayActivity[0] = retrievedActivity[rdm1]+" Time is: "+String.valueOf(hour1)+" : "+String.valueOf(minute1);
//            displayActivity[1] = retrievedActivity[rdm2]+" Time is: "+String.valueOf(hour2)+" : "+String.valueOf(minute2);
//        }
//        else{
//            int numOfRetrieved = retrievedActivity.length;
//            displayActivity = new String[1];
//            int rdm1 = (int)((Math.random()*numOfRetrieved));
//            displayActivity[0] = retrievedActivity[rdm1]+" Time is: "+String.valueOf(hour1)+" : "+String.valueOf(minute1);
//        }
//        /*display to listview at this page*/
//        ArrayAdapter arrayAdapterForResult = new ArrayAdapter(this, android.R.layout.simple_list_item_1,displayActivity);
//        listResult.setAdapter(arrayAdapterForResult);


        //set on click listener for get result button
//        btnRes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                retrieveData(energy,fresh);
//            }
//        });
    }

    public void retrieveData(double energy, double fresh, int childage){
        RequestQueue queue = Volley.newRequestQueue(this);
        //convert double to string to pass to url for GET method
        String energyRetrieve = String.valueOf(energy);
        String freshRetrieve = String.valueOf(fresh);
        String ageRetrieve = String.valueOf(childage);
        String url = "http://10.0.0.146/TogetherWeGrow/findActivity.php?energy="+energyRetrieve+ "&fresh=" + freshRetrieve+ "&age=" + ageRetrieve;
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
                double energyVar;
                double freshVar;
                int ageVar;
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.e("activity: ",jsonObject.getString("activity"));
                activityVar = jsonObject.getString("activity");
//                energyVar = jsonObject.getDouble("energy");
//                freshVar = jsonObject.getDouble("fresh");
                System.out.println("sssss: " + activityVar);
                retrievedActivity[i]="Activity: "+activityVar;
//                ageVar = jsonObject.getInt("age");
                //textView.append("activity: "+activityVar+"energy need: "+energyVar+"fresh need: "+freshVar+"age: "+"\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
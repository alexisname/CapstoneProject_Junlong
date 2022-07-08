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
    String username;
    boolean filled;
    TextView clickForRes;
    String[] retrievedActivity;
    ArrayList<String> displayActivity;
    ListView listResult;
    Button btnBack;
    Button btnConfirm;


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
        listResult = (ListView) findViewById(R.id.listResult);
        clickForRes = findViewById(R.id.clickRes);
        btnBack = findViewById(R.id.btnBack);
        btnConfirm = findViewById(R.id.btnConfirm);
        displayActivity = new ArrayList<>();
        /*add empty string so that size is 3 for further update*/
        displayActivity.add(" ");
        displayActivity.add(" ");
        displayActivity.add(" ");
        filled = false;

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
                    String minuteOne = minute1<10?"0"+String.valueOf(minute1):String.valueOf(minute1);
                    String minuteTwo = minute2<10?"0"+String.valueOf(minute2):String.valueOf(minute2);
                    String minuteThree = minute3<10?"0"+String.valueOf(minute3):String.valueOf(minute3);
                    displayActivity.set(0,retrievedActivity[rdm1]+" At: "+String.valueOf(hour1)+" : "+minuteOne);
                    displayActivity.set(1,retrievedActivity[rdm2]+" At: "+String.valueOf(hour2)+" : "+minuteTwo);
                    displayActivity.set(2,retrievedActivity[rdm3]+" At: "+String.valueOf(hour3)+" : "+minuteThree);
                }
                else if(hour2>=0){
                    int numOfRetrieved = retrievedActivity.length;
                    int rdm1 = (int)((Math.random()*numOfRetrieved));
                    int rdm2 = (int)((Math.random()*numOfRetrieved));
                    String minuteOne = minute1<10?"0"+String.valueOf(minute1):String.valueOf(minute1);
                    String minuteTwo = minute2<10?"0"+String.valueOf(minute2):String.valueOf(minute2);
                    displayActivity.set(0,retrievedActivity[rdm1]+" At: "+String.valueOf(hour1)+" : "+minuteOne);
                    displayActivity.set(1,retrievedActivity[rdm2]+" At: "+String.valueOf(hour2)+" : "+minuteTwo);
                }
                else{
                    int numOfRetrieved = retrievedActivity.length;
                    int rdm1 = (int)((Math.random()*numOfRetrieved));
                    String minuteOne = minute1<10?"0"+String.valueOf(minute1):String.valueOf(minute1);
                    displayActivity.set(0,retrievedActivity[rdm1]+" At: "+String.valueOf(hour1)+" : "+minuteOne);
                }
                /*display to listview at this page*/
                ArrayAdapter arrayAdapterForResult;
                arrayAdapterForResult = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,displayActivity);
                listResult.setAdapter(arrayAdapterForResult);
                filled = true;//the list is filled
                arrayAdapterForResult.notifyDataSetChanged();//update listview result
            }
        });

        /*set onclick listener for back button*/
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*clear list content before back*/
                if(filled){
                    listResult.setAdapter(null);
                }
                Intent intentBack = new Intent(getApplicationContext(),PlanInput.class);
                intentBack.putExtra("username",username);
                startActivity(intentBack);
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
        String url = "http://10.0.0.74/TogetherWeGrow/findActivity.php?energy="+energyRetrieve+ "&fresh=" + freshRetrieve+ "&age=" + ageRetrieve;
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
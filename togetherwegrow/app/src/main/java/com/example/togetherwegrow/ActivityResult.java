package com.example.togetherwegrow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class ActivityResult extends AppCompatActivity {
    TextView textView;
    Button btnRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent(); //get calculated result from
        double energy = intent.getDoubleExtra("energy",0);
        double fresh = intent.getDoubleExtra("fresh",0);
        int childage = intent.getIntExtra("age",0);
        //InputMatch[] thing = intent.get

        textView = findViewById(R.id.result);
        /*TODO add fields for more organized result display*/
        btnRes = findViewById(R.id.btnRes);
        retrieveData(energy,fresh,childage);

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
                textView.setText("not responding");
            }
        });
        queue.add(stringRequest);
    }

    /*method to display data in jason*/
    public void parseJson(String response){
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i=0; i<jsonArray.length(); i++){//for each item in that jsonArray, get desired data
                String activityVar;
                double energyVar;
                double freshVar;
                int ageVar;
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.e("activity: ",jsonObject.getString("activity"));
                activityVar = jsonObject.getString("activity");
                energyVar = jsonObject.getDouble("energy");
                freshVar = jsonObject.getDouble("fresh");
//                ageVar = jsonObject.getInt("age");
                textView.append("activity: "+activityVar+"energy need: "+energyVar+"fresh need: "+freshVar+"age: "+"\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
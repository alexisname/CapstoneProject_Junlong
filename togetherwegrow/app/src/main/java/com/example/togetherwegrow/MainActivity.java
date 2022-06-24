package com.example.togetherwegrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*display welcome info to current user via intent from previous login*/
        Intent intent = getIntent();
        String currUser = intent.getStringExtra(Intent.EXTRA_TEXT);
        TextView wlcUser = findViewById(R.id.usernamemain);
        wlcUser.setText(currUser);

        /*define button for three main features*/
        Button inputBtn = findViewById(R.id.btnplan);
        Button trackBtn = findViewById(R.id.btntrackplan);
        Button recBtn = findViewById(R.id.btnrec);
        /*onclick listener for input button*/
        inputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToInput = new Intent(getApplicationContext(),PlanInput.class);
                startActivity(intentToInput);
                finish();
            }
        });

        /*onclick listener for track plan button*/
        trackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToTrack = new Intent(getApplicationContext(),StatusTrack.class);
                startActivity(intentToTrack);
                finish();
            }
        });

        /*onclick listener for recommendation button*/
        recBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToRec = new Intent(getApplicationContext(),Recommendation.class);
                startActivity(intentToRec);
                finish();
            }
        });
    }
}
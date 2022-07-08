package com.example.togetherwegrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StatusTrack extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_track);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        /*onclick listener for back to main page*/
        Button trcBTM = findViewById(R.id.trackBTM);
        trcBTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMain = new Intent(getApplicationContext(),MainActivity.class);
                intentToMain.putExtra("username",username);
                startActivity(intentToMain);
                finish();
            }
        });
    }
}
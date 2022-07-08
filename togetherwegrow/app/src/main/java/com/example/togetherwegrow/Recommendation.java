package com.example.togetherwegrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Recommendation extends AppCompatActivity {
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        /*onclick listener for back to main page*/
        Button recBTM = findViewById(R.id.recBTM);
        recBTM.setOnClickListener(new View.OnClickListener() {
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
package com.example.togetherwegrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LogIn extends AppCompatActivity {

    /*
    specify fields in the xml file
     */
    TextInputEditText textInputUsername;
    TextInputEditText textInputPassword;
    Button buttonLogIn;
    TextView textViewSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        /*
        initiation
         */
        textInputUsername = findViewById(R.id.usernamelogin);
        textInputPassword = findViewById(R.id.passwordlogin);
        buttonLogIn = findViewById(R.id.buttonLogin);
        textViewSignUp = findViewById(R.id.signupText);

        /*
        add onclick listener to log in button
        it checks if all fields are filled
        and POST to database
        if succeed, it redirects to welcome home page
        else shows error message
         */
        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                get the value/content of input
                 */
                String username;
                String password;
                username = textInputUsername.getText().toString();
                password = textInputPassword.getText().toString();
                /*check if all fields are filled*/
                if(!username.equals("") && !password.equals("")){
                    /*handler to open a thread to pass the message to database*/
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            /*put data input array and write to database*/
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;
                            /*use a utility for I/O stream*/
                            PutData putData = new PutData("http://10.0.0.146/TogetherWeGrow/login.php","POST",field,data);
                            if(putData.startPut()){
                                if(putData.onComplete()){
                                    String result = putData.getResult();
                                    if(result.equals("Login Success")){ //if log in succeed, redirect to welcome home page
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        intent.putExtra(Intent.EXTRA_TEXT,username);//pass current logged username together to the main page
                                        startActivity(intent);
                                        finish();

                                    }
                                    else {//show error message to user
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else {//if user leaves a field empty
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*
        add onclick listener to sign up textview, if user does not have an account, just redirect to sign up page
         */
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
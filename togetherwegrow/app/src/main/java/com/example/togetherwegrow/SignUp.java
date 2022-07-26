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

public class SignUp extends AppCompatActivity {

    /*
    specify fields in the xml file
     */
    TextInputEditText textInputEmail;
    TextInputEditText textInputUsername;
    TextInputEditText textInputPassword;
    Button buttonSignUp;
    TextView textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /*
        initiation
         */
        textInputEmail = findViewById(R.id.emailsignup);
        textInputUsername = findViewById(R.id.usernamesignup);
        textInputPassword = findViewById(R.id.passwordsignup);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);

        /*
        add onclick listener to sign up button
        it checks if all fields are filled
        and POST to database
        if succeed, it redirects to sign in page
        else shows error message
         */
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                get the value/content of input
                 */
                String email = textInputEmail.getText().toString();
                String username = textInputUsername.getText().toString();
                String password = textInputPassword.getText().toString();
                /*check if all fields are filled*/
                if(!email.equals("") && !username.equals("") && !password.equals("")){
                    /*handler to open a thread to pass the message to database*/
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            /*put data input array and write to database*/
                            String[] field = new String[3];
                            field[0] = "email";
                            field[1] = "username";
                            field[2] = "password";
                            String[] data = new String[3];
                            data[0] = email;
                            data[1] = username;
                            data[2] = password;
                            /*use SendData helper for url connection and IO stream
                             * check the returned result on completion of data sending
                             */
                            SendData putData = new SendData("http://35.183.174.53/TogetherWeGrow/signup.php","POST",field,data);
                            if(putData.startSend()){
                                if(putData.onComplete()){
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){ //if sign up succeed, redirect to sign in page
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),LogIn.class);
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
        add onclick listener to login textview, if user already has an account, just redirect to login page
         */
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LogIn.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
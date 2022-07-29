package com.example.togetherwegrow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class PlanInput extends AppCompatActivity{
    /*fields to be sent to database*/
    private String username;
    private String worktype;
    private String workload;
    private String freshnessafterwork;
    private String childage;
    private String mostpreferred;
    private String secondpreferred;
    private String thirdpreferred;
    private String dislike;
    private String selectDay;
    private String email;
    int hour1;
    int minute1;
    int hour2;
    int minute2;
    int hour3;
    int minute3;

    Spinner spworktype;
    Spinner spworkload;
    Spinner spfreshness;
    Spinner spage;
    Spinner spmost;
    Spinner spsecond;
    Spinner spthird;
    Spinner spselectDay;
    AutoCompleteTextView acdis;
    Button iptBTM;
    Button iptSMT;
    Button timeBtn1;
    Button timeBtn2;
    Button timeBtn3;
    Button timeRst;
    TextInputEditText txtemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_input);
        /*receive current username and pass it to field username*/
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        /*initialize dropdown list and buttons*/
        spworktype = findViewById(R.id.spworktype);
        spworkload = findViewById(R.id.spworkload);
        spfreshness = findViewById(R.id.spfreshness);
        spage = findViewById(R.id.spage);
        spmost = findViewById(R.id.spmost);
        spsecond = findViewById(R.id.spsecond);
        spthird = findViewById(R.id.spthird);
        spselectDay = findViewById(R.id.spselectday);
        acdis = findViewById(R.id.acdis);
        iptBTM = findViewById(R.id.inputBTM);
        iptSMT = findViewById(R.id.inputSMT);
        timeBtn1 = findViewById(R.id.time1);
        timeBtn2 = findViewById(R.id.time2);
        timeBtn3 = findViewById(R.id.time3);
        timeRst = findViewById(R.id.timeRst);
        txtemail = findViewById(R.id.emaileditCal);

        /*assign content for work type dropdown list*/
        ArrayAdapter<CharSequence> worktypeAdapter = ArrayAdapter.createFromResource(this,R.array.worktype, R.layout.spinner_item);
        worktypeAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spworktype.setAdapter(worktypeAdapter);
        spworktype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                worktype = spworktype.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*assign content for work load dropdown list*/
        ArrayAdapter<CharSequence> workloadAdapter = ArrayAdapter.createFromResource(this,R.array.workload, R.layout.spinner_item);
        workloadAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spworkload.setAdapter(workloadAdapter);
        spworkload.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                workload = spworkload.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign content for energy dropdown list*/
        ArrayAdapter<CharSequence> freshAdapter = ArrayAdapter.createFromResource(this,R.array.fresh, R.layout.spinner_item);
        freshAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spfreshness.setAdapter(freshAdapter);
        spfreshness.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                freshnessafterwork = spfreshness.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign time picker for three time slots*/
        timeBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener1 = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {// on select, assign value to hour and minute
                        hour1 = selectedHour;
                        minute1 = selectedMinute;
                        timeBtn1.setText(String.format(Locale.getDefault(),"%02d:%02d",hour1, minute1));
                    }
                };
                int style = android.R.style.Theme_Holo;
                TimePickerDialog timePickerDialog1 = new TimePickerDialog(PlanInput.this,style,onTimeSetListener1,hour1,minute1,true);
                timePickerDialog1.setTitle("Select Time");
                timePickerDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog1.show();
            }
        });

        timeBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener2 = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {// on select, assign value to hour and minute
                        hour2 = selectedHour;
                        minute2 = selectedMinute;
                        timeBtn2.setText(String.format(Locale.getDefault(),"%02d:%02d",hour2, minute2));
                    }
                };
                int style = android.R.style.Theme_Holo;
                TimePickerDialog timePickerDialog2 = new TimePickerDialog(PlanInput.this,style,onTimeSetListener2,hour2,minute2,true);
                timePickerDialog2.setTitle("Select Time");
                timePickerDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog2.show();
            }
        });

        timeBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener3 = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {// on select, assign value to hour and minute
                        hour3 = selectedHour;
                        minute3 = selectedMinute;
                        timeBtn3.setText(String.format(Locale.getDefault(),"%02d:%02d",hour3, minute3));
                    }
                };
                int style = android.R.style.Theme_Holo;
                TimePickerDialog timePickerDialog3 = new TimePickerDialog(PlanInput.this,style,onTimeSetListener3,hour3,minute3,true);
                timePickerDialog3.setTitle("Select Time");
                timePickerDialog3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog3.show();
            }
        });


        /*assign content for age dropdown list*/
        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this,R.array.age, R.layout.spinner_item);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spage.setAdapter(ageAdapter);
        spage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                childage = spage.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign content for activity 1 dropdown list*/
        ArrayAdapter<CharSequence> mostAdapter = ArrayAdapter.createFromResource(this,R.array.activities, R.layout.spinner_item);
        mostAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spmost.setAdapter(mostAdapter);
        spmost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mostpreferred = spmost.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign content for activity 2 dropdown list*/
        ArrayAdapter<CharSequence> secondAdapter = ArrayAdapter.createFromResource(this,R.array.activities, R.layout.spinner_item);
        secondAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spsecond.setAdapter(secondAdapter);
        spsecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                secondpreferred = spsecond.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign content for activity 3 dropdown list*/
        ArrayAdapter<CharSequence> thirdAdapter = ArrayAdapter.createFromResource(this,R.array.activities, R.layout.spinner_item);
        thirdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spthird.setAdapter(thirdAdapter);
        spthird.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                thirdpreferred = spthird.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign content for select day dropdown list and convert to calendar day value*/
        ArrayAdapter<CharSequence> selectDayAdapter = ArrayAdapter.createFromResource(this,R.array.daysOfWeek, R.layout.spinner_item);
        selectDayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spselectDay.setAdapter(selectDayAdapter);
        spselectDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectDay = spselectDay.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*assign content for disklike autocompletetextview list*/
        String[] dislikeArr = getResources().getStringArray(R.array.dislikes);
        Set<String> dislikeSet = new HashSet<>();
        for(String dis:dislikeArr){
            dislikeSet.add(dis);
        }
        ArrayAdapter<String> disAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dislikeArr);
        acdis.setAdapter(disAdapter);
        acdis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(acdis.getText()!=null){
                    dislike = acdis.getText().toString();
                    //Log.e("dislike click: ", dislike);
                }
            }
        });

        /*calculate points according to input*/
        /*
        add onclick listener to submit input button
        when clicked, call calPoints() method to calculate input
        then redirect to result page, fetch data according to input and display result
         */
        iptSMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("on click submit: "+ SystemClock.currentThreadTimeMillis());
                //if all fields are filled, calculate
                if(timeBtn1.getText().equals("Select Time")&& timeBtn2.getText().equals("Select Time")&& timeBtn3.getText().equals("Select Time")){
                    Toast.makeText(getApplicationContext(), "Choose at least one time slot", Toast.LENGTH_SHORT).show();
                }
                else if(mostpreferred.equals("N/A")&& secondpreferred.equals("N/A") && thirdpreferred.equals("N/A")){
                    Toast.makeText(getApplicationContext(), "Choose at least one preference", Toast.LENGTH_SHORT).show();
                }
                else if(dislike==null || !dislikeSet.contains(dislike)){
                    Toast.makeText(getApplicationContext(), "Select listed dislike activity or N/A", Toast.LENGTH_SHORT).show();
                }
                else if(!timeBtn1.getText().equals("Select Time") && (timeBtn1.getText().equals(timeBtn2.getText()) || timeBtn1.getText().equals(timeBtn3.getText()))
                || !timeBtn2.getText().equals("Select Time") && (timeBtn2.getText().equals(timeBtn1.getText()) || timeBtn2.getText().equals(timeBtn3.getText()))
                || !timeBtn3.getText().equals("Select Time") && (timeBtn3.getText().equals(timeBtn1.getText()) || timeBtn3.getText().equals(timeBtn2.getText()))) {
                    Toast.makeText(getApplicationContext(), "Two time slots should be not be the same", Toast.LENGTH_SHORT).show();
                }
                else if(!username.equals("") && !worktype.equals("")&& !workload.equals("")&& !freshnessafterwork.equals("")
                        && !childage.equals("")&& !mostpreferred.equals("")&& !secondpreferred.equals("")
                        && !thirdpreferred.equals("")&& !dislike.equals("")) {
                    /*assign email content*/
                    email = txtemail.getText().toString();
                    InputMatch inputMatch = new InputMatch(worktype, workload, freshnessafterwork, mostpreferred, secondpreferred, thirdpreferred, childage);
                    inputMatch.calPoints();
                    //pass input to inputmatch then calculate
                    //redirect to result page, with calculated points
                    Intent intentSMT = new Intent(getApplicationContext(), ActivityResult.class);
                    intentSMT.putExtra("energy", inputMatch.getEnergy());
                    intentSMT.putExtra("fresh", inputMatch.getFreshness());
                    intentSMT.putExtra("age", Integer.valueOf(childage));
                    intentSMT.putExtra("selectDay", selectDay);
                    intentSMT.putExtra("dislike", dislike);
                    intentSMT.putExtra("email", email);
                    if(timeBtn1.getText().equals("Select Time")){
                        intentSMT.putExtra("hour1",-1);
                        intentSMT.putExtra("minute1",-1);
                    }else{
                        intentSMT.putExtra("hour1",hour1);
                        intentSMT.putExtra("minute1",minute1);
                    }
                    if(timeBtn2.getText().equals("Select Time")){
                        intentSMT.putExtra("hour2",-1);
                        intentSMT.putExtra("minute2",-1);
                    }else{
                        intentSMT.putExtra("hour2",hour2);
                        intentSMT.putExtra("minute2",minute2);
                    }
                    if(timeBtn3.getText().equals("Select Time")){
                        intentSMT.putExtra("hour3",-1);
                        intentSMT.putExtra("minute3",-1);
                    }else{
                        intentSMT.putExtra("hour3",hour3);
                        intentSMT.putExtra("minute3",minute3);
                    }
                    intentSMT.putExtra("username",username);
                    startActivity(intentSMT);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*onclick listener for resetting time*/
        timeRst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeBtn1.setText("Select Time");
                timeBtn2.setText("Select Time");
                timeBtn3.setText("Select Time");
            }
        });


        /*onclick listener for back to main page*/
        iptBTM.setOnClickListener(new View.OnClickListener() {
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
package com.bibendum.bluehacks.bibendum;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

import io.realm.Realm;

public class HabitActivity extends AppCompatActivity {

    TextInputEditText habitView;
    EditText durationT, frequencyT;
    Button btnMinusD, btnAddD, btnMinusF, btnAddF, addHabitBtn;
    Realm realm;
    Spinner spinner;
    String tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);

        setTitle("YAY NEW HABIT!");

        habitView = (TextInputEditText) findViewById(R.id.habitText);
        durationT = (EditText) findViewById(R.id.durationText);
        frequencyT = (EditText) findViewById(R.id.frequencyText);

        durationT.setText("66");
        frequencyT.setText("1");

        btnMinusD=(Button)findViewById(R.id.buttonMinusD);
        btnAddD=(Button)findViewById(R.id.buttonAddD);
        btnMinusF=(Button)findViewById(R.id.buttonMinusF);
        btnAddF=(Button)findViewById(R.id.buttonAddF);
        addHabitBtn=(Button)findViewById(R.id.addHabitBtn);

        //Button Listeners
        btnMinusD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                int curr = Integer.parseInt(durationT.getText().toString());
                if(curr >= 67) durationT.setText((curr-1)+"");
            }
        });
        btnAddD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                durationT.setText((Integer.parseInt(durationT.getText().toString())+1)+"");
            }
        });
        btnMinusF.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                int curr = Integer.parseInt(frequencyT.getText().toString());
                if(curr >= 1) frequencyT.setText((curr-1)+"");
            }
        });
        btnAddF.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                int curr = Integer.parseInt(frequencyT.getText().toString());
                if( curr < 7 ) frequencyT.setText((Integer.parseInt(frequencyT.getText().toString())+1)+"");
            }
        });


        spinner = (Spinner) findViewById(R.id.tagBar);

        String[] items = new String[] {"Spiritual", "Emotional/Psychological", "Physical", "Social", "Occupational", "Intellectual"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                tag = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        addHabitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                String habit = habitView.getText().toString();
                int duration = Integer.parseInt(durationT.getText().toString());
                int frequency = Integer.parseInt(frequencyT.getText().toString());
                Date d = new Date();
                realm = Realm.getDefaultInstance();
                Habit newHabit = new Habit(habit,duration,frequency,d,tag);
                RealmHelper rh = new RealmHelper(realm);
                rh.saveHabits(newHabit);
                Intent ag = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(ag);
            }
        });




    }



}
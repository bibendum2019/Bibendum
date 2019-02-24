package com.bibendum.bluehacks.bibendum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;

public class AdventureSummaryActivity extends AppCompatActivity {
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    Date date = new Date();
    int nRescue;
    int ptsPerResc = 10;
    private Realm realm;
    private RealmHelper helper;
    private Button btnRescue;
    private RecyclerView linesRView;
    private TextView title, currDate;
    private ASummaryAdapter mAdapter;
    private ArrayList<String> lines = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_summary);
        //setTitle("Adventure Summary");

        //initialization of View elements
        btnRescue = (Button) findViewById(R.id.btnRescue);
        title = (TextView) findViewById(R.id.title);
        currDate = (TextView) findViewById(R.id.date);

        // get list of lines
        realm = Realm.getDefaultInstance();
        //RETRIEVE
        helper = new RealmHelper(realm);
        nRescue = helper.retrieveRescPts();
        lines.add("You have saved " + nRescue + "citizens today!");

        // setting up recycler view
        linesRView = (RecyclerView) findViewById(R.id.linesRView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        linesRView.setLayoutManager(mLayoutManager);
        linesRView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ASummaryAdapter(lines, AdventureSummaryActivity.this);
        linesRView.setAdapter(mAdapter);

        //setup rescue button
        if(nRescue == 0){
            btnRescue.setAlpha(0);
        }else{
            btnRescue.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Code here executes on main thread after user presses button
                    addRescues();
                }
            });
        }

        // set date
        formatter.format(date);
        currDate.setText(date.toString());
    }

    private void addRescues() {
        nRescue += ptsPerResc;
        mAdapter.notifyDataSetChanged();
        lines.add("You have saved " + nRescue + "citizens today!");
    }

}

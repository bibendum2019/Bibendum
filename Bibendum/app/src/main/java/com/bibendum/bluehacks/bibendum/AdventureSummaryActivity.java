package com.bibendum.bluehacks.bibendum;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;

public class AdventureSummaryActivity extends AppCompatActivity {
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    Date date = new Date();
    Button btnRescue;
    int nRescue;
    private Realm realm;
    private RealmHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_summary);
        //setTitle("Adventure Summary");

        //initialization of View elements
        btnRescue = (Button) findViewById(R.id.btnRescue);

        // get list of items
        realm = Realm.getDefaultInstance();
        //RETRIEVE
        helper = new RealmHelper(realm);
        nRescue = helper.retrieveRescPts();

        linesRView = (RecyclerView) findViewById(R.id.linesRView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        linesRView.setLayoutManager(mLayoutManager);
        linesRView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ASummaryActivityAdapter(lines, AdventureSummaryActivity.this);
        linesRView.setAdapter(mAdapter);

        //setup rescue button
        if(nRescue == 0){
            btnRescue.setAlpha(0);
        }else{
            btnRescue.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Code here executes on main thread after user presses button
                }
            });
        }

        formatter.format(date);
    }

}

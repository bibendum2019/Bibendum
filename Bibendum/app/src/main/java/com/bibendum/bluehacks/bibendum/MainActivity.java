package com.bibendum.bluehacks.bibendum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private RecyclerView feedRView;
    private boolean strongholdExists = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup realm
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("habits.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);

        realm = Realm.getDefaultInstance();
        //RETRIEVE
        RealmHelper helper = new RealmHelper(realm);

        if( !strongholdExists ) {
            helper.saveStronghold(new Stronghold());
            strongholdExists = true;
        }

        Date date = new Date();
        //TextView tv = (TextView) findViewById(R.id.textView3);

        ArrayList<Habit> habits = helper.retrieveHabits();

        feedRView = (RecyclerView) findViewById(R.id.feedRView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        feedRView.setLayoutManager(mLayoutManager);
        feedRView.setItemAnimator(new DefaultItemAnimator());
        MainActivityAdapter mAdepter = new MainActivityAdapter(habits,helper.retrieveStronghold(),MainActivity.this);
        feedRView.setAdapter(mAdepter);

//        Habit newHabit = new Habit("COOKING",2,3,date,"Physical");
//        helper.saveHabits(newHabit);
//        //helper.deleteHabits();

//        System.out.println("number of habits: " + habits.size());
//        for(Habit h: habits){
//            System.out.println(h.getDaysPassed(date));
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addHabit:
                Intent ag = new Intent(getApplicationContext(), StrongholdActivity.class);
                startActivity(ag);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}

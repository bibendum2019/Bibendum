package com.bibendum.bluehacks.bibendum;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class StrongholdActivity extends AppCompatActivity {
    private ArrayList<Item> items = new ArrayList<Item>();
    private RecyclerView itemsRView;
    private StrongholdAdapter mAdapter;
    public TextView itemDetails;
    public Dialog myDialog;
    private Realm realm;
    private RealmHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stronghold);
        setTitle("Stronghold");

        // get list of items
        realm = Realm.getDefaultInstance();
        //RETRIEVE
        helper = new RealmHelper(realm);
        helper.removeItem("name", "house");
        helper.removeItem("name", "hammer");
        helper.removeItem("name", "saw");
        helper.removeItem("name", "barbwire");
        prepareItemData();
        items = helper.retrieveItems();

        itemsRView = (RecyclerView) findViewById(R.id.itemsRView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        itemsRView.setLayoutManager(mLayoutManager);
        itemsRView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new StrongholdAdapter(items, StrongholdActivity.this);
        itemsRView.setAdapter(mAdapter);

        // popup
        myDialog = new Dialog(this);

        itemsRView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), itemsRView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Item item = items.get(position);
                String habit = item.getHabit();
                int habitDur = item.getHabitDuration();
                int points = item.getPoints();
                ShowPopup(habit, habitDur, points);
                //Toast.makeText(getApplicationContext(), item.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    // to show popup
    public void ShowPopup(String habit, int habitDur, int points) {
        TextView itemDetails;
        myDialog.setContentView(R.layout.item_popup_window);
        itemDetails =(TextView) myDialog.findViewById(R.id.itemDetails);
        itemDetails.setText("You got this item for " + habit + " for " + habitDur + " days!" +
                "\nPoints: " + points);
        itemDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        myDialog.show();
    }

    // dummy data for testing
    private void prepareItemData() {
        Item item1 = new Item("house","walking the dog",30,50);
        helper.saveItems(item1);

//        Item item2 = new Item("hammer");
//        item2.setHabit("drinking 10 glasses of water");
//        item2.setHabitDuration(7);
//        item2.setPoints(40);
//        helper.saveItems(item2);
//
//        Item item3 = new Item("saw");
//        item3.setHabit("jogging for 30 minutes");
//        item3.setHabitDuration(60);
//        item3.setPoints(30);
//        helper.saveItems(item3);
//
//        Item item4 = new Item("barbwire");
//        item4.setHabit("meditating every morning");
//        item4.setHabitDuration(14);
//        item4.setPoints(20);
//        helper.saveItems(item4);

    }

}

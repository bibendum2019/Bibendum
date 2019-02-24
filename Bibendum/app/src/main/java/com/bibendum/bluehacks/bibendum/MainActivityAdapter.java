package com.bibendum.bluehacks.bibendum;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHolder> {

    private List<Habit> habitsList;
    private Stronghold s;
    private int limit = 3;
    Context c;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text, progressDay ;
        public ProgressBar pb;
        public Button accomplishBtn, newHabitBtn;

        public MyViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.habitName);
            progressDay = (TextView) view.findViewById(R.id.progressDay);
            pb = (ProgressBar) view.findViewById(R.id.progressBar);
            accomplishBtn = (Button) view.findViewById(R.id.accomplishBtn);
            newHabitBtn = (Button) view.findViewById(R.id.newHabitBtn);
        }
    }


    public MainActivityAdapter(List<Habit> habitsList, Stronghold s,Context c) {
        this.habitsList = habitsList;
        this.s = s;
        this.c = c;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == habitsList.size()) ? R.layout.addhabit_recview_row_layout : R.layout.main_recview_row_layout;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        if(viewType == R.layout.main_recview_row_layout){
            itemView = LayoutInflater.from(c).inflate(R.layout.main_recview_row_layout, parent, false);
        }

        else {
            itemView = LayoutInflater.from(c).inflate(R.layout.addhabit_recview_row_layout, parent, false);
        }


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(position == habitsList.size()) {
            holder.newHabitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ag = new Intent(c, HabitActivity.class);
                    c.startActivity(ag);
                    Toast.makeText(c, "Time to add a new habit!", Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            Date curr = new Date();
            final Habit h = habitsList.get(position);
            holder.text.setText(h.getName());
            holder.progressDay.setText("Progress: Day " + (h.getDaysPassed(curr)+1+"") + "/" + h.getDuration()+"");
            holder.pb.setMax(h.getDuration());
            holder.pb.setProgress((int) h.getDaysPassed(curr));
            final Realm realm = Realm.getDefaultInstance();
            if(h.canDoHabitToday()){
                holder.accomplishBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        realm.beginTransaction();
                        h.didHabitToday();
                        s.addPtsForResc(1);
                        realm.commitTransaction();
                        System.out.println("YOU HAVE: " + s.getPtsForResc());
                        Button button = (Button) view;
                        button.setVisibility(View.GONE);
                        Toast.makeText(c, "Time to add a new habit!", Toast.LENGTH_LONG).show();
                    }
                });
            }else holder.accomplishBtn.setAlpha(0);


        }


        //holder.groupName.setText(h.getGroupName());
    }

    @Override
    public int getItemCount() {
        if(habitsList.size() > limit) return limit;
        else return habitsList.size()+1;
    }


}


package com.bibendum.bluehacks.bibendum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ASummaryAdapter extends RecyclerView.Adapter<ASummaryAdapter.MyViewHolder> {
    private ArrayList<String> lines;
    Context c;

    public ASummaryAdapter(ArrayList<String> lines, Context c) {
        this.lines = lines;
        this.c = c;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView line;

        public MyViewHolder(View view) {
            super(view);
            line = (TextView) view.findViewById(R.id.line);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View lineView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_recview_row_layout, parent, false);

        return new MyViewHolder(lineView);
    }

    public void onBindViewHolder(ASummaryAdapter.MyViewHolder holder, int position) {
        String line = lines.get(position);
        holder.line.setText(line);
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }
}

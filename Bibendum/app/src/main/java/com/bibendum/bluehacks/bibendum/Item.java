package com.bibendum.bluehacks.bibendum;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;

public class Item extends RealmObject {
    private String name, habit;
    private Date dateReceived;
    private int habitDuration, points;

    public Item(){}

    public Item(String n, String h, int hDur, int pts){
        name = n;
        habit = h;
        habitDuration = hDur;
        points = pts;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public int getHabitDuration() {
        return habitDuration;
    }

    public void setHabitDuration(int habitDuration) {
        this.habitDuration = habitDuration;
    }

    public Item(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String itemName) {
        this.name = itemName;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

}

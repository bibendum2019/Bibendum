package com.bibendum.bluehacks.bibendum;

import java.util.Calendar;
import java.util.Date;

import io.realm.RealmObject;

public class Habit extends RealmObject {
	private String name;
	private int duration;
	private int frequency;
	private int daysPassed;
	private Date start;
	private boolean currentStatus;
	private boolean finalStatus;
	private String tag;

	public Habit(){

	}

	public Habit(String n, int d, int f, Date date, String t) {
		name = n;
		duration = d;
		frequency = f;
		start = date;
		currentStatus = false;
		finalStatus = false;
		tag = t;
	}

	public String getName()
	{
		return name;
	}

	public long getDaysPassed(Date curr) {
        long diff = curr.getTime() - start.getTime();
        long seconds = diff / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        return days;
	}

	public void addDay()
	{
		daysPassed++;
	}

	public int getDuration() { return duration; }

	public int getFrequency()
	{
		return frequency;
	}

	public Date getStartDate() { return start; }

	public String getTag() { return tag; }

	public int getDaysLeft()
	{
		return duration - daysPassed;
	}

	public boolean getCurrentStatus() { return currentStatus; }

	public void didHabitToday() { currentStatus = true; }

	public boolean habitAccomplished() { return finalStatus; }

	public boolean canDoHabitToday(){
	    boolean flag = false;
	    Date currDate = new Date();
	    if((getDaysPassed(currDate))%duration == 0) flag = true;
	    return flag;
    }
}
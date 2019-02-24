package com.bibendum.bluehacks.bibendum;

public class Citizen {
	private String[] classNames = {"Spiritual", "Emotional/Psychological", "Physical", "Social", "Occupational", "Intellectual"};
	private int classs;
	private boolean living;

	public Citizen(int c) {
		classs = c;
		living = true;
	}

	public String getClasss() {
		return classNames[classs];
	}

	public boolean getLiving()
	{
		return living;
	}
}
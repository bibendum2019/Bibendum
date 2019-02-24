package com.bibendum.bluehacks.bibendum;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;
    public RealmHelper(Realm realm){
        this.realm = realm;
    }

    public void saveHabits(final Habit habit) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Habit h =realm.copyToRealm(habit);

            }
        });
    }

    public ArrayList<Habit> retrieveHabits() {
        ArrayList<Habit> habitsInfos = new ArrayList<>();
        RealmResults<Habit> habits = realm.where(Habit.class).findAll();

        for (Habit h : habits) {
            Habit toAdd = new Habit(h.getName(),h.getDuration(),h.getFrequency(),h.getStartDate(),h.getTag());
            habitsInfos.add(h);
        }

        return habitsInfos;
    }


    public void deleteHabits(){
        RealmResults<Habit> habits = realm.where(Habit.class).findAll();
        realm.beginTransaction();
        habits.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public void saveItems(final Item item) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Item i =realm.copyToRealm(item);

            }
        });
    }

    public ArrayList<Item> retrieveItems() {
        ArrayList<Item> items = new ArrayList<>();
        RealmResults<Item> itemsRealm = realm.where(Item.class).findAll();

        for (Item i : itemsRealm) {
            items.add(i);
        }

        return items;
    }

    public void removeItem(String field, String value) {

        RealmResults<Item> results = realm.where(Item.class).equalTo(field, value).findAll();

        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public void saveStronghold(final Stronghold stronghold) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Stronghold s =realm.copyToRealm(stronghold);

            }
        });
    }

    public Stronghold retrieveStronghold() {
        RealmResults<Stronghold> s = realm.where(Stronghold.class).findAll();
        return s.first();
    }

    public void removeStronghold() {
        RealmResults<Stronghold> results = realm.where(Stronghold.class).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

}

package com.bibendum.bluehacks.bibendum;

import java.util.ArrayList;

<<<<<<< HEAD
import io.realm.RealmList;
=======
>>>>>>> develop
import io.realm.RealmObject;

public class Stronghold extends RealmObject {

    private int strength;
    private int ptsForResc;
    private int nCitizens;
    private int nSpirit;
    private int nEmoPsych;
    private int nPhys;
    private int nSoc;
    private int nOccup;
    private int nIntell;
    private RealmList<Item> items = new RealmList<>();

    public int getPtsForResc() {
        return ptsForResc;
    }

    public void setPtsForResc(int ptsForResc) {
        this.ptsForResc = ptsForResc;
    }

    public void addPtsForResc(int ptsForResc) {
        this.ptsForResc += ptsForResc;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void addStrength(int moreStrength){
        this.strength += moreStrength;
    }

    public int getNCitizens() {
        return nCitizens;
    }

    public void setNCitizens(int nCitizens) {
        this.nCitizens = nCitizens;
    }

    public void addCitizens(int newCitizens){
        this.nCitizens += newCitizens;
    }

    public int getNSpirit() {
        return nSpirit;
    }

    public void setNSpirit(int nSpirit) {
        this.nSpirit = nSpirit;
    }

    public void addNSpirit(int moreSpirit){
        this.nSpirit += moreSpirit;
    }

    public int getNEmoPsych() {
        return nEmoPsych;
    }

    public void setNEmoPsych(int nEmoPsych) {
        this.nEmoPsych = nEmoPsych;
    }

    public void addNEmoPsych(int moreEmoPsych){
        this.nEmoPsych += moreEmoPsych;
    }

    public int getNPhys() {
        return nPhys;
    }

    public void setNPhys(int nPhys) {
        this.nPhys = nPhys;
    }

    public void addNPhys(int morePhys){
        this.nPhys += morePhys;
    }

    public int getNSoc() {
        return nSoc;
    }

    public void setNSoc(int nSoc) {
        this.nSoc = nSoc;
    }

    public void addNSoc(int moreSoc){
        this.nSoc += moreSoc;
    }

    public int getNOccup() {
        return nOccup;
    }

    public void setNOccup(int nOccup) {
        this.nOccup = nOccup;
    }

    public void addNOccup(int moreOccup){
        this.nOccup += moreOccup;
    }

    public int getNIntell() {
        return nIntell;
    }

    public void setNIntell(int nIntell) {
        this.nIntell = nIntell;
    }

    public void addNIntell(int moreIntell){
        this.nIntell += moreIntell;
    }

    public RealmList<Item> getItems() {
        return items;
    }

    public void setItems(RealmList<Item> items) {
        this.items = items;
    }
}

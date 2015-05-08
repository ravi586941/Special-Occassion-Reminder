package com.example.nkharish.specialoccasionremainder;

import java.util.Comparator;

/**
 * Created by Raveendra on 4/27/2015.
 */
public class Occasions implements Comparable<Occasions>
       // implements Comparable<Occasions>
{

    public String name;
    public String occasion;
    public String date;
    public long id;
    public long diff;

public Occasions()
{

}
    public Occasions(String name, String occasion, String date, long diff) {
        super();
        this.name = name;
        this.occasion = occasion;
        this.date = date;
        this.diff=diff;
    }



    public long getDiff() {   return diff;  }

    public void setDiff(long diff) {
        this.diff = diff;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int compareTo(Occasions occasions) {

        long compareQuantity = ((Occasions) occasions).getDiff();
        return (int)(this.diff - compareQuantity);

    }



}

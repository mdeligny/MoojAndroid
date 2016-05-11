package com.example.martindeligny.mooj;


import java.util.Date;

/**
 * Created by martindeligny on 29/04/2016.
 */
public class Deal {

    public String name;
    public String description;
    public Date date;

    public Deal(String name, String description, Date date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }
}

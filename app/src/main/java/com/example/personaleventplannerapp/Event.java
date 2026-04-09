package com.example.personaleventplannerapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class Event {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String category;
    public String location;
    public String date;
    public String time;

    public Event(String title, String category, String location, String date, String time) {
        this.title = title;
        this.category = category;
        this.location = location;
        this.date = date;
        this.time = time;
    }
}

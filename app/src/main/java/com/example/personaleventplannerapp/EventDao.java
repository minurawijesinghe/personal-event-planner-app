package com.example.personaleventplannerapp;

import androidx.room.*;
import java.util.List;

@Dao
public interface EventDao {
    @Insert
    void insert(Event event);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

    @Query("SELECT * FROM events ORDER BY date ASC")
    List<Event> getAll();

    @Query("SELECT * FROM events WHERE id = :id")
    Event getById(int id);
}

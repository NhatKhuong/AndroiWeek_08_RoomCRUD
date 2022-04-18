package com.example.demosqllite2;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlaceDao {

    @Query("SELECT * FROM place")
    List<Place> getAll();

    @Delete
    void delete(Place place);

    @Insert
    void insert(Place place);

    @Query("SELECT * FROM place WHERE id IN (:placeid)")
    Place loadAllById(int placeid);

    @Update
    void update(Place place);
}

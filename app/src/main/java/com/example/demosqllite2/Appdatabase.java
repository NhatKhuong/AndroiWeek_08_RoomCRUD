package com.example.demosqllite2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Place.class}, version = 1)
public abstract class Appdatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "place.db";
    private static Appdatabase instance;

    public static synchronized Appdatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),Appdatabase.class,DATABASE_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }
    public  abstract  PlaceDao placeDao();


}

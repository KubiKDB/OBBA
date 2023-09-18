package com.daniel.obba.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.daniel.obba.dao.StatDao;
import com.daniel.obba.entities.Statistics;

@Database(entities = Statistics.class, version = 2, exportSchema = false)
public abstract class StatDatabase extends RoomDatabase {

    private static StatDatabase statDatabase;

    public static synchronized StatDatabase getDatabase(Context context){
        if (statDatabase == null){
            statDatabase = Room.databaseBuilder(
                    context,
                    StatDatabase.class,
                    "stats_db"
            ).build();
        }
        return statDatabase;
    }

    public abstract StatDao statDao();

}

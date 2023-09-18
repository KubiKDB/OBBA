package com.daniel.obba.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.daniel.obba.entities.Statistics;

import java.util.List;

@Dao
public interface StatDao {
    @Query("SELECT * FROM statistics ORDER BY ID DESC ")
    List<Statistics> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStat(Statistics stats);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void changeStat(Statistics stats);
}

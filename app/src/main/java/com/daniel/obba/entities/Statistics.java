package com.daniel.obba.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "statistics")
public class Statistics implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "games_played")
    private int games_played = 0;

    @ColumnInfo(name = "word_string")
    private String word_string;

    @ColumnInfo(name = "int_string")
    private String int_string;

    @ColumnInfo(name = "words_number")
    private int words_number = 0;

    @ColumnInfo(name = "played_today")
    private boolean played_today;

    @ColumnInfo(name = "stats_3")
    private int stats_3 = 0;

    @ColumnInfo(name = "stats_4")
    private int stats_4 = 0;

    @ColumnInfo(name = "stats_5")
    private int stats_5 = 0;

    @ColumnInfo(name = "stats_6")
    private int stats_6 = 0;

    @ColumnInfo(name = "stats_7")
    private int stats_7 = 0;

    @ColumnInfo(name = "today_words_number")
    private int today_words_number = 0;

    @ColumnInfo(name = "today_3")
    private int today_3 = 0;

    @ColumnInfo(name = "today_4")
    private int today_4 = 0;

    @ColumnInfo(name = "today_5")
    private int today_5 = 0;

    @ColumnInfo(name = "today_6")
    private int today_6 = 0;

    @ColumnInfo(name = "today_7")
    private int today_7 = 0;

    public int getGames_played() {
        return games_played;
    }

    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord_string() {
        return word_string;
    }

    public void setWord_string(String word_string) {
        this.word_string = word_string;
    }

    public int getWords_number() {
        return words_number;
    }

    public String getInt_string() {
        return int_string;
    }

    public int getStats_3() {
        return stats_3;
    }

    public void setStats_3(int stats_3) {
        this.stats_3 = stats_3;
    }

    public int getStats_4() {
        return stats_4;
    }

    public void setStats_4(int stats_4) {
        this.stats_4 = stats_4;
    }

    public int getStats_5() {
        return stats_5;
    }

    public void setStats_5(int stats_5) {
        this.stats_5 = stats_5;
    }

    public int getStats_6() {
        return stats_6;
    }

    public void setStats_6(int stats_6) {
        this.stats_6 = stats_6;
    }

    public int getStats_7() {
        return stats_7;
    }

    public void setStats_7(int stats_7) {
        this.stats_7 = stats_7;
    }

    public int getToday_words_number() {
        return today_words_number;
    }

    public void setToday_words_number(int today_words_number) {
        this.today_words_number = today_words_number;
    }

    public int getToday_3() {
        return today_3;
    }

    public void setToday_3(int today_3) {
        this.today_3 = today_3;
    }

    public int getToday_4() {
        return today_4;
    }

    public void setToday_4(int today_4) {
        this.today_4 = today_4;
    }

    public int getToday_5() {
        return today_5;
    }

    public void setToday_5(int today_5) {
        this.today_5 = today_5;
    }

    public int getToday_6() {
        return today_6;
    }

    public void setToday_6(int today_6) {
        this.today_6 = today_6;
    }

    public int getToday_7() {
        return today_7;
    }

    public void setToday_7(int today_7) {
        this.today_7 = today_7;
    }

    public boolean isPlayed_today() {
        return played_today;
    }

    public void setPlayed_today(boolean played_today) {
        this.played_today = played_today;
    }

    public void setInt_string(String int_string) {
        this.int_string = int_string;
    }

    public void setWords_number(int words_number) {
        this.words_number = words_number;
    }
}

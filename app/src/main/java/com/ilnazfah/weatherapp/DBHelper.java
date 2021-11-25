package com.ilnazfah.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DBHelper extends AppCompatActivity {
    private SQLiteDatabase db;
    private String tableName;

    public void createDataBase (String dataBaseName) {
        db = getBaseContext().openOrCreateDatabase(dataBaseName, MODE_PRIVATE, null);
    }

    public void createTable (String tableName) {
        this.tableName = tableName;
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (city varchar(50))", tableName));
    }

    public void insert (String value) {
        db.execSQL(String.format("INSERT OR IGNORE INTO %s VALUES ('%s');", this.tableName, value));
    }

    public void close () {
        db.close();
    }
}
package com.ilnazfah.weatherapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseEditor {
    private SQLiteDatabase db;
    private String tableName;
    private final Context adapterContext;

    public DataBaseEditor(Context adapterContext) {
        this.adapterContext = adapterContext;
    }

    public void createDataBase (String dataBaseName) {
        db = adapterContext.openOrCreateDatabase(dataBaseName, MODE_PRIVATE, null);
    }

    public void createTable (String tableName) {
        this.tableName = tableName;
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (city varchar(50) UNIQUE)", tableName));
    }

    public void insert (String value) {
        db.execSQL(String.format("INSERT OR IGNORE INTO %s VALUES ('%s');", this.tableName, value));
    }

    public void close () {
        db.close();
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
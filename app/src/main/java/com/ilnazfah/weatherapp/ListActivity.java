package com.ilnazfah.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class ListActivity extends AppCompatActivity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.ilnazfah.weatherapp/databases/cities.db", null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Cursor query = db.rawQuery("SELECT * FROM cities;", null);
        TextView textView = findViewById(R.id.textView);
        textView.setText("");
        while(query.moveToNext()){
            String name = query.getString(0);
            textView.append(name + "\n");
        }
        query.close();
        db.close();
    }
}
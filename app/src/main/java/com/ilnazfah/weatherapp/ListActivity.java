package com.ilnazfah.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.ilnazfah.weatherapp/databases/cities.db", null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        List<String> cityList = new ArrayList<>();

        Cursor query = db.rawQuery("SELECT * FROM cities;", null);
        ListView listView = findViewById(R.id.textView);

        while(query.moveToNext()){
            String name = query.getString(0);
            cityList.add(name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.list_view_prop, cityList);
        listView.setAdapter(adapter);
        query.close();
        db.close();
    }
}
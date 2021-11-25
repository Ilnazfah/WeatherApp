package com.ilnazfah.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ilnazfah.weatherapp.model.Root;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.ilnazfah.weatherapp/databases/cities.db", null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        list = findViewById(R.id.list);
        List<String> cityList = new ArrayList<>();
        Cursor query = db.rawQuery("SELECT * FROM cities;", null);

        while(query.moveToNext()){
            String name = query.getString(0);
            cityList.add(name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.list_view_prop, cityList);
        list.setAdapter(adapter);
        query.close();
        db.close();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String city = cityList.get(position);
                System.out.println(city);
                String key = "2e4a2a95e9810de9669c244ff818b5ad";
                String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=ru",
                        city,
                        key);
                Intent intent = new Intent(v.getContext(), ResultActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }
}
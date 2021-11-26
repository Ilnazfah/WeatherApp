package com.ilnazfah.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ilnazfah.weatherapp.R;

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
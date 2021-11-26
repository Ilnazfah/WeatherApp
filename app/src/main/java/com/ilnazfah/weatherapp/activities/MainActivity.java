package com.ilnazfah.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ilnazfah.weatherapp.DataBaseEditor;
import com.ilnazfah.weatherapp.R;

public class MainActivity extends AppCompatActivity {

    private Button search;
    private Button list_btn;
    private Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
        list_btn = findViewById(R.id.list_btn);
        add_btn = findViewById(R.id.add_btn);

        DataBaseEditor dataBaseEditor = new DataBaseEditor(getApplicationContext());
        dataBaseEditor.createDataBase("cities.db");
        dataBaseEditor.createTable("cities");
        dataBaseEditor.insert("Казань");
        dataBaseEditor.insert("Набережные челны");
        dataBaseEditor.insert("Елабуга");
        dataBaseEditor.close();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddActivity.class);
                startActivity(intent);
            }
        });
    }
}
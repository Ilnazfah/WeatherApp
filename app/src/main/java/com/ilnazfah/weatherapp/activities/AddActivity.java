package com.ilnazfah.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ilnazfah.weatherapp.R;

public class AddActivity extends AppCompatActivity {
    private Button main_btn;
    private SQLiteDatabase db;
    private EditText user_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        user_field = findViewById(R.id.user_field);
        main_btn = findViewById(R.id.main_btn);

        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.ilnazfah.weatherapp/databases/cities.db", null);
                db.execSQL(String.format("INSERT OR IGNORE INTO %s VALUES ('%s');", "cities", user_field.getText().toString()));
                db.close();
                Toast.makeText(AddActivity.this, "Город добавлен!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
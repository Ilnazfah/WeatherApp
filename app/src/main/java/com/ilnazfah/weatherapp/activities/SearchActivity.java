package com.ilnazfah.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ilnazfah.weatherapp.DataBaseEditor;
import com.ilnazfah.weatherapp.GsonParser;
import com.ilnazfah.weatherapp.R;
import com.ilnazfah.weatherapp.model.Root;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchActivity extends AppCompatActivity {

    private EditText user_field;
    private Button main_btn;
    private TextView result_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        user_field = findViewById(R.id.user_field);
        main_btn = findViewById(R.id.main_btn);
        result_info = findViewById(R.id.result_info);

        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_field.getText().toString().trim().equals("")) {
                    Toast.makeText(SearchActivity.this, R.string.no_user_input, Toast.LENGTH_LONG).show();
                } else {
                    String city = user_field.getText().toString();
                    String key = "2e4a2a95e9810de9669c244ff818b5ad";
                    String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=ru",
                            city,
                            key);
                    new GetUrlData().execute(url);
                }
            }
        });
    }

    private class GetUrlData extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            result_info.setText("Ожидание...");
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                    return buffer.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            GsonParser gsonParser = new GsonParser(result);
            Root root = gsonParser.parse();
            super.onPostExecute(result);
            result_info.setText(String.format("%s\n" +
                            "%s\n" +
                            "Температура: %s\n" +
                            "Ветер %s м/с",
                    root.getName(),
                    root.getWeather().get(0).getDescription(),
                    root.getMain().getTemp(),
                    root.getWind().getSpeed()));
        }
    }


}
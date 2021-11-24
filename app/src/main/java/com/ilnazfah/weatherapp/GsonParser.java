package com.ilnazfah.weatherapp;

import com.google.gson.Gson;
import com.ilnazfah.weatherapp.model.Root;

public class GsonParser {
    private final String jsonData;

    public GsonParser(String jsonData) {
        this.jsonData = jsonData;
    }

    public Root parse() {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, Root.class);
    }
}
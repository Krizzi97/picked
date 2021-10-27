package com.example.picked.Database;

import android.location.Location;
import android.media.Image;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class PostPlant {

    public String name;
    public String city;
    public String state;
    public String zip;

    public PostPlant(String name, String city, String state, String zip) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    @Exclude
    public Map<String, String> toMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("name", name);
        result.put("city", city);
        result.put("state", state);
        result.put("zip", zip);

        return result;
    }
}

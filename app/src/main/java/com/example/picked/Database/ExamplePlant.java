package com.example.picked.Database;

import android.media.Image;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamplePlant {
    String name;
    String region;
    String season;
    String area;
    String part;

    public ExamplePlant(String name, String region, String season, String area, String part) {
        this.name = name;
        this.region = region;
        this.season = season;
        this.area = area;
        this.part = part;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("regions", region);
        result.put("seasons", season);
        result.put("areas", area);
        result.put("parts", part);

        return result;
    }
}

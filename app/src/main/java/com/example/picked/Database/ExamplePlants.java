package com.example.picked.Database;

import android.media.Image;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamplePlants {
    String name;
    List<String> regions;
    List<String> seasons;
    List<String> areas;
    List<String> parts;
    List<String> characteristics;
    List<Image> images;
    String warnings;

    public ExamplePlants() { }

    public ExamplePlants(String name, List<String> regions, List<String> seasons, List<String> areas, List<String> parts, List<String> characteristcs, List<Image> images, String warnings) {
        this.name = name;
        this.regions = regions;
        this.seasons = seasons;
        this.areas = areas;
        this.parts = parts;
        this.characteristics = characteristcs;
        this.images = images;
        this.warnings = warnings;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("regions", regions);
        result.put("seasons", seasons);
        result.put("areas", areas);
        result.put("parts", parts);
        result.put("characteristics", characteristics);
        result.put("images", images);
        result.put("warnings", warnings);

        return result;
    }
}

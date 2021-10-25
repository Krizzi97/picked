package com.example.picked;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import timber.log.Timber;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class PlantPartFragment extends Fragment implements View.OnClickListener{
    enum parts{
        Greens,
        Fruits,
        Seeds,
        Flower,
        Underground,
        Shoots
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plant_part, container,false);

        Button GreensButton = view.findViewById(R.id.GreenButton);
        GreensButton.setOnClickListener(this);
        Button FruitButton = view.findViewById(R.id.FruitButton);
        FruitButton.setOnClickListener(this);
        Button SeedButton = view.findViewById(R.id.SeedsButton);
        SeedButton.setOnClickListener(this);
        Button FlowerButton = view.findViewById(R.id.FlowerButton);
        FlowerButton.setOnClickListener(this);
        Button UndergroundButton = view.findViewById(R.id.UndergroundButton);
        UndergroundButton.setOnClickListener(this);
        Button ShootButton = view.findViewById(R.id.ShootButton);
        ShootButton.setOnClickListener(this);
        Button otherButton = view.findViewById(R.id.OtherButton);
        otherButton.setOnClickListener(this);
        Button submitButton = view.findViewById(R.id.SubmitButton);
        submitButton.setOnClickListener(this);

        return view;
    }

    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        SharedPreferences sharedPref = activity.getSharedPreferences("plant_part", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if (viewId == R.id.GreenButton) {
            editor.putString(getString(R.string.selected_part), parts.Greens.toString());
            editor.apply();
        } else if (viewId == R.id.FruitButton) {
            editor.putString(getString(R.string.selected_part), parts.Fruits.toString());
            editor.apply();
        } else if (viewId == R.id.FlowerButton) {
            editor.putString(getString(R.string.selected_part), parts.Flower.toString());
            editor.apply();
        } else if (viewId == R.id.SeedsButton) {
            editor.putString(getString(R.string.selected_part), parts.Seeds.toString());
            editor.apply();
        } else if (viewId == R.id.ShootButton) {
            editor.putString(getString(R.string.selected_part), parts.Shoots.toString());
            editor.apply();
        } else if (viewId == R.id.UndergroundButton) {
            editor.putString(getString(R.string.selected_part), parts.Underground.toString());
            editor.apply();
        } else if (viewId == R.id.OtherButton) {
            editor.putString(getString(R.string.selected_part), "other");
            editor.apply();
        } else if (viewId == R.id.SubmitButton) {
            startActivity(new Intent(appContext, LogActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
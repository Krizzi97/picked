package com.example.picked;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import timber.log.Timber;

public class TypeSearchFragment extends Fragment implements View.OnClickListener{

    private int selected;
    private int[] allButtons = new int[6];
    private View view;
    private Button submitButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_type, container, false);

        Button GreensButton = view.findViewById(R.id.GreenButton);
        GreensButton.setOnClickListener(this);
        allButtons[0] = GreensButton.getId();
        Button FruitButton = view.findViewById(R.id.FruitButton);
        FruitButton.setOnClickListener(this);
        allButtons[1] = FruitButton.getId();
        Button SeedButton = view.findViewById(R.id.SeedsButton);
        SeedButton.setOnClickListener(this);
        allButtons[2] = SeedButton.getId();
        Button FlowerButton = view.findViewById(R.id.FlowerButton);
        FlowerButton.setOnClickListener(this);
        allButtons[3] = FlowerButton.getId();
        Button UndergroundButton = view.findViewById(R.id.UndergroundButton);
        UndergroundButton.setOnClickListener(this);
        allButtons[4] = UndergroundButton.getId();
        Button ShootButton = view.findViewById(R.id.ShootButton);
        ShootButton.setOnClickListener(this);
        allButtons[5] = ShootButton.getId();
        submitButton = view.findViewById(R.id.SubmitButton);
        submitButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        SharedPreferences sharedPref = activity.getSharedPreferences("plant_part", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        selected = viewId;
        view.setSelected(true);
        DeselectButtons();

        if (viewId == R.id.GreenButton) {
            editor.putString(getString(R.string.selected_part), "green");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.FruitButton) {
            editor.putString(getString(R.string.selected_part), "fruit");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.FlowerButton) {
            editor.putString(getString(R.string.selected_part), "flower");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.SeedsButton) {
            editor.putString(getString(R.string.selected_part), "seed");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.ShootButton) {
            editor.putString(getString(R.string.selected_part), "shoot");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.UndergroundButton) {
            editor.putString(getString(R.string.selected_part), "underground");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.SubmitButton) {
            // Query selected part with list of matching entries
        } else {
            Timber.e("Invalid button click");
        }
    }
    // source: https://stackoverflow.com/a/2060708
    private void DeselectButtons(){
        for (int button : allButtons)
        {
            if (button != selected)
            {
                view.findViewById(button).setSelected(false);
            }
        }
    }
}
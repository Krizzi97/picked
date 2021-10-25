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

public class SeasonFragment extends Fragment implements View.OnClickListener{
    enum seasons{
        EarlySpring,
        MidToLateSpring,
        Summer,
        Fall,
        Winter
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_season, container,false);

        Button EarlySpringButton = view.findViewById(R.id.EarlySpringButton);
        EarlySpringButton.setOnClickListener(this);
        Button MidLateSpringButton = view.findViewById(R.id.MidtoLateSpringButton);
        MidLateSpringButton.setOnClickListener(this);
        Button SummerButton = view.findViewById(R.id.SummerButton);
        SummerButton.setOnClickListener(this);
        Button FallButton = view.findViewById(R.id.FallButton);
        FallButton.setOnClickListener(this);
        Button WinterButton = view.findViewById(R.id.WinterButton);
        WinterButton.setOnClickListener(this);
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

        SharedPreferences sharedPref = activity.getSharedPreferences("season", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if (viewId == R.id.EarlySpringButton) {
            editor.putString(getString(R.string.selected_season), seasons.EarlySpring.toString());
            editor.apply();
        } else if (viewId == R.id.MidtoLateSpringButton) {
            editor.putString(getString(R.string.selected_season), seasons.MidToLateSpring.toString());
            editor.apply();
        } else if (viewId == R.id.SummerButton) {
            editor.putString(getString(R.string.selected_season), seasons.Summer.toString());
            editor.apply();
        } else if (viewId == R.id.FallButton) {
            editor.putString(getString(R.string.selected_season), seasons.Fall.toString());
            editor.apply();
        } else if (viewId == R.id.WinterButton) {
            editor.putString(getString(R.string.selected_season), seasons.Winter.toString());
            editor.apply();
        } else if (viewId == R.id.OtherButton) {
            editor.putString(getString(R.string.selected_season), "other");
            editor.apply();
        } else if (viewId == R.id.SubmitButton) {
            startActivity(new Intent(appContext, PlantPartsActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
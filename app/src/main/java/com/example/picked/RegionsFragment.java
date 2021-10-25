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


public class RegionsFragment extends Fragment implements View.OnClickListener{

    enum region{
       Midwest,
       EasternSeaboard,
       Southeast
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regions, container,false);

        Button midwestButton = view.findViewById(R.id.MidwestButton);
        midwestButton.setFocusable(true);
        midwestButton.setOnClickListener(this);
        Button seaboardButton = view.findViewById(R.id.SeaboardButton);
        seaboardButton.setFocusable(true);
        seaboardButton.setOnClickListener(this);
        Button southeastButton = view.findViewById(R.id.SouthEastButton);
        southeastButton.setOnClickListener(this);
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

        SharedPreferences sharedPref = activity.getSharedPreferences("region", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if (viewId == R.id.MidwestButton) {
            editor.putString(getString(R.string.selected_region), region.Midwest.toString());
            editor.apply();
        } else if (viewId == R.id.SeaboardButton) {
            editor.putString(getString(R.string.selected_region), region.EasternSeaboard.toString());
            editor.apply();
        } else if (viewId == R.id.SouthEastButton) {
            editor.putString(getString(R.string.selected_region), region.Southeast.toString());
            editor.apply();
        } else if (viewId == R.id.OtherButton) {
            editor.putString(getString(R.string.selected_region), "other");
            editor.apply();
        } else if (viewId == R.id.SubmitButton) {
            startActivity(new Intent(appContext, AreasActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
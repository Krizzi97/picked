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

public class AreasFragment extends Fragment implements View.OnClickListener{
    enum areas{
        FullSun,
        NearWater,
        PartialShade
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_areas, container,false);

        Button fullSunButton = view.findViewById(R.id.FullSunButton);
        fullSunButton.setOnClickListener(this);
        Button partialShadeButton = view.findViewById(R.id.PartialShadeButton);
        partialShadeButton.setOnClickListener(this);
        Button nearWaterButton = view.findViewById(R.id.NearWaterButton);
        nearWaterButton.setOnClickListener(this);
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

        SharedPreferences sharedPref = activity.getSharedPreferences("area", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if (viewId == R.id.FullSunButton) {
            editor.putString(getString(R.string.selected_area), areas.FullSun.toString());
            editor.apply();
        } else if (viewId == R.id.PartialShadeButton) {
            editor.putString(getString(R.string.selected_area), areas.PartialShade.toString());
            editor.apply();
        } else if (viewId == R.id.NearWaterButton) {
            editor.putString(getString(R.string.selected_area), areas.NearWater.toString());
            editor.apply();
        } else if (viewId == R.id.OtherButton) {
            editor.putString(getString(R.string.selected_area), "other");
            editor.apply();
        } else if (viewId == R.id.SubmitButton) {
            startActivity(new Intent(appContext, SeasonActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
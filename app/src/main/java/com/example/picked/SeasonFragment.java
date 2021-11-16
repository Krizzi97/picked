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

    private int selected;
    private int[] allButtons = new int[5];
    private View view;
    private Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_season, container,false);

        Button EarlySpringButton = view.findViewById(R.id.EarlySpringButton);
        EarlySpringButton.setOnClickListener(this);
        allButtons[0] = EarlySpringButton.getId();
        Button MidLateSpringButton = view.findViewById(R.id.MidtoLateSpringButton);
        MidLateSpringButton.setOnClickListener(this);
        allButtons[1] = MidLateSpringButton.getId();
        Button SummerButton = view.findViewById(R.id.SummerButton);
        SummerButton.setOnClickListener(this);
        allButtons[2] = SummerButton.getId();
        Button FallButton = view.findViewById(R.id.FallButton);
        FallButton.setOnClickListener(this);
        allButtons[3] = FallButton.getId();
        Button WinterButton = view.findViewById(R.id.WinterButton);
        WinterButton.setOnClickListener(this);
        allButtons[4] = WinterButton.getId();
        submitButton = view.findViewById(R.id.SubmitButton);
        submitButton.setOnClickListener(this);

        return view;
    }

    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        SharedPreferences sharedPref = activity.getSharedPreferences("season", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        selected = viewId;
        view.setSelected(true);
        DeselectButtons();

        if (viewId == R.id.EarlySpringButton) {
            editor.putString(getString(R.string.selected_season), "early spring");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.MidtoLateSpringButton) {
            editor.putString(getString(R.string.selected_season), "mid-to-late spring");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.SummerButton) {
            editor.putString(getString(R.string.selected_season), "summer");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.FallButton) {
            editor.putString(getString(R.string.selected_season), "fall");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.WinterButton) {
            editor.putString(getString(R.string.selected_season), "winter");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.SubmitButton) {
            startActivity(new Intent(appContext, PlantPartsActivity.class));
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
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

    private int selected;
    private int[] allButtons = new int[3];
    private View view;
    private Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_regions, container,false);

        Button midwestButton = view.findViewById(R.id.MidwestButton);
        midwestButton.setOnClickListener(this);
        allButtons[0] = midwestButton.getId();
        Button seaboardButton = view.findViewById(R.id.SeaboardButton);
        seaboardButton.setOnClickListener(this);
        allButtons[1] = seaboardButton.getId();
        Button southeastButton = view.findViewById(R.id.SouthEastButton);
        southeastButton.setOnClickListener(this);
        allButtons[2] = southeastButton.getId();
        submitButton = view.findViewById(R.id.SubmitButton);
        submitButton.setOnClickListener(this);

        return view;
    }

    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        SharedPreferences sharedPref = activity.getSharedPreferences("region", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        selected = viewId;
        view.setSelected(true);
        DeselectButtons();

        if (viewId == R.id.MidwestButton) {
            editor.putString(getString(R.string.selected_region), "midwest");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.SeaboardButton) {
            editor.putString(getString(R.string.selected_region), "seaboard");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.SouthEastButton) {
            editor.putString(getString(R.string.selected_region), "south east");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.SubmitButton) {
            startActivity(new Intent(appContext, AreasActivity.class));
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
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

    private int selected;
    private int[] allButtons = new int[3];
    private View view;
    private Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_areas, container,false);

        Button fullSunButton = view.findViewById(R.id.FullSunButton);
        fullSunButton.setOnClickListener(this);
        allButtons[0] = fullSunButton.getId();
        Button partialShadeButton = view.findViewById(R.id.PartialShadeButton);
        partialShadeButton.setOnClickListener(this);
        allButtons[1] = partialShadeButton.getId();
        Button nearWaterButton = view.findViewById(R.id.NearWaterButton);
        nearWaterButton.setOnClickListener(this);
        allButtons[2] = nearWaterButton.getId();
        submitButton = view.findViewById(R.id.SubmitButton);
        submitButton.setOnClickListener(this);

        return view;
    }

    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        SharedPreferences sharedPref = activity.getSharedPreferences("area", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        selected = viewId;
        view.setSelected(true);
        DeselectButtons();

        if (viewId == R.id.FullSunButton) {
            editor.putString(getString(R.string.selected_area), "full sun");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.PartialShadeButton) {
            editor.putString(getString(R.string.selected_area), "partial shade");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.NearWaterButton) {
            editor.putString(getString(R.string.selected_area), "near water");
            editor.apply();
            submitButton.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.SubmitButton) {
            startActivity(new Intent(appContext, SeasonActivity.class));
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
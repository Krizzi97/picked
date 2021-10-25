package com.example.picked;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import timber.log.Timber;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.picked.Database.PickedDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class LogFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log, container, false);

        final Activity activity = requireActivity();

        SharedPreferences regionPref = activity.getSharedPreferences("region", activity.MODE_PRIVATE);
        String region = regionPref.getString(getString(R.string.selected_region), "none selected");

        SharedPreferences seasonPref = activity.getSharedPreferences("season", activity.MODE_PRIVATE);
        String season = seasonPref.getString(getString(R.string.selected_season), "none selected");

        SharedPreferences areaPref = activity.getSharedPreferences("area", activity.MODE_PRIVATE);
        String area = areaPref.getString(getString(R.string.selected_area), "none selected");

        SharedPreferences partPref = activity.getSharedPreferences("plant_part", activity.MODE_PRIVATE);
        String plant_part = partPref.getString(getString(R.string.selected_part), "none selected");

        final TextView options = (TextView) v.findViewById(R.id.choices);
//        options.setText(region + "\n" + season + "\n" + area + "\n" + plant_part);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        PickedDatabase queries = new PickedDatabase(database);
        options.setText(queries.getMultiplePlants());

//        Button submitButton = v.findViewById(R.id.SubmitButton);
//        submitButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        if (viewId == R.id.SubmitButton) {
            startActivity(new Intent(appContext, LogActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
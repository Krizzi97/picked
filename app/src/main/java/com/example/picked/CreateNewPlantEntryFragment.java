package com.example.picked;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import timber.log.Timber;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picked.Database.PickedDatabase;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class CreateNewPlantEntryFragment extends Fragment implements View.OnClickListener{
    DatabaseReference reference;
    TextView located;
    // WARNING: THIS CODE IS NOT FLEXIBLE !!!


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_new_plant_entry, container, false);

        final Activity activity = requireActivity();

        SharedPreferences plantPref = activity.getSharedPreferences("plant", activity.MODE_PRIVATE);
        String plant = plantPref.getString(getString(R.string.selected_plant), " ");

        located = (TextView) v.findViewById(R.id.location);
        TextView selected = (TextView) v.findViewById(R.id.name);
        selected.setText(plant);

        Button locationButton = v.findViewById(R.id.find_me);
        locationButton.setOnClickListener(this);
        Button submitButton = v.findViewById(R.id.submit);
        submitButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        SharedPreferences location = activity.getSharedPreferences("location", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = location.edit();

        if (viewId == R.id.find_me) {
            String found_location = "Columbus,OH,43210";
            located.setText(found_location);
            editor.putString(getString(R.string.found_location), found_location);
            editor.apply();
        } else if (viewId == R.id.submit) {
            Toast.makeText(appContext, "SUCCESS!!", Toast.LENGTH_LONG);
            startActivity(new Intent(appContext, MainScreenActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
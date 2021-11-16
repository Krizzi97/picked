package com.example.picked;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import androidx.fragment.app.Fragment;
import timber.log.Timber;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.picked.Database.PickedDatabase;
import com.example.picked.Database.PostPlant;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateNewPlantEntryFragment extends Fragment implements View.OnClickListener{

    DatabaseReference reference;
    String plant;
    Button submitButton;
    TextView located;

    // WARNING: THIS CODE IS NOT FLEXIBLE !!!

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_new_plant_entry, container, false);

        final Activity activity = requireActivity();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("savedPlant");

        SharedPreferences plantPref = activity.getSharedPreferences("plant", activity.MODE_PRIVATE);
        plant = plantPref.getString(getString(R.string.selected_plant), " ");

        TextView selected = (TextView) v.findViewById(R.id.name);
        selected.setText(plant);
        located = (TextView) v.findViewById(R.id.location);

        SharedPreferences locationPref = activity.getSharedPreferences("location", activity.MODE_PRIVATE);
        String savedLocation = locationPref.getString(getString(R.string.found_location), " ");
        String[] address = savedLocation.split(",");

        located.setText(address[0] + ", " + address[1] + ", " + address[2]);

        submitButton = v.findViewById(R.id.submit);
        submitButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        if (viewId == R.id.submit) {

            SharedPreferences locationPref = activity.getSharedPreferences("location", activity.MODE_PRIVATE);
            String savedLocation = locationPref.getString(getString(R.string.found_location), " ");
            String[] address = savedLocation.split(",");

            // simplify with PostPlant.class
            DatabaseReference newPost = reference.push();
            newPost.child("name").setValue(plant);
            newPost.child("location").child("city").setValue(address[0]);
            newPost.child("location").child("state").setValue(address[1]);
            newPost.child("location").child("zip").setValue(address[2]);

            startActivity(new Intent(appContext, MainScreenActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
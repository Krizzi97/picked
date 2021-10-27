package com.example.picked;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
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
import com.example.picked.Database.PostPlant;
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
import java.util.Locale;
import java.util.Map;

public class CreateNewPlantEntryFragment extends Fragment implements View.OnClickListener{
    DatabaseReference reference;
    TextView located;
    String plant;
    // WARNING: THIS CODE IS NOT FLEXIBLE !!!


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_new_plant_entry, container, false);

        final Activity activity = requireActivity();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("savedPlant");

        SharedPreferences plantPref = activity.getSharedPreferences("plant", activity.MODE_PRIVATE);
        plant = plantPref.getString(getString(R.string.selected_plant), " ");

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

        if (viewId == R.id.find_me) {
            // LoggingLocationServiceActivity started here

            SharedPreferences location = activity.getSharedPreferences("location", activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = location.edit();
            String address = "Columbus,OH,43210";
            located.setText(address);
            editor.putString(getString(R.string.found_location), address);
            editor.apply();
        } else if (viewId == R.id.submit) {

            SharedPreferences locationPref = activity.getSharedPreferences("location", activity.MODE_PRIVATE);
            String savedLocation = locationPref.getString(getString(R.string.found_location), " ");
            String[] address = savedLocation.split(",");

            SharedPreferences previousPostsPref = activity.getSharedPreferences("lastPost", activity.MODE_PRIVATE);
            String previousPosts = previousPostsPref.getString(getString(R.string.last_post), " ");

            // simplify with PostPlant.class
            DatabaseReference newPost = reference.push();
            newPost.child("name").setValue(plant);
            newPost.child("location").child("city").setValue(address[0]);
            newPost.child("location").child("state").setValue(address[1]);
            newPost.child("location").child("zip").setValue(address[2]);

            SharedPreferences lastPost = activity.getSharedPreferences("lastPost", activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = lastPost.edit();
            editor.putString(getString(R.string.last_post), previousPosts + "," + newPost.getKey());
            editor.apply();

            startActivity(new Intent(appContext, EditPostsActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
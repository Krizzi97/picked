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

public class LogFragment extends Fragment implements View.OnClickListener{
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    TextView available;
    List<Button> plant_names;
    DatabaseReference reference;
    SharedPreferences plant;
    SharedPreferences.Editor editor;

    // WARNING: THIS CODE IS NOT FLEXIBLE !!!

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log, container, false);

        final Activity activity = requireActivity();

        plant = activity.getSharedPreferences("plant", activity.MODE_PRIVATE);
        editor = plant.edit();

        SharedPreferences regionPref = activity.getSharedPreferences("region", activity.MODE_PRIVATE);
        String region = regionPref.getString(getString(R.string.selected_region), "none selected");

        SharedPreferences seasonPref = activity.getSharedPreferences("season", activity.MODE_PRIVATE);
        String season = seasonPref.getString(getString(R.string.selected_season), "none selected");

        SharedPreferences areaPref = activity.getSharedPreferences("area", activity.MODE_PRIVATE);
        String area = areaPref.getString(getString(R.string.selected_area), "none selected");

        SharedPreferences partPref = activity.getSharedPreferences("plant_part", activity.MODE_PRIVATE);
        String plant_part = partPref.getString(getString(R.string.selected_part), "none selected");

        available = (TextView) v.findViewById(R.id.available);
        option1 = (Button) v.findViewById(R.id.plant1);
        option2 = (Button) v.findViewById(R.id.plant2);
        option3 = (Button) v.findViewById(R.id.plant3);
        option4 = (Button) v.findViewById(R.id.plant4);
        plant_names = new ArrayList<>();
        plant_names.add(option1);
        plant_names.add(option2);
        plant_names.add(option3);
        plant_names.add(option4);

        reference = FirebaseDatabase.getInstance().getReference().child("Plants");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    available.setVisibility(View.GONE);
                    int count = 0;
                    for (int i = 0; i < snapshot.getChildrenCount(); i++) {
                        plant_names.get(i).setVisibility(View.VISIBLE);
                        plant_names.get(i).setText(snapshot.child(i + "").child("name").getValue().toString());
                    }
                }
                else
                {
                    available.setText("No plant matches your description :(");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button submitButton = v.findViewById(R.id.submit);
        submitButton.setOnClickListener(this);
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        if (viewId == R.id.plant1) {
            editor.putString(getString(R.string.selected_plant), option1.getText().toString());
            editor.apply();
        } else if (viewId == R.id.plant2) {
            editor.putString(getString(R.string.selected_plant), option2.getText().toString());
            editor.apply();
        } else if (viewId == R.id.plant3) {
            editor.putString(getString(R.string.selected_plant), option3.getText().toString());
            editor.apply();
        } else if (viewId == R.id.plant4) {
            editor.putString(getString(R.string.selected_plant), option4.getText().toString());
            editor.apply();
        } else if (viewId == R.id.submit) {
            startActivity(new Intent(appContext, CreateNewPlantEntryActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
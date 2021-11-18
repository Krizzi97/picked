package com.example.picked;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class LocationResultsFragment extends Fragment implements View.OnClickListener{

    DatabaseReference reference;
    String location_search;
    TextView noResults;
    TextView name1;
    TextView name2;
    TextView name3;
    TextView name4;
    TextView location1;
    TextView location2;
    TextView location3;
    TextView location4;
    CardView card1;
    CardView card2;
    CardView card3;
    CardView card4;
    List<TextView> plant_names;
    List<TextView> plant_locations;
    List<CardView> plant_cards;
    List<String> matches;
    String[] cityState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_location_results, container, false);

        final Activity activity = requireActivity();

        Button homeButton = v.findViewById(R.id.home);
        homeButton.setOnClickListener(this);
        cityState = new String[2];

        noResults = (TextView) v.findViewById(R.id.no_results);

        card1 = (CardView) v.findViewById(R.id.card1);
        card2 = (CardView) v.findViewById(R.id.card2);
        card3 = (CardView) v.findViewById(R.id.card3);
        card4 = (CardView) v.findViewById(R.id.card4);
        plant_cards = new ArrayList<>();
        plant_cards.add(card1);
        plant_cards.add(card2);
        plant_cards.add(card3);
        plant_cards.add(card4);

        name1 = (TextView) v.findViewById(R.id.plant1_name);
        name2 = (TextView) v.findViewById(R.id.plant2_name);
        name3 = (TextView) v.findViewById(R.id.plant3_name);
        name4 = (TextView) v.findViewById(R.id.plant4_name);
        plant_names = new ArrayList<>();
        plant_names.add(name1);
        plant_names.add(name2);
        plant_names.add(name3);
        plant_names.add(name4);

        location1 = (TextView) v.findViewById(R.id.plant1_location);
        location2 = (TextView) v.findViewById(R.id.plant2_location);
        location3 = (TextView) v.findViewById(R.id.plant3_location);
        location4 = (TextView) v.findViewById(R.id.plant4_location);
        plant_locations = new ArrayList<>();
        plant_locations.add(location1);
        plant_locations.add(location2);
        plant_locations.add(location3);
        plant_locations.add(location4);

        matches = new ArrayList<>();

        SharedPreferences locatePref = activity.getSharedPreferences("location_search", activity.MODE_PRIVATE);
        location_search = locatePref.getString(getString(R.string.selected_part), "none selected");

        cityState = location_search.split(",");
        reference = FirebaseDatabase.getInstance().getReference().child("savedPlant");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;
                if (snapshot.hasChildren()) {
                    for (DataSnapshot subSnap : snapshot.getChildren()) {
                        if (subSnap.child("location").child("city").getValue().toString().equalsIgnoreCase(cityState[0]) &&
                                subSnap.child("location").child("state").getValue().toString().equalsIgnoreCase(cityState[1])) {
                            if (count < plant_cards.size()) {
                                plant_cards.get(count).setVisibility(View.VISIBLE);
                                plant_names.get(count).setText(subSnap.child("name").getValue().toString());
                                String local = subSnap.child("location").child("city").getValue().toString() + ", " + subSnap.child("location").child("state").getValue().toString() + ", " + subSnap.child("location").child("zip").getValue().toString();
                                plant_locations.get(count).setText(local);
                                count++;
                            }
                            else
                            {
                                break;
                            }
                        }
                    }
                }
                if (!snapshot.hasChildren() || count == 0) {
                    noResults.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;
    }

    @Override
    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();
        if (viewId == R.id.home) {
            startActivity(new Intent(appContext, MainScreenActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
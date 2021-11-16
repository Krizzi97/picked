package com.example.picked;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import timber.log.Timber;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.picked.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditPostsFragment extends Fragment implements View.OnClickListener {
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
    DatabaseReference reference;
    String[] previousPosts;
    int count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_posts, container, false);
        final Activity activity = requireActivity();

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

        SharedPreferences previousPostsPref = activity.getSharedPreferences("lastPost", activity.MODE_PRIVATE);
        String previous = previousPostsPref.getString(getString(R.string.last_post), " ");
        previousPosts = previous.split(" ");

        count = 0;

        plant_cards.get(count).setVisibility(View.VISIBLE);
        plant_names.get(count).setText(previous);

        while (count < previousPosts.length && count < 3)
        {
            reference = FirebaseDatabase.getInstance().getReference().child("savedPlant").child(previousPosts[count]);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChildren()) {
                        plant_cards.get(count).setVisibility(View.VISIBLE);
                        plant_names.get(count).setText(snapshot.child("name").getValue().toString());

                        String local = snapshot.child("location").child("city").getValue().toString() + ", " + snapshot.child("location").child("state").getValue().toString() + ", " + snapshot.child("location").child("zip").getValue().toString();
                        plant_locations.get(count).setText(local);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    throw error.toException();
                }
            });

            count++;
        }

        Button homeButton = v.findViewById(R.id.home);
        homeButton.setOnClickListener(this);
        Button edit1Button = v.findViewById(R.id.edit_plant1);
        edit1Button.setOnClickListener(this);
        Button edit2Button = v.findViewById(R.id.edit_plant2);
        edit2Button.setOnClickListener(this);
        Button edit3Button = v.findViewById(R.id.edit_plant3);
        edit3Button.setOnClickListener(this);
        Button edit4Button = v.findViewById(R.id.edit_plant4);
        edit4Button.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        if (viewId == R.id.home) {
            startActivity(new Intent(appContext, MainScreenActivity.class));
        } else if (viewId == R.id.edit_plant1) {

        } else if (viewId == R.id.edit_plant2) {

        } else if (viewId == R.id.edit_plant3) {

        } else if (viewId == R.id.edit_plant4) {

        } else {
            Timber.e("Invalid button click");
        }
    }
}
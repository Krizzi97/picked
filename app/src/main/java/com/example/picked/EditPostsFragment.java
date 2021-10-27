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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditPostsFragment extends Fragment implements View.OnClickListener {
    TextView option1;
    TextView option2;
    TextView option3;
    TextView option4;
    List<TextView> plant_names;
    DatabaseReference reference;
    String[] previousPosts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_posts, container, false);
        final Activity activity = requireActivity();

        option1 = (TextView) v.findViewById(R.id.plant1);
        option2 = (TextView) v.findViewById(R.id.plant2);
        option3 = (TextView) v.findViewById(R.id.plant3);
        option4 = (TextView) v.findViewById(R.id.plant4);
        plant_names = new ArrayList<>();
        plant_names.add(option1);
        plant_names.add(option2);
        plant_names.add(option3);
        plant_names.add(option4);

        SharedPreferences previousPostsPref = activity.getSharedPreferences("lastPost", activity.MODE_PRIVATE);
        String previous = previousPostsPref.getString(getString(R.string.last_post), " ");
        previousPosts = previous.split(",");

        reference = FirebaseDatabase.getInstance().getReference().child("savedPlant");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for (int i = 0; i < snapshot.getChildrenCount(); i++) {
                        plant_names.get(i).setVisibility(View.VISIBLE);
                        plant_names.get(i).setText(snapshot.child("name").getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button homeButton = v.findViewById(R.id.home);
        homeButton.setOnClickListener(this);

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
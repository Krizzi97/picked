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
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class SearchFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Button name = view.findViewById(R.id.name_button);
        name.setOnClickListener(this);
        Button location = view.findViewById(R.id.location_button);
        location.setOnClickListener(this);
        Button type = view.findViewById(R.id.type_button);
        type.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        if (viewId == R.id.name_search) {
            startActivity(new Intent(appContext, NameSearchActivity.class));
        } else if (viewId == R.id.location_search) {
            startActivity(new Intent(appContext, LocationSearchActivity.class));
        } else if (viewId == R.id.type_search) {
            startActivity(new Intent(appContext, TypeSearchActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
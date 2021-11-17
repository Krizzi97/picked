package com.example.picked;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import timber.log.Timber;

public class LocationSearchCurrentFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_location, container, false);

        Button currentLocation = view.findViewById(R.id.user_location);
        currentLocation.setOnClickListener(this);
        Button inputLocation = view.findViewById(R.id.input_location);
        inputLocation.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        if (viewId == R.id.user_location) {
            // Not implemented yet
        } else if (viewId == R.id.input_location) {
            // Not implemented yet
        } else {
            Timber.e("Invalid button click");
        }
    }
}
package com.example.picked;

import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import timber.log.Timber;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainScreenFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_screen, container,false);

        Button logButton = view.findViewById(R.id.log_button);
        logButton.setOnClickListener(this);
        Button searchButton = view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(this);

        return view;
    }

    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        if (viewId == R.id.log_button) {
            startActivity(new Intent(appContext, LogActivity.class));
        } else if (viewId == R.id.search_button) {
            // not implemented
        } else {
            Timber.e("Invalid button click");
        }
    }
}
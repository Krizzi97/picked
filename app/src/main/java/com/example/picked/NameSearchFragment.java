package com.example.picked;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import timber.log.Timber;

public class NameSearchFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Button name = view.findViewById(R.id.name_button);
        name.setOnClickListener(this);
        Button location = view.findViewById(R.id.location_button);
        location.setOnClickListener(this);
        Button type = view.findViewById(R.id.type_button);
        location.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        if (viewId == R.id.name_search) {
            startActivity(new Intent(appContext, RegionsActivity.class));
        } else if (viewId == R.id.location_search) {
            startActivity(new Intent(appContext, SearchActivity.class));
        } else if (viewId == R.id.type_search) {
            startActivity(new Intent(appContext, SearchActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
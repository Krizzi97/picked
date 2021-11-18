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
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Locale;

import timber.log.Timber;

public class LocationSearchInputFragment extends Fragment implements View.OnClickListener{

    private Button submitButton;
    private EditText locationInput;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_location_input, container, false);

        locationInput = (EditText) view.findViewById(R.id.txt_box);
        locationInput.setOnClickListener(this);
        submitButton = view.findViewById(R.id.SubmitButton);
        submitButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        SharedPreferences sharedName= activity.getSharedPreferences("location_search", activity.MODE_PRIVATE);
        SharedPreferences.Editor nameEditor = sharedName.edit();

        if (viewId == R.id.SubmitButton) {
            nameEditor.putString(getString(R.string.location_search), locationInput.getText().toString().toLowerCase(Locale.ROOT));
            nameEditor.commit();
            startActivity(new Intent(appContext, LocationResultsActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
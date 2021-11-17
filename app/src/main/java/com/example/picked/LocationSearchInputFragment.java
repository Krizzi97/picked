package com.example.picked;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import timber.log.Timber;

public class LocationSearchInputFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_location_input, container, false);

        EditText nameInput = (EditText) view.findViewById(R.id.txt_box);
        nameInput.setOnClickListener(this);
        Button submitButton = view.findViewById(R.id.SubmitButton);
        submitButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        if (viewId == R.id.user_location) {
            // ya
        } else if (viewId == R.id.input_location) {
            startActivity(new Intent(appContext, LocationSearchInputActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
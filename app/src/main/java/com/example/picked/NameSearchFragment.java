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

import java.io.StringReader;

import timber.log.Timber;

public class NameSearchFragment extends Fragment implements View.OnClickListener{

    private Button submitButton;
    private EditText nameInput;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_name, container, false);

        nameInput = (EditText) view.findViewById(R.id.txt_box);
        nameInput.setOnClickListener(this);
        submitButton = view.findViewById(R.id.SubmitButton);
        submitButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();

        SharedPreferences sharedPref = activity.getSharedPreferences("name_search", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //setName(this, );
        if (viewId == R.id.SubmitButton) {
            // When they press submit, save the string in shared preferences and find matches in database
            //editor.putString("name", name);
            editor.commit();
        } else {
            Timber.e("Invalid button click");
        }
    }
    // Used https://stackoverflow.com/questions/31090558/how-to-save-user-input-from-edittext-to-a-variable-to-be-used-on-a-different-act
    public static void setName(Context context, String name) {
        SharedPreferences prefs = context.getSharedPreferences("myAppPackage", 0);
        SharedPreferences.Editor editor = prefs.edit();

    }
}
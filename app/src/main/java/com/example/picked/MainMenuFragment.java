package com.example.picked;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

public class MainMenuFragment
{
    public View OnCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.activity_main, container,false);

        //Activity activity;
        // View menu = inflater.inflate(, container, false);

        // Two Buttons so far Log, Search
        //Activity activity;
        // View menu = inflater.inflate(, container, false);
        // Two Buttons so far Log, Search

        //Button logButton = v.findViewById(R.id.log_button);
        //logButton.setOnClickListener(this);
        //Button searchButton = v.findViewById(R.id.search_button);
        //searchButton.setOnClickListener(this);

        return v;
    }
}

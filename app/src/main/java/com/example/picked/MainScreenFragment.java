package com.example.picked;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import timber.log.Timber;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import java.nio.charset.Charset;
import java.util.Random;

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

        //Source: https://stackoverflow.com/questions/5474089/how-to-check-currently-internet-connection-is-available-or-not-in-android
        boolean connected = false;
        //  ConnectivityManager.
        ConnectivityManager connectivityManager = (ConnectivityManager)appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;

        if (!connected) {
            Timber.e("Not connected to the internet!");
            Snackbar.make(view, R.string.no_connection_warning,
                    Snackbar.LENGTH_SHORT)
                    .show();
        }
        else if (viewId == R.id.log_button) {
            startActivity(new Intent(appContext, RegionsActivity.class));
        } else if (viewId == R.id.search_button) {
            startActivity(new Intent(appContext, SearchActivity.class));
        } else {
            Timber.e("Invalid button click");
        }
    }
}
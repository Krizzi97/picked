package com.example.picked;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import timber.log.Timber;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Timber.d("DEBUG: onCreate()");
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Timber.d("DEBUG: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("DEBUG: onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Timber.d("DEBUG: onPause()");

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Timber.d("DEBUG: onStop()");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Timber.d("DEBUG: onDestroy()");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Timber.d("DEBUG: onRestart()");
   }

}

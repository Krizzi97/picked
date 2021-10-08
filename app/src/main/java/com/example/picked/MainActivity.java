package com.example.picked;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    protected String TAG = "MainActivity.java";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.d(TAG,"DEBUG: onCreate()");

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Timber.d(TAG,"DEBUG: onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Timber.d(TAG,"DEBUG: onResume()");

    }

    @Override
    protected void onPause(){
        super.onPause();
        Timber.d(TAG,"DEBUG: onPause()");

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Timber.d(TAG,"DEBUG: onStop()");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Timber.d(TAG,"DEBUG: onDestroy()");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Timber.d(TAG,"DEBUG: onRestart()");
   }

}

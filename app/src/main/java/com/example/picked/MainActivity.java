package com.example.picked;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    protected String TAG = "MainActivity.java";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"DEBUG: onCreate()");

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d(TAG,"DEBUG: onStart()");

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"DEBUG: onResume()");

    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"DEBUG: onPause()");

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(TAG,"DEBUG: onStop()");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG,"DEBUG: onDestroy()");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d(TAG,"DEBUG: onRestart()");
   }

}

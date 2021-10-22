package com.example.picked;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class StartScreenActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new StartScreenFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}
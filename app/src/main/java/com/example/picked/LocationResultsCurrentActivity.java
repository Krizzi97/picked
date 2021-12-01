package com.example.picked;

import androidx.fragment.app.Fragment;

public class LocationResultsCurrentActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new LocationResultsCurrentFragment();
    }
}
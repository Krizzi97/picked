package com.example.picked;

import androidx.fragment.app.Fragment;

public class LocationResultsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new LocationResultsFragment();
    }
}
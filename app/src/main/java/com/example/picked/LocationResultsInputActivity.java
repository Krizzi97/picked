package com.example.picked;

import androidx.fragment.app.Fragment;

public class LocationResultsInputActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new LocationResultsInputFragment();
    }
}
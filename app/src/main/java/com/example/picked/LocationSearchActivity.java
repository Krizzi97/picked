package com.example.picked;

import androidx.fragment.app.Fragment;

public class LocationSearchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new LocationSearchFragment();
    }
}
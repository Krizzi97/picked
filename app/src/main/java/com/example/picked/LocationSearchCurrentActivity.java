package com.example.picked;

import androidx.fragment.app.Fragment;

public class LocationSearchCurrentActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new LocationSearchCurrentFragment();
    }
}
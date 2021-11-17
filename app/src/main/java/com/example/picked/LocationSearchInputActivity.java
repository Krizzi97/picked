package com.example.picked;

import androidx.fragment.app.Fragment;

public class LocationSearchInputActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new LocationSearchInputFragment();
    }
}
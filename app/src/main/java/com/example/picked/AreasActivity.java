package com.example.picked;

import androidx.fragment.app.Fragment;

public class AreasActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new AreasFragment();
    }
}
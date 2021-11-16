package com.example.picked;

import androidx.fragment.app.Fragment;

public class TypeSearchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TypeSearchFragment();
    }
}
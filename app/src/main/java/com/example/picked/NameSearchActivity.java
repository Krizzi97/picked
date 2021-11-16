package com.example.picked;

import androidx.fragment.app.Fragment;

public class NameSearchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new NameSearchFragment();
    }
}
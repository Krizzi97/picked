package com.example.picked;

import androidx.fragment.app.Fragment;

public class SearchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SearchFragment();
    }
}
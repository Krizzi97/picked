package com.example.picked;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainScreenActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MainScreenFragment();
    }
}
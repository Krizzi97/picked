package com.example.picked;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class NameResultsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new NameResultsFragment();
    }
}
package com.example.picked;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class TypeResultsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TypeResultsFragment();
    }
}
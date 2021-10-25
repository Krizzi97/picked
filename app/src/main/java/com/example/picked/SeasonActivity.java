package com.example.picked;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class SeasonActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SeasonFragment();
    }
}
package com.example.picked;

import androidx.fragment.app.Fragment;

import com.example.picked.databinding.ActivityLogBinding;

public class LogActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new LogFragment();
    }
}
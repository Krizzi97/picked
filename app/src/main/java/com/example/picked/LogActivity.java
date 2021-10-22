package com.example.picked;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.picked.databinding.ActivityLogBinding;

public class LogActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new LogFragment();
    }
}
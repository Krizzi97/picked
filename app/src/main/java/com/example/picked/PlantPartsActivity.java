package com.example.picked;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class PlantPartsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new PlantPartFragment();
    }
}
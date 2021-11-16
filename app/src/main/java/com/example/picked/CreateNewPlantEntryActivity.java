package com.example.picked;

import androidx.fragment.app.Fragment;

public class CreateNewPlantEntryActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CreateNewPlantEntryFragment();
    }
}
package com.example.picked;

import androidx.fragment.app.Fragment;

public class EditPostsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new EditPostsFragment();
    }
}
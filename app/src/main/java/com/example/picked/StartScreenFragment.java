package com.example.picked;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StartScreenFragment extends Fragment {
    private boolean mIsActive = true;
    private final int mSplashTime = 500;
    private final int mTimeIncrement = 100;
    private final int mSleepTime = 100;

    // source: TicTacToeNew-Master by Adam Champion
    @Override
    public void onStart() {
        super.onStart();
        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int elapsedTime = 0;
                    while (mIsActive && (elapsedTime < mSplashTime)) {
                        sleep(mSleepTime);
                        if (mIsActive) elapsedTime = elapsedTime + mTimeIncrement;
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Activity activity = requireActivity();
                    activity.finish();
                    startActivity(new Intent(".MainScreenActivity"));
                }
            }
        };
        splashThread.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_screen, container, false);
    }
}
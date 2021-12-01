package com.example.picked;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatButton;
import timber.log.Timber;

@RunWith(JUnit4.class)
public class ExampleUnitTest {

    // Fragments contain onCreateView and OnClick methods

//    @Test
//    // Test that no more than one button is selected
//    public void DeselectButtons() throws Exception {
//        RegionsFragment frag = new RegionsFragment();
//        Button select = new Button(frag.getContext());
//        select.setId(1);
//        Button notSelected1 = new Button(frag.getContext());
//        notSelected1.setId(2);
//        Button notSelected2 = new Button(frag.getContext());
//        notSelected1.setId(3);
//        frag.allButtons[0] = notSelected1.getId();
//        frag.allButtons[1] = select.getId();
//        frag.allButtons[2] = notSelected2.getId();
//        select.setSelected(true);
//        frag.selected = select.getId();
//        frag.DeselectButtons();
//        assert(true);
//    }

}
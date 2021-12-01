package com.example.picked;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private LogActivity logActivity;
    private LogFragment logFragment;
    private UiDevice mDevice;
    private String working_package = "com.example.picked";
    private Context context;

    // source: https://github.com/android/testing-samples/blob/main/ui/uiautomator/BasicSample/app/src/androidTest/java/com/example/android/testing/uiautomator/BasicSample/ChangeTextBehaviorTest.java
    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertNotNull(launcherPackage);
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), 5000);

        // Launch the blueprint app
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(working_package);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(working_package).depth(0)), 5000);
    }

    @Test
    public void checkPreconditions() {
        assertNotNull(mDevice);
    }

    @Test
    public void TestStartSearchActivity() {
        mDevice.findObject(By.res(working_package, "search_button")).click();
        UiObject2 search = mDevice.wait(Until.findObject(By.res(working_package, "find_title")), 500 /* wait 500ms */);
        assertEquals(search.getText(), "FIND USING");
    }

    @Test
    public void TestSearchByType() {
        mDevice.findObject(By.res(working_package, "search_button")).click();
        mDevice.wait(Until.findObject(By.res(working_package, "type_button")), 500).click();
        mDevice.wait(Until.findObject(By.res(working_package, "FruitButton")), 500).click();
        mDevice.wait(Until.findObject(By.res(working_package, "SubmitButton")), 500).click();
        UiObject2 plant1_name = mDevice.wait(Until.findObject(By.res(working_package, "plant1_name")), 10000);
        assertTrue(plant1_name.getText().equalsIgnoreCase("APPLE FRUIT") || plant1_name.getText().equalsIgnoreCase("AMERICAN PERSIMMON FRUIT"));
    }

    @Test
    public void TestSearchWithName() {
        mDevice.findObject(By.res(working_package, "search_button")).click();
        mDevice.wait(Until.findObject(By.res(working_package, "name_button")), 500).click();
        mDevice.wait(Until.findObject(By.res(working_package, "txt_box")), 500).setText("apple");
        mDevice.wait(Until.findObject(By.res(working_package, "SubmitButton")), 500).click();
        UiObject2 plant1_name = mDevice.wait(Until.findObject(By.res(working_package, "plant1_name")), 10000);
        assertEquals(plant1_name.getText(), "apple fruit");
    }

}
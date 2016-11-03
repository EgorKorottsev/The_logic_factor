package com.example.user.thelogicfactor;

/**
 * Created by Student-204 on 11.04.2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class SettingsActivity extends FragmentActiv {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_container);
        if(fragment == null) {
            fragment = new SettingsFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_container, fragment)
                    .commit();
        }
    }
}


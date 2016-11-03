package com.example.user.thelogicfactor;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by User on 18.05.2016.
 */
public class TrainingActivity extends FragmentActiv{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_container);
        if(fragment == null) {
            fragment = new TrainingFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_container, fragment)
                    .commit();
        }
    }
}

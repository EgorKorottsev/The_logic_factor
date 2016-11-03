package com.example.user.thelogicfactor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import database.EasyQuestionsDatabaseHelper;

public class MainActivity extends FragmentActivity {
    private AudioPlayer audioPlayer = new AudioPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_container);

        if(fragment == null) {
            fragment = new MainFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        playOrNotPlayMusic();
    }

    private void playOrNotPlayMusic() {
        if(SettingsFragment.isMusicOff()) {
            audioPlayer.stop();
        } else {
            if(audioPlayer.getPlayer() == null) {
                audioPlayer.play(this);
            }
        }
    }

}

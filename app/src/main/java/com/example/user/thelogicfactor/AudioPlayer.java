package com.example.user.thelogicfactor;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by User on 27.05.2016.
 */
public class AudioPlayer {  //класс, отвечающий за воспроизведение и остановку музыки
    private static MediaPlayer mPlayer;

    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void play(Context c) {
        mPlayer = MediaPlayer.create(c, R.raw.music);
        mPlayer.start();
    }

    public static MediaPlayer getPlayer() {
        return mPlayer;
    }
}

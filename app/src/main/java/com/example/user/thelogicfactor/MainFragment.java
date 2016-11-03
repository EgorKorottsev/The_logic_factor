package com.example.user.thelogicfactor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by User on 04.07.2016.
 */
public class MainFragment extends Fragment {
    Button play, settings, achievments, training;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        play = (Button)v.findViewById(R.id.button_play);
        settings = (Button)v.findViewById(R.id.button_options);
        achievments = (Button)v.findViewById(R.id.button_achievments);
        training = (Button)v.findViewById(R.id.button_train);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(EasyQuestions.class);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(SettingsActivity.class);
            }
        });

        achievments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(AchievmentsActivity.class);
            }
        });

        training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(TrainingActivity.class);
            }
        });

        return v;
    }

    public void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }
}

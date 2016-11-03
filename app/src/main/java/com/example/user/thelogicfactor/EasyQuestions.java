package com.example.user.thelogicfactor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by Student-204 on 01.03.2016.
 */
public class EasyQuestions extends FragmentActiv {
    private static boolean[] trueAnswers = {false, false, false, false, false, false, false, false, false};
    private static int[] wrongAnswers = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static boolean showNameOfPlayer = true;

    private static final Question[] easyQuestions = new Question[] {
            new Question(R.string.easy_question_1, R.string.easy_question_1_answer),
            new Question(R.string.easy_question_2, R.string.easy_question_2_answer),
            new Question(R.string.easy_question_3, R.string.easy_question_3_answer),
            new Question(R.string.easy_question_4, R.string.easy_question_4_answer),
            new Question(R.string.easy_question_5, R.string.easy_question_5_answer),
            new Question(R.string.easy_question_6, R.string.easy_question_6_answer),
            new Question(R.string.easy_question_7, R.string.easy_question_7_answer),
            new Question(R.string.easy_question_8, R.string.easy_question_8_answer),
            new Question(R.string.easy_question_9, R.string.easy_question_9_answer),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_container);

        if(fragment == null) {
            fragment = new EasyQuestionsFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_container, fragment)
                    .commit();
        }

        if(showNameOfPlayer) {
            Toast.makeText(this, "Здравствуйте, " + SettingsFragment.getNamePlayer(), Toast.LENGTH_SHORT).show();
            showNameOfPlayer = false;
        }
    }

    public static Question[] getEasyQuestions() {
        return easyQuestions;
    }

    public static void setWrongAnswers(int index) {
        EasyQuestions.wrongAnswers[index] = EasyQuestions.wrongAnswers[index] + 1;
    }

    public static int[] getWrongAnswers() {
        return EasyQuestions.wrongAnswers;
    }

    public static void setTrueAnswers(int index) {
        EasyQuestions.trueAnswers[index] = true;
    }

    public static boolean[] getTrueAnswers() {
        return EasyQuestions.trueAnswers;
    }

    public static void setShowNameOfPlayer(boolean a) {
        showNameOfPlayer = a;
    }
}

package com.example.user.thelogicfactor;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Student-204 on 15.04.2016.
 */
public class HardQuestions extends FragmentActiv {
    private static boolean[] trueAnswers = {false, false, false, false, false, false, false, false, false};
    private static int[] wrongAnswers = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    private static final Question[] hardQuestions = new Question[] {
            new Question(R.string.hard_question_1, R.string.hard_question_1_answer),
            new Question(R.string.hard_question_2, R.string.hard_question_2_answer),
            new Question(R.string.hard_question_3, R.string.hard_question_3_answer),
            new Question(R.string.hard_question_4, R.string.hard_question_4_answer),
            new Question(R.string.hard_question_5, R.string.hard_question_5_answer),
            new Question(R.string.hard_question_6, R.string.hard_question_6_answer),
            new Question(R.string.hard_question_7, R.string.hard_question_7_answer),
            new Question(R.string.hard_question_8, R.string.hard_question_8_answer),
            new Question(R.string.hard_question_9, R.string.hard_question_9_answer),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_container);

        if(fragment == null) {
            fragment = new HardQuestionsFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_container, fragment)
                    .commit();
        }
    }

    public static Question[] getHardQuestions() {
        return hardQuestions;
    }

    public static void setWrongAnswers(int index) {
        HardQuestions.wrongAnswers[index] = HardQuestions.wrongAnswers[index] + 1;
    }

    public static void setTrueAnswers(int index) {
        HardQuestions.trueAnswers[index] = true;
    }

    public static int[] getWrongAnswers() {
        return HardQuestions.wrongAnswers;
    }

    public static boolean[] getTrueAnswers() {
        return HardQuestions.trueAnswers;
    }
}

package com.example.user.thelogicfactor;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Student-204 on 15.04.2016.
 */
public class NormalQuestions extends FragmentActiv {
    private static boolean[] trueAnswers = {false, false, false, false, false, false, false, false, false};
    private static int[] wrongAnswers = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    private static final Question[] normalQuestions = new Question[] {
            new Question(R.string.normal_question_1, R.string.normal_question_1_answer),
            new Question(R.string.normal_question_2, R.string.normal_question_2_answer),
            new Question(R.string.normal_question_3, R.string.normal_question_3_answer),
            new Question(R.string.normal_question_4, R.string.normal_question_4_answer),
            new Question(R.string.normal_question_5, R.string.normal_question_5_answer),
            new Question(R.string.normal_question_6, R.string.normal_question_6_answer),
            new Question(R.string.normal_question_7, R.string.normal_question_7_answer),
            new Question(R.string.normal_question_8, R.string.normal_question_8_answer),
            new Question(R.string.normal_question_9, R.string.normal_question_9_answer),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_container);

        if(fragment == null) {
            fragment = new NormalQuestionsFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_container, fragment)
                    .commit();
        }
    }

    public static Question[] getNormalQuestions() {
        return normalQuestions;
    }

    public static void setWrongAnswers(int index) {
       NormalQuestions.wrongAnswers[index] = NormalQuestions.wrongAnswers[index] + 1;
    }

    public static void setTrueAnswers(int index) {
        NormalQuestions.trueAnswers[index] = true;
    }

    public static int[] getWrongAnswers() {
        return NormalQuestions.wrongAnswers;
    }

    public static boolean[] getTrueAnswers() {
        return NormalQuestions.trueAnswers;
    }
}

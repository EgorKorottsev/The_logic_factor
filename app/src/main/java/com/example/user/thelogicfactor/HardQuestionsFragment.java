package com.example.user.thelogicfactor;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import database.HardQuestionsDatabaseHelper;

/**
 * Created by User on 08.07.2016.
 */
public class HardQuestionsFragment extends DifferentQuestionsFragment {

    private HardQuestionsDatabaseHelper hardQuestionsDatabaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        numberActivity = 3;
        View v = inflater.inflate(R.layout.fragment_different_questions, container, false);
        findViewsByIds(v);
        setOnClickListenerForButtons();
        textTypeQuestion.setText(R.string.textView_hard_question);

        hardQuestionsDatabaseHelper = new HardQuestionsDatabaseHelper(getActivity());

        return v;
    }

    @Override
    public void onClick(View v) {
        Context context = getActivity();
        switch(v.getId()) {
            case R.id.button_question_1: goToQuestionActivity(context, R.string.hard_question_1, R.string.hard_question_1_answer, 3, 0); break;
            case R.id.button_question_2: goToQuestionActivity(context, R.string.hard_question_2, R.string.hard_question_2_answer, 3, 1); break;
            case R.id.button_question_3: goToQuestionActivity(context, R.string.hard_question_3, R.string.hard_question_3_answer, 3, 2); break;
            case R.id.button_question_4: goToQuestionActivity(context, R.string.hard_question_4, R.string.hard_question_4_answer, 3, 3); break;
            case R.id.button_question_5: goToQuestionActivity(context, R.string.hard_question_5, R.string.hard_question_5_answer, 3, 4); break;
            case R.id.button_question_6: goToQuestionActivity(context, R.string.hard_question_6, R.string.hard_question_6_answer, 3, 5); break;
            case R.id.button_question_7: goToQuestionActivity(context, R.string.hard_question_7, R.string.hard_question_7_answer, 3, 6); break;
            case R.id.button_question_8: goToQuestionActivity(context, R.string.hard_question_8, R.string.hard_question_8_answer, 3, 7); break;
            case R.id.button_question_9: goToQuestionActivity(context, R.string.hard_question_9, R.string.hard_question_9_answer, 3, 8); break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        numberActivity = 3;
        setAmountMoney();
        setTextColorOnButtons(hardQuestionsDatabaseHelper.getHardQuestionsDatabase(), HardQuestionsDatabaseHelper.getTableName3(), HardQuestions.getTrueAnswers(), HardQuestions.getWrongAnswers());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        hardQuestionsDatabaseHelper.insertOrUpdateDatabase(hardQuestionsDatabaseHelper.getHardQuestionsDatabase(),
                HardQuestionsDatabaseHelper.getTableName3(), HardQuestions.getTrueAnswers(), HardQuestions.getWrongAnswers());
    }
}

package com.example.user.thelogicfactor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import database.NormalQuestionsDatabaseHelper;

/**
 * Created by User on 08.07.2016.
 */
public class NormalQuestionsFragment extends DifferentQuestionsFragment {

    private NormalQuestionsDatabaseHelper normalQuestionsDatabaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        numberActivity = 2;
        View v = inflater.inflate(R.layout.fragment_different_questions, container, false);
        findViewsByIds(v);
        setOnClickListenerForButtons();
        textTypeQuestion.setText(R.string.textView_normal_question);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HardQuestions.class);
                startActivity(intent);
            }
        });

        normalQuestionsDatabaseHelper = new NormalQuestionsDatabaseHelper(getActivity());

        return v;
    }

    @Override
    public void onClick(View v) {
        Context context = getActivity();
        switch(v.getId()) {
            case R.id.button_question_1: goToQuestionActivity(context, R.string.normal_question_1, R.string.normal_question_1_answer, 2, 0); break;
            case R.id.button_question_2: goToQuestionActivity(context, R.string.normal_question_2, R.string.normal_question_2_answer, 2, 1); break;
            case R.id.button_question_3: goToQuestionActivity(context, R.string.normal_question_3, R.string.normal_question_3_answer, 2, 2); break;
            case R.id.button_question_4: goToQuestionActivity(context, R.string.normal_question_4, R.string.normal_question_4_answer, 2, 3); break;
            case R.id.button_question_5: goToQuestionActivity(context, R.string.normal_question_5, R.string.normal_question_5_answer, 2, 4); break;
            case R.id.button_question_6: goToQuestionActivity(context, R.string.normal_question_6, R.string.normal_question_6_answer, 2, 5); break;
            case R.id.button_question_7: goToQuestionActivity(context, R.string.normal_question_7, R.string.normal_question_7_answer, 2, 6); break;
            case R.id.button_question_8: goToQuestionActivity(context, R.string.normal_question_8, R.string.normal_question_8_answer, 2, 7); break;
            case R.id.button_question_9: goToQuestionActivity(context, R.string.normal_question_9, R.string.normal_question_9_answer, 2, 8); break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        numberActivity = 2;
        setAmountMoney();
        setTextColorOnButtons(normalQuestionsDatabaseHelper.getNormalQuestionsDatabase(), NormalQuestionsDatabaseHelper.getTableName2(), NormalQuestions.getTrueAnswers(), NormalQuestions.getWrongAnswers());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        normalQuestionsDatabaseHelper.insertOrUpdateDatabase(normalQuestionsDatabaseHelper.getNormalQuestionsDatabase(),
                NormalQuestionsDatabaseHelper.getTableName2(), NormalQuestions.getTrueAnswers(), NormalQuestions.getWrongAnswers());
    }
}

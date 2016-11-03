package com.example.user.thelogicfactor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import database.EasyQuestionsDatabaseHelper;

/**
 * Created by User on 08.07.2016.
 */
public class EasyQuestionsFragment extends DifferentQuestionsFragment {

    public EasyQuestionsDatabaseHelper easyQuestionsDatabaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        numberActivity = 1;
        View v = inflater.inflate(R.layout.fragment_different_questions, container, false);
        findViewsByIds(v);
        setOnClickListenerForButtons();
        textTypeQuestion.setText(R.string.textView_easy_question);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NormalQuestions.class);
                startActivity(intent);
            }
        });

        easyQuestionsDatabaseHelper = new EasyQuestionsDatabaseHelper(getActivity());

        return v;
    }

    @Override
    public void onClick(View v) {
        Context context = getActivity();
        switch(v.getId()) {
            case R.id.button_question_1: goToQuestionActivity(context, R.string.easy_question_1, R.string.easy_question_1_answer, 1, 0);break;
            case R.id.button_question_2: goToQuestionActivity(context, R.string.easy_question_2, R.string.easy_question_2_answer, 1, 1); break;
            case R.id.button_question_3: goToQuestionActivity(context, R.string.easy_question_3, R.string.easy_question_3_answer, 1, 2); break;
            case R.id.button_question_4: goToQuestionActivity(context, R.string.easy_question_4, R.string.easy_question_4_answer, 1, 3); break;
            case R.id.button_question_5: goToQuestionActivity(context, R.string.easy_question_5, R.string.easy_question_5_answer, 1, 4); break;
            case R.id.button_question_6: goToQuestionActivity(context, R.string.easy_question_6, R.string.easy_question_6_answer, 1, 5); break;
            case R.id.button_question_7: goToQuestionActivity(context, R.string.easy_question_7, R.string.easy_question_7_answer, 1, 6); break;
            case R.id.button_question_8: goToQuestionActivity(context, R.string.easy_question_8, R.string.easy_question_8_answer, 1, 7); break;
            case R.id.button_question_9: goToQuestionActivity(context, R.string.easy_question_9, R.string.easy_question_9_answer, 1, 8); break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        numberActivity = 1;
        setAmountMoney();
        setTextColorOnButtons(easyQuestionsDatabaseHelper.getEasyQuestionsDatabase(), EasyQuestionsDatabaseHelper.getTableName1(), EasyQuestions.getTrueAnswers(), EasyQuestions.getWrongAnswers());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        easyQuestionsDatabaseHelper.insertOrUpdateDatabase(easyQuestionsDatabaseHelper.getEasyQuestionsDatabase(),
                EasyQuestionsDatabaseHelper.getTableName1(), EasyQuestions.getTrueAnswers(), EasyQuestions.getWrongAnswers());


    }
}

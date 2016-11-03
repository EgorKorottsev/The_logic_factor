package com.example.user.thelogicfactor;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import database.ColumnsInfo;
import database.EasyQuestionsDatabaseHelper;
import database.OperationsWithDatabase;

/**
 * Created by User on 07.07.2016.
 */

//суперкласс для 3 классов: EasyQuestionFragment, NormalQuestionFragment, HardQuestionFragment

public class DifferentQuestionsFragment extends Fragment implements View.OnClickListener {
    TextView textTypeQuestion;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    ImageButton nextButton;
    TextView textViewAmountMoney;
    public static boolean isNewName1 = true;
    public static boolean isNewName2 = true;
    public static boolean isNewName3 = true;
    public static int numberActivity = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        return v;
    }

    @Override
    public void onClick(View v) {
    }

    public void goToQuestionActivity(Context context, int resQuestion, int resAnswer, int typeQuestion, int index) {
        Intent intent = new Intent(context, QuestionActivity.class);
        intent.putExtra(QuestionActivity.STRING_QUESTION, resQuestion);
        intent.putExtra(QuestionActivity.STRING_ANSWER, resAnswer);
        intent.putExtra(QuestionActivity.STRING_TYPE_QUESTION, typeQuestion);
        intent.putExtra(QuestionActivity.STRING_INDEX, index);
        QuestionActivity.setIsNewQuestion(false);
        startActivity(intent);
    }

    public void findViewsByIds(View v) {
        textTypeQuestion = (TextView)v.findViewById(R.id.textTypeQuestion);
        button1 = (Button)v.findViewById(R.id.button_question_1);
        button2 = (Button)v.findViewById(R.id.button_question_2);
        button3 = (Button)v.findViewById(R.id.button_question_3);
        button4 = (Button)v.findViewById(R.id.button_question_4);
        button5 = (Button)v.findViewById(R.id.button_question_5);
        button6 = (Button)v.findViewById(R.id.button_question_6);
        button7 = (Button)v.findViewById(R.id.button_question_7);
        button8 = (Button)v.findViewById(R.id.button_question_8);
        button9 = (Button)v.findViewById(R.id.button_question_9);
        nextButton = (ImageButton)v.findViewById(R.id.next_button);
        textViewAmountMoney = (TextView)v.findViewById(R.id.textViewAmountMoney);
    }

    public void setOnClickListenerForButtons() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
    }

    public void setAmountMoney() {
        String amountMoney = Integer.toString(Money.getM());
        textViewAmountMoney.setText(amountMoney);
    }

    public void setAmountMoney(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select " + ColumnsInfo.NAME_PLAYER + ", " + ColumnsInfo.AMOUNT_MONEY + " from " + EasyQuestionsDatabaseHelper.getTableName1(), null);
        if(cursor.getCount() == 0) {
            Money.setM(0);
        } else {
            if(cursor.moveToFirst()) {
                do {
                    if (cursor.getString(cursor.getColumnIndex(ColumnsInfo.NAME_PLAYER)).equals(SettingsFragment.getNamePlayer())) {
                        Money.setM(cursor.getInt(cursor.getColumnIndex(ColumnsInfo.AMOUNT_MONEY)));
                        cursor.close();
                        return;
                    }
                } while (cursor.moveToNext());
            }
        }

        cursor.close();
    }

    public void setTextColorOnButtons(SQLiteDatabase db, String tableName, boolean[] trueQuestions, int[] wrongAnswers) {
            if (numberActivity == 1 && isNewName1) {
                for (int i = 0; i < trueQuestions.length; i++) {
                    trueQuestions[i] = false;
                }
                for (int i = 0; i < wrongAnswers.length; i++) {
                    wrongAnswers[i] = 0;
                }
                putValuesInTrueAndWrongAnswers(db, tableName, trueQuestions, wrongAnswers);
                isNewName1 = false;

            } else if (numberActivity == 2 && isNewName2) {
                for (int i = 0; i < trueQuestions.length; i++) {
                    trueQuestions[i] = false;
                }
                for (int i = 0; i < wrongAnswers.length; i++) {
                    wrongAnswers[i] = 0;
                }
                putValuesInTrueAndWrongAnswers(db, tableName, trueQuestions, wrongAnswers);
                isNewName2 = false;

            } else if (numberActivity == 3 && isNewName3) {
                for (int i = 0; i < trueQuestions.length; i++) {
                    trueQuestions[i] = false;
                }
                for (int i = 0; i < wrongAnswers.length; i++) {
                    wrongAnswers[i] = 0;
                }
                putValuesInTrueAndWrongAnswers(db, tableName, trueQuestions, wrongAnswers);
                isNewName3 = false;
            }

            for (int i = 0; i < 9; i++) {
                if (trueQuestions[i]) {
                    switch (i) {
                        case 0:
                            button1.setTextColor(getResources().getColor(R.color.green));
                            break;
                        case 1:
                            button2.setTextColor(getResources().getColor(R.color.green));
                            break;
                        case 2:
                            button3.setTextColor(getResources().getColor(R.color.green));
                            break;
                        case 3:
                            button4.setTextColor(getResources().getColor(R.color.green));
                            break;
                        case 4:
                            button5.setTextColor(getResources().getColor(R.color.green));
                            break;
                        case 5:
                            button6.setTextColor(getResources().getColor(R.color.green));
                            break;
                        case 6:
                            button7.setTextColor(getResources().getColor(R.color.green));
                            break;
                        case 7:
                            button8.setTextColor(getResources().getColor(R.color.green));
                            break;
                        case 8:
                            button9.setTextColor(getResources().getColor(R.color.green));
                            break;
                    }
                } else {
                    switch (i) {
                        case 0:
                            button1.setTextColor(getResources().getColor(R.color.red));
                            break;
                        case 1:
                            button2.setTextColor(getResources().getColor(R.color.red));
                            break;
                        case 2:
                            button3.setTextColor(getResources().getColor(R.color.red));
                            break;
                        case 3:
                            button4.setTextColor(getResources().getColor(R.color.red));
                            break;
                        case 4:
                            button5.setTextColor(getResources().getColor(R.color.red));
                            break;
                        case 5:
                            button6.setTextColor(getResources().getColor(R.color.red));
                            break;
                        case 6:
                            button7.setTextColor(getResources().getColor(R.color.red));
                            break;
                        case 7:
                            button8.setTextColor(getResources().getColor(R.color.red));
                            break;
                        case 8:
                            button9.setTextColor(getResources().getColor(R.color.red));
                            break;
                    }
                }
            }
    }

    public void putValuesInTrueAndWrongAnswers(SQLiteDatabase db, String tableName, boolean[] trueQuestions, int[] wrongAnswers) {
        Cursor cursor = OperationsWithDatabase.getAllTimeRecords(db, tableName);
        String namePlayer = SettingsFragment.getNamePlayer();
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(cursor.getColumnIndex(ColumnsInfo.NAME_PLAYER)).equals(namePlayer)) {
                        getBooleanInCursorTrueQuestions(cursor, ColumnsInfo.TRUE_QUESTION_1, trueQuestions, 0);
                        getBooleanInCursorTrueQuestions(cursor, ColumnsInfo.TRUE_QUESTION_2, trueQuestions, 1);
                        getBooleanInCursorTrueQuestions(cursor, ColumnsInfo.TRUE_QUESTION_3, trueQuestions, 2);
                        getBooleanInCursorTrueQuestions(cursor, ColumnsInfo.TRUE_QUESTION_4, trueQuestions, 3);
                        getBooleanInCursorTrueQuestions(cursor, ColumnsInfo.TRUE_QUESTION_5, trueQuestions, 4);
                        getBooleanInCursorTrueQuestions(cursor, ColumnsInfo.TRUE_QUESTION_6, trueQuestions, 5);
                        getBooleanInCursorTrueQuestions(cursor, ColumnsInfo.TRUE_QUESTION_7, trueQuestions, 6);
                        getBooleanInCursorTrueQuestions(cursor, ColumnsInfo.TRUE_QUESTION_8, trueQuestions, 7);
                        getBooleanInCursorTrueQuestions(cursor, ColumnsInfo.TRUE_QUESTION_9, trueQuestions, 8);

                        getIntInCursor(cursor, ColumnsInfo.AMOUNT_WRONG_ANSWERS_1, wrongAnswers, 0);
                        getIntInCursor(cursor, ColumnsInfo.AMOUNT_WRONG_ANSWERS_2, wrongAnswers, 1);
                        getIntInCursor(cursor, ColumnsInfo.AMOUNT_WRONG_ANSWERS_3, wrongAnswers, 2);
                        getIntInCursor(cursor, ColumnsInfo.AMOUNT_WRONG_ANSWERS_4, wrongAnswers, 3);
                        getIntInCursor(cursor, ColumnsInfo.AMOUNT_WRONG_ANSWERS_5, wrongAnswers, 4);
                        getIntInCursor(cursor, ColumnsInfo.AMOUNT_WRONG_ANSWERS_6, wrongAnswers, 5);
                        getIntInCursor(cursor, ColumnsInfo.AMOUNT_WRONG_ANSWERS_7, wrongAnswers, 6);
                        getIntInCursor(cursor, ColumnsInfo.AMOUNT_WRONG_ANSWERS_8, wrongAnswers, 7);
                        getIntInCursor(cursor, ColumnsInfo.AMOUNT_WRONG_ANSWERS_9, wrongAnswers, 8);

                        if (!cursor.isClosed()) {
                            cursor.close();
                        }
                    }
                } while (cursor.moveToNext());
            }
        } else {
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    public void getBooleanInCursorTrueQuestions(Cursor cursor, String nameColumn, boolean[] trueQuestions, int index) {
        if(cursor.getInt(cursor.getColumnIndex(nameColumn)) == 1) {
            trueQuestions[index] = true;
        }
    }

    public void getIntInCursor(Cursor cursor, String nameColumn, int[] wrongAnswers, int index) {
        wrongAnswers[index] = cursor.getInt(cursor.getColumnIndex(nameColumn));
    }


}

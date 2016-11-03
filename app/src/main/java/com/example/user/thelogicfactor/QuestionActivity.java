package com.example.user.thelogicfactor;

/**
 * Created by Student-204 on 11.04.2016.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {       //активность, где будут появляться вопросы
    public static final String STRING_QUESTION = "question";
    public static final String STRING_ANSWER = "answer";
    public static final String STRING_TYPE_QUESTION = "typeQuestion";
    public static final String STRING_INDEX = "index";

    static final String STATE_RES_QUESTION = "resQuestion";
    static final String STATE_RES_ANSWER = "resAnswer";
    static final String STATE_TYPE_QUESTION = "typeQues";
    static final String STATE_INDEX_QUESTION= "indexQues";

    private static int resQuestion;
    private static int answer;
    private static int typeQuestion;
    private static int index;
    private static int restriction = 9;
    private static boolean isNewQuestion = false;
    private static boolean isButtonOk = false;

    TextView textViewQuestion;
    EditText editText;
    ImageButton prevButton, nextButton;
    Button confirmButton, supportButton;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_question);
        textViewQuestion = (TextView)findViewById(R.id.textViewQuestion);
        editText = (EditText)findViewById(R.id.editText);
        prevButton = (ImageButton)findViewById(R.id.prev_question);
        nextButton = (ImageButton)findViewById(R.id.next_question);
        confirmButton = (Button)findViewById(R.id.confirm);
        supportButton = (Button)findViewById(R.id.support);

        if(!isNewQuestion) {
            resQuestion = getIntent().getIntExtra(STRING_QUESTION, 0);
            answer = getIntent().getIntExtra(STRING_ANSWER, 0);
            typeQuestion = getIntent().getIntExtra(STRING_TYPE_QUESTION, 0);
            index = getIntent().getIntExtra(STRING_INDEX, 0);
        } else {
            resQuestion = savedInstanceState.getInt(STATE_RES_QUESTION);
            answer = savedInstanceState.getInt(STATE_RES_ANSWER);
            typeQuestion = savedInstanceState.getInt(STATE_TYPE_QUESTION);
            index = savedInstanceState.getInt(STATE_INDEX_QUESTION);
        }

        textViewQuestion.setText(resQuestion);
        textViewQuestion.setTextSize(SettingsFragment.getFontSize());
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPrevQuestion();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextQuestion();
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTrueAnswer();
            }
        });
        supportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogInfo dialog = new DialogInfo();
                dialog.show(fragmentManager, "dialog");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isButtonOk) {
            makeSupport();
        }
        isButtonOk = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void isTrueAnswer() {
        String stringAnswer = getString(answer);
        if((editText.getText().toString()).compareToIgnoreCase(stringAnswer) == 0) {
            switch (typeQuestion) {
                case 1:
                    updateOrNotUpdateMoney(EasyQuestions.getTrueAnswers(), EasyQuestions.getWrongAnswers());
                    break;
                case 2:
                    updateOrNotUpdateMoney(NormalQuestions.getTrueAnswers(), NormalQuestions.getWrongAnswers());
                    break;
                case 3:
                    updateOrNotUpdateMoney(HardQuestions.getTrueAnswers(), HardQuestions.getWrongAnswers());
                    break;
            }

            switch (typeQuestion) {
                case 1: EasyQuestions.setTrueAnswers(index); break;
                case 2: NormalQuestions.setTrueAnswers(index); break;
                case 3: HardQuestions.setTrueAnswers(index); break;
            }
        } else {
            switch (typeQuestion) {
                case 1:
                    if(EasyQuestions.getTrueAnswers()[index]) {
                        Toast.makeText(context, R.string.text_you_answered, Toast.LENGTH_SHORT).show();
                    } else {
                        EasyQuestions.setWrongAnswers(index);
                        Toast.makeText(context, R.string.text_wrong, Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 2:
                    if(NormalQuestions.getTrueAnswers()[index]) {
                        Toast.makeText(context, R.string.text_you_answered, Toast.LENGTH_SHORT).show();
                    } else {
                        NormalQuestions.setWrongAnswers(index);
                        Toast.makeText(context, R.string.text_wrong, Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 3:
                    if(HardQuestions.getTrueAnswers()[index]) {
                        Toast.makeText(context, R.string.text_you_answered, Toast.LENGTH_SHORT).show();
                    } else {
                        HardQuestions.setWrongAnswers(index);
                        Toast.makeText(context, R.string.text_wrong, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    public void goToNextQuestion() {
        int nextIndex = index + 1;
        isNewQuestion = true;
        if(nextIndex == restriction) {
            if(typeQuestion == 1){
                updateQuestion(2, 0, NormalQuestions.getNormalQuestions());
            } else if(typeQuestion == 2){
                updateQuestion(3, 0, HardQuestions.getHardQuestions());
            } else if(typeQuestion == 3) {
                Toast.makeText(context, R.string.text_no_questions, Toast.LENGTH_SHORT).show();
            }
        } else {
            index = nextIndex;
            switch(typeQuestion) {
                case 1:
                    updateQuestion(EasyQuestions.getEasyQuestions());
                    break;
                case 2:
                    updateQuestion(NormalQuestions.getNormalQuestions());
                    break;
                case 3:
                    updateQuestion(HardQuestions.getHardQuestions());
                    break;
            }
        }

        editText.setText("");
    }

    public void goToPrevQuestion() {
        int prevIndex = index - 1;
        isNewQuestion = true;
        if(prevIndex == -1) {
            int endIndex = restriction - 1;
            if(typeQuestion == 1) {
                Toast.makeText(context, R.string.text_no_questions, Toast.LENGTH_LONG).show();
            } else if(typeQuestion == 2) {
                updateQuestion(1, endIndex, EasyQuestions.getEasyQuestions());
            } else if(typeQuestion == 3) {
                updateQuestion(2, endIndex, NormalQuestions.getNormalQuestions());
            }
        } else {
            index = prevIndex;
            switch(typeQuestion) {
                case 1:
                    updateQuestion(EasyQuestions.getEasyQuestions());
                    break;
                case 2:
                    updateQuestion(NormalQuestions.getNormalQuestions());
                    break;
                case 3:
                    updateQuestion(HardQuestions.getHardQuestions());
                    break;
            }
        }

        editText.setText("");
    }

    public void makeSupport() {
        switch (typeQuestion) {
            case 1: getResAnswer(EasyQuestions.getEasyQuestions(), 8); break;
            case 2: getResAnswer(NormalQuestions.getNormalQuestions(), 7); break;
            case 3: getResAnswer(HardQuestions.getHardQuestions(), 6); break;
        }
    }

    public void updateAmountMoney(int[] wrongAnswers) {
        int amountWrongAnswers = wrongAnswers[index];
        switch (amountWrongAnswers) {
            case 0:
                updateAmountMoneyDifferentTypeQuestions(0);
                break;
            case 1:
                updateAmountMoneyDifferentTypeQuestions(1);
                break;
            case 2:
                updateAmountMoneyDifferentTypeQuestions(2);
                break;
            case 3:
                updateAmountMoneyDifferentTypeQuestions(3);
                break;
            default: Toast.makeText(context, R.string.text_3_errors, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void updateOrNotUpdateMoney(boolean[] trueAnswers,  int[] wrongAnswers) {
        if(trueAnswers[index]) {
            Toast.makeText(context, R.string.text_you_answered, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.text_right, Toast.LENGTH_SHORT).show();
            updateAmountMoney(wrongAnswers);
        }
    }

    public void updateQuestion(Question[] questions) {
        resQuestion = questions[index].getResQuestion();
        answer = questions[index].getResAnswer();
        textViewQuestion.setText(resQuestion);
    }

    public void updateQuestion(int typeQuestion, int index, Question[] questions) {
        QuestionActivity.typeQuestion = typeQuestion;
        QuestionActivity.index = index;
        resQuestion = questions[index].getResQuestion();
        answer = questions[index].getResAnswer();
    }

    public void getResAnswer(Question[] questions, int minusMoney) {
        if(Money.canMinusMoney(minusMoney) == 1) {
            int resAnsw = questions[index].getResAnswer();
            Money.minusMoney(minusMoney);
            Toast.makeText(context, resAnsw, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, R.string.text_no_support, Toast.LENGTH_SHORT).show();
        }
    }

    public void updateAmountMoneyDifferentTypeQuestions(int minus) {
        int startAmountMoney = Money.getM();

        if(typeQuestion == 1) {
            int startAmountMoneyForTrueEasyQuestions = 4;
            Money.plusMoney(startAmountMoneyForTrueEasyQuestions - minus);
        } else if(typeQuestion == 2) {
            int startAmountMoneyForTrueNormalQuestions = 6;
            Money.plusMoney(startAmountMoneyForTrueNormalQuestions - minus);
        } else if(typeQuestion == 3) {
            int startAmountMoneyForTrueHardQuestions = 8;
            Money.plusMoney(startAmountMoneyForTrueHardQuestions - minus);
        }

        int currentAmountMoney = Money.getM();
        int deltaAmountMoney = currentAmountMoney - startAmountMoney;

        Toast.makeText(context, "Вам зачислено " + Integer.toString(deltaAmountMoney), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(STATE_RES_QUESTION, resQuestion);
        savedInstanceState.putInt(STATE_RES_ANSWER, answer);
        savedInstanceState.putInt(STATE_TYPE_QUESTION, typeQuestion);
        savedInstanceState.putInt(STATE_INDEX_QUESTION, index);

        super.onSaveInstanceState(savedInstanceState);
    }

    public static void setIsNewQuestion(boolean isNewQuestion) {
        QuestionActivity.isNewQuestion = isNewQuestion;
    }

    public static void setIsButtonOk(boolean isButtonOk) {
        QuestionActivity.isButtonOk = isButtonOk;
    }
}
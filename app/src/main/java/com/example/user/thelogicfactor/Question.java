package com.example.user.thelogicfactor;

/**
 * Created by Student-204 on 11.04.2016.
 */
public class Question {
      private int resQuestion;
      private int resAnswer;

      public Question(int resQuestion, int resAnswer) {
          this.resQuestion = resQuestion;
          this.resAnswer = resAnswer;
      }

      public int getResQuestion() {
          return resQuestion;
      }

      public int getResAnswer() {
          return resAnswer;
      }
}


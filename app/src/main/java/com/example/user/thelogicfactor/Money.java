package com.example.user.thelogicfactor;

/**
 * Created by Student-204 on 22.04.2016.
 */
public class Money {    //вспомогательный класс для работы с вознаграждением
    private static int m = 0;           //переменная, отвечающая за кол-во денег

    public static void setM(int m) {    //сеттер - присваивание значения переменной m
        Money.m = m;
    }

    public static int getM() {
        return m;
    }   //получить значение переменной m

    public static void plusMoney(int value){
        m = m + value;
    }   //увеличить кол-во денег

    public static int canMinusMoney(int value){    //уменьшить кол-во денег
        int r;
        if((m - value) < 0) {
            r = 0;
        } else {
            r = 1;
        }
        return r;
    }

    public static void minusMoney(int value) {
        m = m - value;
    }

}

package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 21.09.2016.
 */
public class HardQuestionsOpenHelper extends SQLiteOpenHelper {
    private static final String INT = " INTEGER, ";

    public HardQuestionsOpenHelper(Context context) {
        super(context, "database_hard_questions", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table table_hard_questions " +
                "( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnsInfo.NAME_PLAYER + " TEXT, " +
                ColumnsInfo.TRUE_QUESTION_1 + INT +
                ColumnsInfo.TRUE_QUESTION_2 + INT +
                ColumnsInfo.TRUE_QUESTION_3 + INT +
                ColumnsInfo.TRUE_QUESTION_4 + INT +
                ColumnsInfo.TRUE_QUESTION_5 + INT +
                ColumnsInfo.TRUE_QUESTION_6 + INT +
                ColumnsInfo.TRUE_QUESTION_7 + INT +
                ColumnsInfo.TRUE_QUESTION_8 + INT +
                ColumnsInfo.TRUE_QUESTION_9 + INT +
                ColumnsInfo.AMOUNT_WRONG_ANSWERS_1 + INT +
                ColumnsInfo.AMOUNT_WRONG_ANSWERS_2 + INT +
                ColumnsInfo.AMOUNT_WRONG_ANSWERS_3 + INT +
                ColumnsInfo.AMOUNT_WRONG_ANSWERS_4 + INT +
                ColumnsInfo.AMOUNT_WRONG_ANSWERS_5 + INT +
                ColumnsInfo.AMOUNT_WRONG_ANSWERS_6 + INT +
                ColumnsInfo.AMOUNT_WRONG_ANSWERS_7 + INT +
                ColumnsInfo.AMOUNT_WRONG_ANSWERS_8 + INT +
                ColumnsInfo.AMOUNT_WRONG_ANSWERS_9 + INT +
                ColumnsInfo.AMOUNT_MONEY + " )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS table_hard_questions");
    }
}

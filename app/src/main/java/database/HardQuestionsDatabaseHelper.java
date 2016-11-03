package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Created by User on 21.09.2016.
 */
public class HardQuestionsDatabaseHelper extends OperationsWithDatabase {
    private static final String TABLE_NAME_3 = "table_hard_questions";

    private HardQuestionsOpenHelper hardQuestionsOpenHelper;
    private static SQLiteDatabase hardQuestionsDatabase;

    public HardQuestionsDatabaseHelper(Context context) {
        hardQuestionsOpenHelper = new HardQuestionsOpenHelper(context);
        try {
            hardQuestionsDatabase = hardQuestionsOpenHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            hardQuestionsDatabase = hardQuestionsOpenHelper.getWritableDatabase();
        }
    }

    public static String getTableName3() {
        return TABLE_NAME_3;
    }

    public SQLiteDatabase getHardQuestionsDatabase() {
        return hardQuestionsDatabase;
    }
}

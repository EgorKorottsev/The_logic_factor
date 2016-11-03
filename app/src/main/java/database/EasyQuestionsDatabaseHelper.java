package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Created by User on 18.09.2016.
 */
public class EasyQuestionsDatabaseHelper extends OperationsWithDatabase{    //класс - помощник, для работы с базами данных
    private static final String TABLE_NAME_1 = "table_easy_questions";

    private EasyQuestionsOpenHelper easyQuestionsOpenHelper;
    private static SQLiteDatabase easyQuestionsDatabase;

    public EasyQuestionsDatabaseHelper(Context context) {
        easyQuestionsOpenHelper = new EasyQuestionsOpenHelper(context);
        try {
            easyQuestionsDatabase = easyQuestionsOpenHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            easyQuestionsDatabase = easyQuestionsOpenHelper.getReadableDatabase();
        }
    }

    public static String getTableName1() {
        return TABLE_NAME_1;
    }

    public SQLiteDatabase getEasyQuestionsDatabase() {
        return easyQuestionsDatabase;
    }
}

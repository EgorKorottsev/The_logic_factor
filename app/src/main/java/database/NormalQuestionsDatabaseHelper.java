package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.user.thelogicfactor.NormalQuestions;
import com.example.user.thelogicfactor.SettingsFragment;


/**
 * Created by User on 21.09.2016.
 */
public class NormalQuestionsDatabaseHelper extends OperationsWithDatabase {
    private static final String TABLE_NAME_2 = "table_normal_questions";

    private NormalQuestionsOpenHelper normalQuestionsOpenHelper;
    private SQLiteDatabase normalQuestionsDatabase;

    public NormalQuestionsDatabaseHelper(Context context) {
        normalQuestionsOpenHelper = new NormalQuestionsOpenHelper(context);
        try {
            normalQuestionsDatabase = normalQuestionsOpenHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            normalQuestionsDatabase = normalQuestionsOpenHelper.getReadableDatabase();
        }
    }

    public static String getTableName2() {
        return TABLE_NAME_2;
    }

    public SQLiteDatabase getNormalQuestionsDatabase() {
        return normalQuestionsDatabase;
    }
}

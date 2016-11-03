package database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.thelogicfactor.Money;
import com.example.user.thelogicfactor.SettingsFragment;

/**
 * Created by User on 06.10.2016.
 */

public class OperationsWithDatabase {   //класс, отвечающий за различные операции с базами данных

    private int parseBooleanToInt(boolean a) {
        if(a) return 1;
        return 0;
    }

    public ContentValues createContentValues(boolean[] a, int[] wrongAnswers) {
        ContentValues values = new ContentValues();

        values.put(ColumnsInfo.NAME_PLAYER, SettingsFragment.getNamePlayer());
        values.put(ColumnsInfo.TRUE_QUESTION_1, parseBooleanToInt(a[0]));
        values.put(ColumnsInfo.TRUE_QUESTION_2, parseBooleanToInt(a[1]));
        values.put(ColumnsInfo.TRUE_QUESTION_3, parseBooleanToInt(a[2]));
        values.put(ColumnsInfo.TRUE_QUESTION_4, parseBooleanToInt(a[3]));
        values.put(ColumnsInfo.TRUE_QUESTION_5, parseBooleanToInt(a[4]));
        values.put(ColumnsInfo.TRUE_QUESTION_6, parseBooleanToInt(a[5]));
        values.put(ColumnsInfo.TRUE_QUESTION_7, parseBooleanToInt(a[6]));
        values.put(ColumnsInfo.TRUE_QUESTION_8, parseBooleanToInt(a[7]));
        values.put(ColumnsInfo.TRUE_QUESTION_9, parseBooleanToInt(a[8]));
        values.put(ColumnsInfo.AMOUNT_WRONG_ANSWERS_1, wrongAnswers[0]);
        values.put(ColumnsInfo.AMOUNT_WRONG_ANSWERS_2, wrongAnswers[1]);
        values.put(ColumnsInfo.AMOUNT_WRONG_ANSWERS_3, wrongAnswers[2]);
        values.put(ColumnsInfo.AMOUNT_WRONG_ANSWERS_4, wrongAnswers[3]);
        values.put(ColumnsInfo.AMOUNT_WRONG_ANSWERS_5, wrongAnswers[4]);
        values.put(ColumnsInfo.AMOUNT_WRONG_ANSWERS_6, wrongAnswers[5]);
        values.put(ColumnsInfo.AMOUNT_WRONG_ANSWERS_7, wrongAnswers[6]);
        values.put(ColumnsInfo.AMOUNT_WRONG_ANSWERS_8, wrongAnswers[7]);
        values.put(ColumnsInfo.AMOUNT_WRONG_ANSWERS_9, wrongAnswers[8]);
        values.put(ColumnsInfo.AMOUNT_MONEY, Money.getM());

        return values;
    }

    public void saveResults(SQLiteDatabase db, String nameTable, boolean[] getTrueAnswers, int[] getWrongAnswers) {
        ContentValues contentValues = createContentValues(getTrueAnswers, getWrongAnswers);
        db.insert(nameTable, null, contentValues);
    }

    public void updateDatabase(SQLiteDatabase db, String tableName, boolean[] getTrueAnswers, int[] getWrongAnswers) {
        String namePlayer = SettingsFragment.getNamePlayer();
        ContentValues values = createContentValues(getTrueAnswers, getWrongAnswers);
        db.update(tableName, values, ColumnsInfo.NAME_PLAYER + " = ?", new String[] { namePlayer });
    }

    public static Cursor getAllTimeRecords(SQLiteDatabase db, String tableName) {
        return db.rawQuery(
                "select * from " + tableName,
                null
        );
    }

    public void insertOrUpdateDatabase(SQLiteDatabase db, String tableName, boolean[] getTrueAnswers, int[] getWrongAnswers) {
        Cursor cursor = getAllTimeRecords(db, tableName);
        String namePlayer = SettingsFragment.getNamePlayer();

        if(cursor.getCount() == 0) {
            saveResults(db, tableName, getTrueAnswers, getWrongAnswers);
        } else {
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(cursor.getColumnIndex(ColumnsInfo.NAME_PLAYER)).equals(namePlayer)) {
                        updateDatabase(db, tableName, getTrueAnswers, getWrongAnswers);
                        closeCursor(cursor);
                        return;
                    }
                } while (cursor.moveToNext());
            }

            saveResults(db, tableName, getTrueAnswers, getWrongAnswers);
        }

        closeCursor(cursor);
    }

    public void closeCursor(Cursor cursor) {
        if (!cursor.isClosed()) {
            cursor.close();
        }
    }
}

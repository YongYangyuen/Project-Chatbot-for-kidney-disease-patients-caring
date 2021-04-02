package com.example.kidneyphrs;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ParseException;
import android.provider.BaseColumns;

import static com.example.kidneyphrs.HemoContent.AMOUNT;
import static com.example.kidneyphrs.HemoContent.DATE;
import static com.example.kidneyphrs.HemoContent.TABLE_NAME;

/**
 * Created by pawitra on 2/24/2017 AD.
 */

public class HemoHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "hemoExercise.db";
    private static final int DATABASE_VERSION = 1;

    public HemoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + AMOUNT + " INTEGER);" );

        addHemoItem(db, "2017-04-27", 500);
        addHemoItem(db, "2017-04-28", 520);
        addHemoItem(db, "2017-04-29", 510);
        addHemoItem(db, "2017-04-30", 500);
        addHemoItem(db, "2017-05-01", 510);
        addHemoItem(db, "2017-05-02", 500);
        addHemoItem(db, "2017-05-03", 510);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void addHemoItem(SQLiteDatabase db, String date, Integer amount) throws ParseException {

        ContentValues values = new ContentValues();
        values.put(DATE , date);
        values.put(AMOUNT , amount);

        db.insert(TABLE_NAME, null, values);
    }
}
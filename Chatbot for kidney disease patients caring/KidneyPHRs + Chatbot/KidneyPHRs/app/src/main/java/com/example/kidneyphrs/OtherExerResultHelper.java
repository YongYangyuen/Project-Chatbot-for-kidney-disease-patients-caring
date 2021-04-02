package com.example.kidneyphrs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import static com.example.kidneyphrs.OtherExerContent.AMOUNT;
import static com.example.kidneyphrs.OtherExerContent.DATE;
import static com.example.kidneyphrs.OtherExerContent.EXERCISE_NAME;
import static com.example.kidneyphrs.OtherExerContent.TABLE_NAME;
import static com.example.kidneyphrs.OtherExerContent.TIME_SEC;

/**
 * Created by pawitra on 2/25/2017 AD.
 */

public class OtherExerResultHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "OtherExerResult.db";
    private static final int DATABASE_VERSION = 1;

    public OtherExerResultHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + EXERCISE_NAME + " STRING, " + TIME_SEC + " INTEGER, " + AMOUNT + " INTEGER);" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

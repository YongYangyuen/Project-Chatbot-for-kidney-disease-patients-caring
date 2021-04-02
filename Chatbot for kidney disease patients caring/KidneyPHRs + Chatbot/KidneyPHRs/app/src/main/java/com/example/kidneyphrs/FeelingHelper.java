package com.example.kidneyphrs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.FeelingContent.BACKACHE;
import static com.example.kidneyphrs.FeelingContent.DIARRHEA;
import static com.example.kidneyphrs.FeelingContent.DRYSKIN;
import static com.example.kidneyphrs.FeelingContent.FINGERNUMBNESS;
import static com.example.kidneyphrs.FeelingContent.HEADACHE;
import static com.example.kidneyphrs.FeelingContent.ITCHSKIN;
import static com.example.kidneyphrs.FeelingContent.MOREFEELING;
import static com.example.kidneyphrs.FeelingContent.RASHSKIN;
import static com.example.kidneyphrs.FeelingContent.SLEEPING;
import static com.example.kidneyphrs.FeelingContent.SWELLING;
import static com.example.kidneyphrs.FeelingContent.TABLE_NAME_FEELING;
import static com.example.kidneyphrs.FeelingContent.TIRED;
import static com.example.kidneyphrs.FeelingContent.URINARYDISORDER;
import static com.example.kidneyphrs.FeelingContent.VOMIT;
import static com.example.kidneyphrs.MedicineInfoContent.DATE;

/**
 * Created by pawitra on 3/8/2017 AD.
 */

public class FeelingHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Feeling.db";
    private static final int DATABASE_VERSION = 1;

    public FeelingHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_FEELING + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + SLEEPING + " INTEGER," + HEADACHE + " INTEGER, " + VOMIT + " INTEGER, " + BACKACHE + " INTEGER, " + ITCHSKIN + " INTEGER, " + DRYSKIN + " INTEGER, " + RASHSKIN + " INTEGER, " + TIRED + " INTEGER, " + DIARRHEA + " INTEGER, " + SWELLING + " INTEGER, " + URINARYDISORDER + " INTEGER, " + FINGERNUMBNESS + " INTEGER, " + MOREFEELING + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}

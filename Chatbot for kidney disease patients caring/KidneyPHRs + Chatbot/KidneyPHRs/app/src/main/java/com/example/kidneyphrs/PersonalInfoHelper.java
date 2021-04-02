package com.example.kidneyphrs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;

import static com.example.kidneyphrs.PersonalInfoContent.BIRTHDAY;
import static com.example.kidneyphrs.PersonalInfoContent.DATE;
import static com.example.kidneyphrs.PersonalInfoContent.GENDER;
import static com.example.kidneyphrs.PersonalInfoContent.HEIGHT;
import static com.example.kidneyphrs.PersonalInfoContent.PHONE;
import static com.example.kidneyphrs.PersonalInfoContent.TABLE_NAME_INFOR;
import static com.example.kidneyphrs.PersonalInfoContent.USERNAME;
import static com.example.kidneyphrs.PersonalInfoContent.WEIGHT;

/**
 * Created by pawitra on 3/1/2017 AD.
 */

public class PersonalInfoHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PersonalInfo.db";
    private static final int DATABASE_VERSION = 1;

    public PersonalInfoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_INFOR + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + USERNAME + " TEXT," + BIRTHDAY + " TEXT," + GENDER + " TEXT," + WEIGHT + " INTEGER, " + HEIGHT + " INTEGER, " + PHONE + " TEXT);" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
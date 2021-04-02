package com.example.kidneyphrs;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.kidneyphrs.ExerContent.CATAGORY;
import static com.example.kidneyphrs.ExerContent.EXERCISE_NAME;
import static com.example.kidneyphrs.ExerContent.TABLE_NAME;
import static com.example.kidneyphrs.ExerContent._ID;

/**
 * Created by pawitra on 2/25/2017 AD.
 */

public class ExerHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "exercises.db";
    private static final int DATABASE_VERSION = 1;

    public ExerHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EXERCISE_NAME + " TEXT, " + CATAGORY + " INTEGER);");

        /*ContentValues values1 = new ContentValues();
        values1.put(EXERCISE_NAME, "ยืดกล้ามเนื้อคอ");
        db.insert(TABLE_NAME, null, values1);
        ContentValues values2 = new ContentValues();
        values2.put(EXERCISE_NAME, "ยืดกล้ามเนื้อแขน");
        db.insert(TABLE_NAME, null, values2);*/

        // addExerItem(db, "ยืดกล้ามเนื้อแขน", 1);
        addExerItem(db, "ยืดกล้ามเนื้อคอ", 1);
        addExerItem(db, "หมุนไหล่", 1);
        addExerItem(db, "ยืดกล้ามเนื้ออกและหลังส่วนบน", 1);
        addExerItem(db, "ยืดกล้ามเนื้อบริเวณสีข้าง", 1);
        // addExerItem(db, "ยืดกล้ามเนื้อเข่า", 1);
        // addExerItem(db, "ยืดกล้ามเนื้อขา", 1);
        // addExerItem(db, "ยืดกล้ามเนื้อน่อง", 1);

        addExerItem(db, "บริหารกล้ามเนื้อแขน", 2);
        // addExerItem(db, "บริหารกล้ามเนื้อแขนด้านหลัง", 2);
        addExerItem(db, "บริหารกล้ามเนื้อน่อง", 2);
        // addExerItem(db, "บริหารกล้ามเนื้อต้นขา", 2);
        // addExerItem(db, "Seated Marching", 2);
        addExerItem(db, "แกว่งขาด้านหลัง", 2);
        addExerItem(db, "Side leg lift", 2);
        addExerItem(db, "นอนยกตัว", 2);
        // addExerItem(db, "ดันกำแพง", 2);
        // addExerItem(db, "ดันเก้าอี้", 2);
        // addExerItem(db, "ฝึกกล้ามเนื้ออก", 2);
        // addExerItem(db, "ฝึกกล้ามเนื้อขาด้วยการขึ้นบันได", 2);
        // addExerItem(db, "ฝึกกล้ามเนื้อขาด้วยการลุกนั่งเก้าอี้", 2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void addExerItem(SQLiteDatabase db, String exerName, int catagory){
        ContentValues values = new ContentValues();
        values.put(EXERCISE_NAME, exerName);
        values.put(CATAGORY, catagory);
        db.insert(TABLE_NAME, null, values);
    }
}

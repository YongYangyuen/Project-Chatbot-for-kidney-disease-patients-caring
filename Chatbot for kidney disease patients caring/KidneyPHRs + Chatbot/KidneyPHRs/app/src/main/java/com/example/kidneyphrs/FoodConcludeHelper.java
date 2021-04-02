package com.example.kidneyphrs;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.FoodConcludeContent.AMOUNT;
import static com.example.kidneyphrs.FoodConcludeContent.CALORY_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.CATEGORY_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.DATE;
import static com.example.kidneyphrs.FoodConcludeContent.FOOD_NAME_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.MEAL;
import static com.example.kidneyphrs.FoodConcludeContent.PHOSPHORUS_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.POTASSIUM_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.PROTEIN_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.SODIUM_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.TABLE_NAME_CONCLUDE;

/**
 * Created by pawitra on 2/19/2017 AD.
 */

public class FoodConcludeHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "foodConclude.db";
    private static final int DATABASE_VERSION = 1;

    public FoodConcludeHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_CONCLUDE + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + FOOD_NAME_CONCLUDE + " TEXT, "  + CATEGORY_CONCLUDE + " INTEGER, " + CALORY_CONCLUDE + " INTEGER, " + PROTEIN_CONCLUDE + " DOUBLE, " + SODIUM_CONCLUDE + " DOUBLE, " + POTASSIUM_CONCLUDE + " DOUBLE, " + PHOSPHORUS_CONCLUDE + " DOUBLE, " + AMOUNT + " INTEGER, " + MEAL + " TEXT);");

        try {
            addFoodItem(db, "2017-04-27", "ก๋วยจั๊บน้ำข้น", 368, 1, 21.60, 2668.00, 516.00, 267.00, 1, "เช้า");
            addFoodItem(db, "2017-04-27", "ข้าวคลุกกะปิ", 565, 1, 20.50, 1999.00, 519.00, 260.00, 1, "กลางวัน");
            addFoodItem(db, "2017-04-27", "สุกี้ไก่น้ำ", 253, 1, 20.30, 3542.00, 436.00, 222.00, 1, "เย็น");
            addFoodItem(db, "2017-04-28", "เกาเหลาหมู", 163, 2, 15.20, 1878.00, 256.00, 119.00, 2, "เช้า");
            addFoodItem(db, "2017-04-28", "ผัดไทกุ้งสด", 486, 1, 20.90, 1060.00, 417.00, 317.00, 1, "กลางวัน");
            addFoodItem(db, "2017-04-28", "ข้าวหมูอบ", 432, 1, 19.70, 1354.00, 425.00, 188.00, 1, "เย็น");
            addFoodItem(db, "2017-04-29", "บะหมี่เกี๊ยวหมูแดง", 332, 1, 17.50, 1925.00, 447.00, 141.00, 1, "เช้า");
            addFoodItem(db, "2017-04-29", "หมี่กรอบราดหน้าทะเล", 457, 1, 14.30, 2051.00, 332.00, 142.00, 1, "กลางวัน");
            addFoodItem(db, "2017-04-29", "สเต็กเนื้อสันนอก", 324, 1, 49.47, 563.00, 629.00, 382.00, 1, "เย็น");
            addFoodItem(db, "2017-04-30", "ข้าวมันไก่", 619, 1, 10.90, 1251.00, 213.00, 95.00, 1, "เช้า");
            addFoodItem(db, "2017-04-30", "ข้าวผัดหมู", 581, 1, 22.70, 906.00, 212.00, 243.00, 1, "กลางวัน");
            addFoodItem(db, "2017-04-30", "ขนมจีนแกงเขียวหวาน", 416, 1, 17.70, 1538.00, 702.00, 221.00, 1, "เย็น");
            addFoodItem(db, "2017-05-01", "ก๋วยเตี๋ยวเส้นหมี่ลูกชิ้นน้ำใส", 244, 1, 14.50, 1786.00, 303.00, 438.00, 1, "เช้า");
            addFoodItem(db, "2017-05-01", "ข้าวผัดหมู", 581, 1, 22.70, 906.00, 212.00, 243.00, 1, "กลางวัน");
            addFoodItem(db, "2017-05-01", "บะหมี่แห้งหมู", 463, 1, 26.60, 2377.00, 324.00, 207.00, 1, "เย็น");
            addFoodItem(db, "2017-05-02", "ก๋วยจั๊บน้ำข้น", 368, 1, 21.60, 2668.00, 516.00, 267.00, 1, "เช้า");
            addFoodItem(db, "2017-05-02", "ข้าวคลุกกะปิ", 565, 1, 20.50, 1999.00, 519.00, 260.00, 1, "กลางวัน");
            addFoodItem(db, "2017-05-02", "สุกี้ไก่น้ำ", 253, 1, 20.30, 3542.00, 436.00, 222.00, 1, "เย็น");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    private void addFoodItem(SQLiteDatabase db, String date, String foodName, Integer calory, Integer category, Double protein, Double sodium, Double potassium, Double phosphorus, Integer amount, String meal) throws ParseException {

        ContentValues values = new ContentValues();
        values.put(DATE , date);
        values.put(FOOD_NAME_CONCLUDE , foodName);
        values.put(CALORY_CONCLUDE , calory);
        values.put(CATEGORY_CONCLUDE , category);
        values.put(PROTEIN_CONCLUDE , protein);
        values.put(SODIUM_CONCLUDE , sodium);
        values.put(POTASSIUM_CONCLUDE , potassium);
        values.put(PHOSPHORUS_CONCLUDE , phosphorus);
        values.put(AMOUNT , amount);
        values.put(MEAL , meal);

        db.insert(TABLE_NAME_CONCLUDE, null, values);
    }
}
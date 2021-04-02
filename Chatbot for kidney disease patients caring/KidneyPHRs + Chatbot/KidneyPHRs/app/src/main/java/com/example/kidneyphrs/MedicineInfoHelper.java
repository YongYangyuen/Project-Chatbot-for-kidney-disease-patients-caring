package com.example.kidneyphrs;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.MedicineInfoContent.AMOUNT;
import static com.example.kidneyphrs.MedicineInfoContent.BEFOREBED;
import static com.example.kidneyphrs.MedicineInfoContent.DATE;
import static com.example.kidneyphrs.MedicineInfoContent.EVENING;
import static com.example.kidneyphrs.MedicineInfoContent.IMGMED;
import static com.example.kidneyphrs.MedicineInfoContent.MEDNAME;
import static com.example.kidneyphrs.MedicineInfoContent.MORNING;
import static com.example.kidneyphrs.MedicineInfoContent.NOON;
import static com.example.kidneyphrs.MedicineInfoContent.TABLE_NAME_MED;
import static com.example.kidneyphrs.MedicineInfoContent.TIMEMEAL;
import static com.example.kidneyphrs.MedicineInfoContent.UNIT;

/**
 * Created by pawitra on 3/7/2017 AD.
 */

public class MedicineInfoHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MedicineInfo.db";
    private static final int DATABASE_VERSION = 1;

    public MedicineInfoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_MED + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + MEDNAME + " TEXT," + AMOUNT + " INTEGER," + UNIT + " TEXT," + MORNING + " INTEGER, " + NOON + " INTEGER, " +  EVENING + " INTEGER, " + BEFOREBED + " INTEGER, " + TIMEMEAL + " TEXT, " + IMGMED + " TEXT);" );

        addMedicineItem(db, "Enalapril", 1, "เม็ด", 1, 1, 0, 0, "หลังอาหาร", "");
        addMedicineItem(db, "Furosemide", 1, "เม็ด", 1, 0, 0, 0, "หลังอาหาร", "");
        addMedicineItem(db, "Ketamine", 1, "ซอง", 1, 1, 1, 0, "หลังอาหาร", "");
        addMedicineItem(db, "Sodamint", 1, "เม็ด", 1, 1, 1, 0, "หลังอาหาร", "");



    }


//    private static final String deletetbl = "DROP TABLE IF EXISTS " + MedicineInfoContent.TABLE_NAME_MED;

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(deletetbl);
//        onCreate(db);

    }

    private void addMedicineItem(SQLiteDatabase db, String Medname, Integer Amount, String Unit, Integer Morning, Integer Noon, Integer Evening, Integer Beforebed, String Timemeal, String Imgmed){
        ContentValues values = new ContentValues();
        values.put(MEDNAME , Medname);
        values.put(AMOUNT, Amount);
        values.put(UNIT , Unit);
        values.put(MORNING , Morning);
        values.put(NOON , Noon);
        values.put(EVENING , Evening);
        values.put(BEFOREBED , Beforebed);
        values.put(TIMEMEAL , Timemeal);
        values.put(IMGMED, Imgmed);
        db.insert(TABLE_NAME_MED, null, values);



    }
}

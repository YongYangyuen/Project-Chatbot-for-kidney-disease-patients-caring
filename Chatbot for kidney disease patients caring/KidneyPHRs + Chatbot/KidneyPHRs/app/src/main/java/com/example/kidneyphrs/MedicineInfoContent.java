package com.example.kidneyphrs;

import android.provider.BaseColumns;

/**
 * Created by pawitra on 3/7/2017 AD.
 */

public interface MedicineInfoContent extends BaseColumns {
    public static final String TABLE_NAME_MED = "MedicineInfo";
    public static final String DATE = "Date";
    public static final String MEDNAME = "Medname";
    public static final String AMOUNT = "Amount";
    public static final String UNIT = "Unit";
    public static final String MORNING = "Morning";
    public static final String NOON = "Noon";
    public static final String EVENING = "Evening";
    public static final String BEFOREBED = "Beforebed";
    public static final String TIMEMEAL = "Timemeal";
    public static final String IMGMED = "Imgmed";
}

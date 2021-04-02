package com.example.kidneyphrs;

import android.provider.BaseColumns;

/**
 * Created by pawitra on 2/25/2017 AD.
 */

public interface OtherExerContent extends BaseColumns {
    public static final String TABLE_NAME = "OtherExerResult";
    public static final String EXERCISE_NAME = "ExerciseName";
    public static final String DATE = "date";
    public static final String TIME_SEC = "timeInSec";
    public static final String AMOUNT = "amount";
}
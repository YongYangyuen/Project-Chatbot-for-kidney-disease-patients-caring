package com.example.kidneyphrs;

/**
 * Created by pawitra on 2/18/2017 AD.
 */

import android.provider.BaseColumns;

public interface FoodContent extends BaseColumns{

    public static final String TABLE_NAME = "Food";
    public static final String FOOD_NAME = "FoodName";
    public static final String CATEGORY = "Category";
    public static final String CALORY = "Calory";
    public static final String SODIUM = "Sodium";
    public static final String POTASSIUM = "Potassium";
    public static final String PROTEIN = "Protein";
    public static final String PHOSPHORUS = "Phosphorus";

}

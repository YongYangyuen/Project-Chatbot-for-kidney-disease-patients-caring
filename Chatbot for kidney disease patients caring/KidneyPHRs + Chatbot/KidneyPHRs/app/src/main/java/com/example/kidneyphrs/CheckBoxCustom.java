package com.example.kidneyphrs;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by pawitra on 3/7/2017 AD.
 */

public class CheckBoxCustom extends CheckBox {
    public CheckBoxCustom(Context context) {
        super(context);
        init();
    }

    public CheckBoxCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckBoxCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CheckBoxCustom(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/THSarabun Bold.ttf");
        setTypeface(tf);
    }
}

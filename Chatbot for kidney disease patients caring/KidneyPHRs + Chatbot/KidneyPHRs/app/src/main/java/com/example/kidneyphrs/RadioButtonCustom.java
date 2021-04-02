package com.example.kidneyphrs;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

/**
 * Created by pawitra on 3/4/2017 AD.
 */

public class RadioButtonCustom extends RadioButton {
    public RadioButtonCustom(Context context) {
        super(context);
        init();
    }

    public RadioButtonCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadioButtonCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RadioButtonCustom(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/THSarabun Bold.ttf");
        setTypeface(tf);
    }
}

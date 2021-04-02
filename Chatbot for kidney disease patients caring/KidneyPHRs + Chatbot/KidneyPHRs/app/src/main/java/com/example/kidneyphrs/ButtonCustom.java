package com.example.kidneyphrs;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by pawitra on 2/19/2017 AD.
 */

public class ButtonCustom extends Button {
    public ButtonCustom(Context context) {
        super(context);
        init();
    }

    public ButtonCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ButtonCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ButtonCustom(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/THSarabun Bold.ttf");
        setTypeface(tf);
    }
}

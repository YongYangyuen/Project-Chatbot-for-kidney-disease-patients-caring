package com.example.kidneyphrs;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by pawitra on 2/18/2017 AD.
 */

public class TextviewCustom extends TextView {
    public TextviewCustom(Context context) {
        super(context);
        init();
    }

    public TextviewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextviewCustom(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/THSarabun Bold.ttf");
        setTypeface(tf);
    }
}

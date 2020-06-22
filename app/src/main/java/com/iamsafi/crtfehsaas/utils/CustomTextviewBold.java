package com.iamsafi.crtfehsaas.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

public class CustomTextviewBold extends TextView {
    public CustomTextviewBold(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public CustomTextviewBold(Context context,  AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public CustomTextviewBold(Context context,  AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

  /*  public CustomTextviewBold(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
       // super(context, attrs, defStyleAttr, defStyleRes);
        applyCustomFont(context);
    }*/

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("Raleway-Bold.ttf", context);
        setTypeface(customFont);
        setTextSize(14);

    }
}

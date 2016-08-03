package com.chilangolabs.reporteciudadano.customelements;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import com.chilangolabs.reporteciudadano.R;

public class MontserratButton extends Button {
    public MontserratButton(Context context) {
        super(context);
        if (!isInEditMode()) {
            FontStyle.initStyle(getContext(), this);
        }
    }

    public MontserratButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.montserrat_font,
                    0, 0);

            try {
                int font = typedArray.getInteger(R.styleable.montserrat_font_font, 0);
                FontStyle.initStyle(getContext(), font, this);
            } finally {
                typedArray.recycle();
            }
        }
    }
}

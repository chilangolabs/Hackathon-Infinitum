package com.chilangolabs.reporteciudadano.customelements;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FontStyle {

    public static void initStyle(Context ctx, View v) {
        Typeface tp = Typeface.createFromAsset(ctx.getAssets(), "fonts/Montserrat-Regular.otf");
        setTypeface(tp, v);
    }

    public static void initStyle(Context ctx, int font, View v) {
        Typeface tp;
        switch (font) {
            case 0:
                tp = Typeface.createFromAsset(ctx.getAssets(), "fonts/Montserrat-Black.otf");
                setTypeface(tp, v);
                break;
            case 1:
                tp = Typeface.createFromAsset(ctx.getAssets(), "fonts/Montserrat-Bold.otf");
                setTypeface(tp, v);
                break;
            case 2:
                tp = Typeface.createFromAsset(ctx.getAssets(), "fonts/Montserrat-ExtraBold.otf");
                setTypeface(tp, v);
                break;
            case 3:
                tp = Typeface.createFromAsset(ctx.getAssets(), "fonts/Montserrat-Hairline.otf");
                setTypeface(tp, v);
                break;
            case 4:
                tp = Typeface.createFromAsset(ctx.getAssets(), "fonts/Montserrat-Light.otf");
                setTypeface(tp, v);
                break;
            case 5:
                tp = Typeface.createFromAsset(ctx.getAssets(), "fonts/Montserrat-Regular.otf");
                setTypeface(tp, v);
                break;
            case 6:
                tp = Typeface.createFromAsset(ctx.getAssets(), "fonts/Montserrat-SemiBold.otf");
                setTypeface(tp, v);
                break;
            case 7:
                tp = Typeface.createFromAsset(ctx.getAssets(), "fonts/Montserrat-UltraLight.otf");
                setTypeface(tp, v);
                break;
            default:
                tp = Typeface.createFromAsset(ctx.getAssets(), "fonts/Montserrat-Regular.otf");
                setTypeface(tp, v);
        }
    }

    public static void setTypeface(Typeface typeface, View v) {
        if (v instanceof TextView) {
            ((TextView) v).setTypeface(typeface);
        } else if (v instanceof EditText) {
            ((EditText) v).setTypeface(typeface);
        } else if (v instanceof Button) {
            ((Button) v).setTypeface(typeface);
        }
    }
}

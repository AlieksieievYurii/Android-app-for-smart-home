package com.whitedeveloper.controlhome.factory.convertor;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class ConverterDpPx
{
    public static int pxToDp(float px){
    	    DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
    	    float dp = px / (metrics.densityDpi / 160f);
    	    return Math.round(dp);
    }
    public static int dpToPx(float dp){
    	DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
    	float px = dp * (metrics.densityDpi / 160f);
    	return Math.round(px);
    }

	public static int dpToSp(float dp, Context context) {
	    return (int) (dpToPx(dp, context) / context.getResources().getDisplayMetrics().scaledDensity);
	}

	public static int dpToPx(float dp, Context context) {
	    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
	}

	public static int pxToSp(int px,Context context)
	{
		return dpToSp(pxToDp(px),context) + 1;
	}
}

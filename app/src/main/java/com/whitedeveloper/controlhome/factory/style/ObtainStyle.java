package com.whitedeveloper.controlhome.factory.style;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.factory.convertor.ConverterDpPx;

public class ObtainStyle
{

    public static void setStyleButton(Context context,Button button)
    {
        final TypedArray typedArray = context.obtainStyledAttributes(R.style.view_buttons,R.styleable.style_button);
        final GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(
                new ViewGroup.LayoutParams(
                     (typedArray.getLayoutDimension(R.styleable.style_button_android_layout_width,0)),
                        typedArray.getLayoutDimension(R.styleable.style_button_android_layout_height,0))
        );
        layoutParams.setMargins(typedArray.getLayoutDimension(R.styleable.style_button_android_layout_marginLeft,0),
                typedArray.getLayoutDimension(R.styleable.style_button_android_layout_marginTop,0),
                typedArray.getLayoutDimension(R.styleable.style_button_android_layout_marginRight,0),
                typedArray.getLayoutDimension(R.styleable.style_button_android_layout_marginBottom,0)
        );
        button.setLayoutParams(layoutParams);
        button.setTextColor(typedArray.getColor(R.styleable.style_button_android_textColor, Color.WHITE));
        button.setTextSize(ConverterDpPx.pxToSp(typedArray.getDimensionPixelOffset(R.styleable.style_button_android_textSize,10),context));
        button.setPadding(0,typedArray.getLayoutDimension(R.styleable.style_button_android_paddingTop,0),0,0);

        typedArray.recycle();
    }

    public static void setStyleSeekBar(Context context, BoxedVertical boxedVertical)
    {
        final TypedArray typedArray = context.obtainStyledAttributes(R.style.view_seek_bars,R.styleable.style_seek_bar);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(typedArray.getLayoutDimension(R.styleable.style_seek_bar_android_layout_margin,0),
                typedArray.getLayoutDimension(R.styleable.style_seek_bar_android_layout_margin,0),
                typedArray.getLayoutDimension(R.styleable.style_seek_bar_android_layout_margin,0),
                typedArray.getLayoutDimension(R.styleable.style_seek_bar_android_layout_margin,0));
        layoutParams.weight = 1;

        boxedVertical.setLayoutParams(layoutParams);
        boxedVertical.setCornerRadius(typedArray.getInt(R.styleable.style_seek_bar_libCornerRadius,1));
        boxedVertical.setStep(typedArray.getInt(R.styleable.style_seek_bar_step,1));
        boxedVertical.setDefaultValue(typedArray.getInt(R.styleable.style_seek_bar_defaultValue,0));

        typedArray.recycle();
    }

    public static void setStyleTextViewForSeekBar(Context context, TextView textView)
    {
        final TypedArray typedArray = context.obtainStyledAttributes(R.style.view_seek_bars,R.styleable.style_seek_bar);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 0;

        textView.setLayoutParams(layoutParams);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextColor(typedArray.getColor(R.styleable.style_seek_bar_android_textColor,Color.WHITE));
        textView.setTextSize(ConverterDpPx.pxToSp(typedArray.getDimensionPixelOffset(R.styleable.style_seek_bar_android_textSize,10),context));
        textView.setAllCaps(true);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        typedArray.recycle();
    }

    public static void setStyleLayoutSeekBar(Context context, LinearLayout linearLayout)
    {
        final TypedArray typedArray = context.obtainStyledAttributes(R.style.view_seek_bars,R.styleable.style_seek_bar);
        final GridLayout.LayoutParams layoutParams =
                new GridLayout.LayoutParams(
                        new ViewGroup.LayoutParams(typedArray.getLayoutDimension(R.styleable.style_seek_bar_android_layout_width,0),
                                typedArray.getLayoutDimension(R.styleable.style_seek_bar_android_layout_height,0)));
        layoutParams.rowSpec = GridLayout.spec(GridLayout.UNDEFINED,2);

        layoutParams.setMargins(ConverterDpPx.dpToPx(6),ConverterDpPx.dpToPx(6),ConverterDpPx.dpToPx(6),ConverterDpPx.dpToPx(6));
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(RelativeLayout.CENTER_HORIZONTAL);
        linearLayout.setBackgroundResource(R.drawable.background_seek_bar_container);

        typedArray.recycle();
        }

    public static void setStyleTextView(Context context, TextView textView)
    {
        final TypedArray typedArray = context.obtainStyledAttributes(R.style.view_sensors,R.styleable.style_text_view);
        final GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(
             new ViewGroup.LayoutParams(typedArray.getLayoutDimension(R.styleable.style_text_view_android_layout_width,0),
                             typedArray.getLayoutDimension(R.styleable.style_text_view_android_layout_height,0))
        );
        layoutParams.setMargins(typedArray.getLayoutDimension(R.styleable.style_text_view_android_layout_marginLeft,0),
                typedArray.getLayoutDimension(R.styleable.style_text_view_android_layout_marginTop,0),
                typedArray.getLayoutDimension(R.styleable.style_text_view_android_layout_marginRight,0),
                typedArray.getLayoutDimension(R.styleable.style_text_view_android_layout_marginBottom,0));
        textView.setLayoutParams(layoutParams);
        textView.setPadding(0,typedArray.getLayoutDimension(R.styleable.style_text_view_android_paddingTop,0),0,0);
        textView.setTextSize(ConverterDpPx.pxToSp(typedArray.getDimensionPixelOffset(R.styleable.style_text_view_android_textSize,10),context));
        textView.setTextColor(typedArray.getColor(R.styleable.style_text_view_android_textColor,Color.WHITE));
        textView.setTextAlignment(typedArray.getInt(R.styleable.style_text_view_android_textAlignment, TextView.TEXT_ALIGNMENT_CENTER));

        typedArray.recycle();
    }
}

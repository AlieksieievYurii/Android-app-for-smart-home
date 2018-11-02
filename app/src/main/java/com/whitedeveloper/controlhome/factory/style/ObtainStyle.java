package com.whitedeveloper.controlhome.factory.style;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.factory.convertor.ConverterDpPx;

public class ObtainStyle
{

    public static void setStyleButton(Context context,Button button)
    {
        TypedArray typedArray = context.obtainStyledAttributes(R.style.view_buttons,R.styleable.style_button);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(
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
        TypedArray typedArray = context.obtainStyledAttributes(R.style.view_seek_bars,R.styleable.style_seek_bar);
        GridLayout.LayoutParams layoutParams =
                new GridLayout.LayoutParams(
                        new ViewGroup.LayoutParams(typedArray.getLayoutDimension(R.styleable.style_seek_bar_android_layout_width,0),
                                typedArray.getLayoutDimension(R.styleable.style_seek_bar_android_layout_height,0))
                );
        layoutParams.rowSpec = GridLayout.spec(GridLayout.UNDEFINED,2);

        layoutParams.setMargins(typedArray.getLayoutDimension(R.styleable.style_seek_bar_android_layout_margin,0),
                typedArray.getLayoutDimension(R.styleable.style_seek_bar_android_layout_margin,0),
                typedArray.getLayoutDimension(R.styleable.style_seek_bar_android_layout_margin,0),
                typedArray.getLayoutDimension(R.styleable.style_seek_bar_android_layout_margin,0));
        boxedVertical.setLayoutParams(layoutParams);
        boxedVertical.setCornerRadius(typedArray.getInt(R.styleable.style_seek_bar_libCornerRadius,1));
        boxedVertical.setStep(typedArray.getInt(R.styleable.style_seek_bar_step,1));
        boxedVertical.setDefaultValue(typedArray.getInt(R.styleable.style_seek_bar_defaultValue,0));

        typedArray.recycle();
    }

    public static void setStyleTextView(Context context, TextView textView)
    {
        TypedArray typedArray = context.obtainStyledAttributes(R.style.view_sensors,R.styleable.style_text_view);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(
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

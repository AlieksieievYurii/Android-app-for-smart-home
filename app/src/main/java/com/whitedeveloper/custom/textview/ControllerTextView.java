package com.whitedeveloper.custom.textview;

import android.view.View;
import android.widget.TextView;

public class ControllerTextView
{
    private final TextView textViewSensor;
    private final String name;
    private int value;

    public ControllerTextView(TextView textViewSensor, String nameSensorArduino) {
        this.textViewSensor = textViewSensor;
        this.name = nameSensorArduino;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        textViewSensor.setText(String.valueOf(value));
    }

    void setOnLongClickListener(View.OnLongClickListener onClickListener)
    {
        textViewSensor.setOnLongClickListener(onClickListener);
    }

    public TextView getTextViewSensor() {
        return textViewSensor;
    }
}

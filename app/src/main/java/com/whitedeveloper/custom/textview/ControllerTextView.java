package com.whitedeveloper.custom.textview;

import android.widget.TextView;

public class ControllerTextView
{
    private TextView textViewSensor;
    private String name;
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

    public TextView getTextViewSensor() {
        return textViewSensor;
    }
}

package com.whitedeveloper.custom.textview;

import android.widget.TextView;
import com.whitedeveloper.custom.textview.intervals.Intervals;

public class ControllerTextView
{
    private TextView textViewSensor;
    private String name;
    private int value;
    private Intervals intervals;

    public ControllerTextView(TextView textViewSensor, String name, Intervals intervals) {
        this.textViewSensor = textViewSensor;
        this.name = name;
        this.intervals = intervals;
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
        setState();
    }

    private void setState()
    {
        if(intervals == null)
            return;

        if(value >= intervals.getInterval())
            textViewSensor.setActivated(true);
        else textViewSensor.setActivated(false);
    }
}

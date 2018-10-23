package com.whitedeveloper.custom.seekbar;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;
import com.whitedeveloper.custom.PinArduino;


public class ControllerSeekBar {

    private BoxedVertical seekBar;
    private PinArduino arduinoPin;

    public ControllerSeekBar(BoxedVertical seekBar, PinArduino arduinoPin) {
        this.seekBar = seekBar;
        this.arduinoPin = arduinoPin;

        init();
    }

    private void init()
        {
            seekBar.setMax(255);
        }

    void setOnDoListener(BoxedVertical.OnValuesChangeListener onValuesChangeListener)
    {
        seekBar.setOnBoxedPointsChangeListener(onValuesChangeListener);
    }

    public BoxedVertical getSeekBar() {
        return seekBar;
    }

    public PinArduino getPinArduino() {
        return arduinoPin;
    }
    public int getId()
    {
        return seekBar.getId();
    }

    public void setValue(int value)
    {
        arduinoPin.setValue(value);
        seekBar.setValue(value);
    }
}

package com.whitedeveloper.custom.seekbar;

import android.widget.SeekBar;

public class ControllerSeekBar {

    private SeekBar seekBar;
    private int pin;

    public ControllerSeekBar(SeekBar seekBar, int pin) {
        this.seekBar = seekBar;
        this.pin = pin;

        init();
    }

    private void init()
    {
        seekBar.setMax(255);
    }

    public SeekBar getSeekBar() {
        return seekBar;
    }

    public int getPin() {
        return pin;
    }

    public void setValue(int value)
    {
        seekBar.setProgress(value);
    }
}
